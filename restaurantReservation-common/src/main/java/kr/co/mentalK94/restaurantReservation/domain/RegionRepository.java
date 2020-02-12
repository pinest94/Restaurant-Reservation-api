package kr.co.mentalK94.restaurantReservation.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    List<Region> findAll();
}
