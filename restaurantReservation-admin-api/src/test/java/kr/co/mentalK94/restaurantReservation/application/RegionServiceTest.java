package kr.co.mentalK94.restaurantReservation.application;


import kr.co.mentalK94.restaurantReservation.domain.Region;
import kr.co.mentalK94.restaurantReservation.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RegionServiceTest {

    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions() {

        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder().name("Seoul dongsomunro").build());

        given(regionRepository.findAll()).willReturn(mockRegions);

        List<Region> regions = regionService.getRegions();

        assertThat(regions.get(0).getName(), is("Seoul dongsomunro"));
    }

}