package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.StoreDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Address;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.AddressRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.StoreRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Store create(StoreDto dto, Long supplierId) {
        // Buscar el proveedor por ID
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent()) {
            throw new RuntimeException("Supplier not found with ID: " + supplierId);
        }

        Store store = new Store();
        store.setSupplier(optionalSupplier.get()); // Asignar el objeto Supplier
        store.setName(dto.getName());

        Address addressNew = new Address();
        addressNew.setCountry("Chile");
        addressNew.setStreet("Av 25");
        addressNew.setCity("Santigo de Chile");

        store.setAddress(addressNew);
        return storeRepository.save(store);
    }

    public Store update(StoreDto dto, Long supplierId, Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        Optional<Supplier> suplierOptional = supplierRepository.findById(supplierId);

        if (storeOptional.isEmpty()){
             throw new RuntimeException("Store not found with ID: " + id);
        }

        if (suplierOptional.isEmpty()){
             throw new RuntimeException("Suplier not found with ID: " + supplierId);
        }

        Store store = storeOptional.get();
        store.setName(dto.getName());

        return storeRepository.save(store);
    }

    public List<Store> findStoreByCity(String city) {
        List<Address> addressList = addressRepository.findAllByCity(city);
        List<Long> ids =  addressList.stream().map(Address::getId).toList();
        return storeRepository.findAllByAddress(ids);
    }
}