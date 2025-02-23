package com.alejjandrodev.ArcaWareHouse.repositories;

import com.alejjandrodev.ArcaWareHouse.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
