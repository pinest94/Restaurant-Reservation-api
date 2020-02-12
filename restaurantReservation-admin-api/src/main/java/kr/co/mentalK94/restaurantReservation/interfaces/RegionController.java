package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.RegionService;
import kr.co.mentalK94.restaurantReservation.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {

        List<Region> regions = regionService.getRegions();

//        List<Region> regions = new ArrayList<>();
//        regions.add(Region.builder().name("Seoul dongsomunro 63bunkil").build());

        return regions;
    }
}
