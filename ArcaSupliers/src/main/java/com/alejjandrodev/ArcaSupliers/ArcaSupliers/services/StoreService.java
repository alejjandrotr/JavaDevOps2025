package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.StoreDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.StoreRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SupplierRepository supplierRepository; // Inyectar el repositorio del proveedor

    public Store create(StoreDto dto, Long supplierId) {
        // Buscar el proveedor por ID
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent()) {
            throw new RuntimeException("Supplier not found with ID: " + supplierId);
        }

        Store store = new Store();
        store.setSupplier(optionalSupplier.get()); // Asignar el objeto Supplier
        store.setName(dto.getName());

        return storeRepository.save(store);
    }
}