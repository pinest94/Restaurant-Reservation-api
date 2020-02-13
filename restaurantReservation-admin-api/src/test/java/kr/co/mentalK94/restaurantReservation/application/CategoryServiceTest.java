package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.Category;
import kr.co.mentalK94.restaurantReservation.domain.CategoryRepository;
import kr.co.mentalK94.restaurantReservation.domain.Region;
import kr.co.mentalK94.restaurantReservation.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories() {

        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategories();

        assertThat(categories.get(0).getName(), is("Korean Food"));
    }

    @Test
    public void addRegion() {
        Category category = categoryService.addCategory("Korean Food");

        verify(categoryRepository).save(any());

        assertThat(category.getName(), is("Korean Food"));
    }

}