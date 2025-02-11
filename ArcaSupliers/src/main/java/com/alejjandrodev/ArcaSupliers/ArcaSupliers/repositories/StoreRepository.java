package com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Address;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s  FROM Store s WHERE s.address.id IN :ids")
    List<Store> findAllByAddress(@Param("ids") List<Long> ids);
}
