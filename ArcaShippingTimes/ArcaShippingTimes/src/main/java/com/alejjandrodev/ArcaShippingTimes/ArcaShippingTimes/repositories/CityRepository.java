package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
