package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.AddressRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.StoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class StoreServiceAceptanceTest {
    @Mock
    private StoreRepository storeRepository;

    @Mock
    private AddressRepository addressRepository;


    @Autowired
    StoreService storeService;


    @Test
    void TestgetStoreFromOneCity(){
        String city = "Santiago de Chile";

        List<Store> storeList = storeService.findStoreByCity(city);
        assertFalse(storeList.isEmpty());
        assertEquals ("Tienda Ejemplo", storeList.get(0).getName());
    }
}
