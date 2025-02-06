package com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
