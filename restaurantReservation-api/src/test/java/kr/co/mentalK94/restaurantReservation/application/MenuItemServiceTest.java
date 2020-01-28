package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.MenuItem;
import kr.co.mentalK94.restaurantReservation.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdateTest() {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        menuItems.add(MenuItem.builder()
                .name("Gukbap")
                .build());
        menuItems.add(MenuItem.builder()
                .name("Soybean Paste Stew")
                .build());
        menuItems.add(MenuItem.builder()
                .name("Bibimbap")
                .build());
        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(4)).save(any());
    }

}