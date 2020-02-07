package kr.co.mentalK94.restaurantReservation.interfaces;


import kr.co.mentalK94.restaurantReservation.application.ReviewService;
import kr.co.mentalK94.restaurantReservation.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
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

        given(reviewService.addReview(any())).willReturn(
                Review.builder()
                .id(123L)
                .writer("good hansol")
                .score(4.5)
                .description("taste great!")
                .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"writer\":\"hansol\", \"score\":5.0, \"description\":\"맛있어요!\"}"))
                .andExpect(header().string("location", "/restaurants/1/reviews/123"))
                .andExpect(status().isCreated());

        // 위에 테스트가 완료되면 어떠한 일이 일어나는가?
        verify(reviewService).addReview(any()); // 여기서 에러난다는 것은 addReview가 실행되지 않음을 의미
    }

    @Test
    public void createWithInvalidAttributes() throws Exception{
        mvc.perform(post("/restaurants/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any()); // 파라미터에 never()는 실행되지 않음을 의미
    }

}