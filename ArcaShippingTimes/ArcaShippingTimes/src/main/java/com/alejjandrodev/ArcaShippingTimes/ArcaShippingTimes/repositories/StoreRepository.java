package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByCityId(Long city);

    List<Store> findAllByCityId(Long city);
}
