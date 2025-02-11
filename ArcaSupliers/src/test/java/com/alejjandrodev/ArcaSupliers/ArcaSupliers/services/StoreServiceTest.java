package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Address;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Store;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.AddressRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.StoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    StoreService    storeService;

    @Test
    public void TestfindStoreByCity(){
        String city = "Santiago de Chile";
        List<Address> addressList = new ArrayList<>();

        Address address1 = new Address();
        address1.setId(1L);
        address1.setCity(city);

        Address address2 = new Address();
        address2.setId(2L);
        address2.setCity(city);

        Address address3 = new Address();
        address3.setId(3L);
        address3.setCity(city);

        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);

        when(addressRepository.findAllByCity(city)).thenReturn(addressList);

        List<Store> storeListMock = new ArrayList<>();
        Store store = new Store();
        store.setName("Tienda Ejemplo");
        store.setAddress(address1);

        storeListMock.add(store);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        when(storeRepository.findAllByAddress(ids)).thenReturn(storeListMock);

        List<Store> storeList = storeService.findStoreByCity(city);
        assertFalse(storeList.isEmpty());
        assertEquals( city ,storeList.get(0).getAddress().getCity());
        assertEquals( "Tienda Ejemplo" ,storeList.get(0).getName());
    }
}
