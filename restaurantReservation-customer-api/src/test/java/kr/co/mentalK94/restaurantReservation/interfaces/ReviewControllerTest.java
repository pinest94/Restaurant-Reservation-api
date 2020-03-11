package kr.co.mentalK94.restaurantReservation.interfaces;


import kr.co.mentalK94.restaurantReservation.application.ReviewService;
import kr.co.mentalK94.restaurantReservation.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidAttributes() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJybGFna3N0aGYyMDkiLCJuYW1lIjoiaGFuc29sIn0.WjDAJRtp4qYo-5WZ0bgctaGobn6SsrtvXBy0mE25uwQ";

        given(reviewService.addReview(123L, "hansol", 4.5, "taste great!")).willReturn(
                Review.builder()
                .id(1L)
                .build()
        );

        mvc.perform(post("/restaurants/123/reviews")
                .header("Authorization","Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\":4.5, \"description\":\"taste great!\"}"))
                .andExpect(header().string("location", "/restaurants/123/reviews/1"))
                .andExpect(status().isCreated());

        // 위에 테스트가 완료되면 어떠한 일이 일어나는가?
        verify(reviewService).addReview(eq(123L), eq("hansol"), eq(4.5), eq("taste great!")); // 여기서 에러난다는 것은 addReview가 실행되지 않음을 의미
    }

    @Test
    public void createWithInvalidAttributes() throws Exception{
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(anyLong(), any(), anyDouble(), any()); // 파라미터에 never()는 실행되지 않음을 의미
    }

}