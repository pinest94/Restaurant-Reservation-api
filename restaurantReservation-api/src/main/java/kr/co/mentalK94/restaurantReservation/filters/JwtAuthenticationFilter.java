package kr.co.mentalK94.restaurantReservation.filters;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.restaurantReservation.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    // TODO: 실제로 JWT 분석 필요함.

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws IOException, ServletException {
        // TODO: JWT
        Authentication authentication = getAuthentication(request);

        if(authentication != null) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null) {
            return null;
        }

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);
        return authentication;
    }
}
