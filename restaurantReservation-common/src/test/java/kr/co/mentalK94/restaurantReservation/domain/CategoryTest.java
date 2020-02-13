package kr.co.mentalK94.restaurantReservation.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void creation() {
        Category category = Category.builder().name("Korean Food").build();

        assertThat(category.getName(), is("Korean Food"));
    }

}