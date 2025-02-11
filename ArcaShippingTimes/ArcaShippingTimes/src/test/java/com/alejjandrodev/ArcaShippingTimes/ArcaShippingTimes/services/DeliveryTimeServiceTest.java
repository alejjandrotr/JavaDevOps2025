package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.TimeResponseDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.WarehouseTimeDeliveryDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.*;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.enums.WarehouseType;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.NotFoundStoreInCity;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryTimeServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private WarehouseDeliveryTimeRepository warehouseDeliveryTimeRepository;

    @Mock
    private StoreDeliveryTimeRepository storeDeliveryTimeRepository;

    @Mock
    private WarehouseInventoryRepository warehouseInventoryRepository;

    @Mock
    private StoreInventoryRepository storeInventoryRepository;

    @InjectMocks
    private DeliveryTimeService deliveryTimeService;

    private String productCode;
    private Long cityId;
    private Store store;
    private Warehouse warehouse;
    private City city;
    private Product product;

    @BeforeEach
    void setUp() {
        productCode = "PROD001";
        cityId = 1L;

        city = new City();
        city.setId(cityId);

        store = new Store();
        store.setId(1L);
        store.setCity(city);

        warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setCity(city);

        product = new Product();
        product.setProductCode(productCode);
    }

    @Test
    void calculateDeliveryTimes_noStoreInCity_throwsNotFoundStoreInCity() {
        // Arrange
        when(storeRepository.findByCityId(cityId)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundStoreInCity exception = assertThrows(NotFoundStoreInCity.class, () -> {
            deliveryTimeService.calculateDeliveryTimes(productCode, cityId);
        });

        assertEquals(cityId, exception.getCity());
    }

    @Test
    void calculateDeliveryTimes_expressAndPickupAvailable_returnsBothShippingTimes() {
        // Arrange
        when(storeRepository.findByCityId(cityId)).thenReturn(Optional.of(store));

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse);
        when(warehouseInventoryRepository.findWareHouseByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(warehouses);

        List<Store> storesWithProduct = new ArrayList<>();
        storesWithProduct.add(store);
        when(storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(storesWithProduct);

        // Act
        TimeResponseDto responseDto = deliveryTimeService.calculateDeliveryTimes(productCode, cityId);

        // Assert
        assertEquals(2, responseDto.getShippingTimes().size());
        assertEquals("Express", responseDto.getShippingTimes().get(0).getMethod());
        assertEquals("PickUp", responseDto.getShippingTimes().get(1).getMethod());
    }

    @Test
    void calculateDeliveryTimes_onlyExpressAvailable_returnsOnlyExpressShippingTime() {
        // Arrange
        when(storeRepository.findByCityId(cityId)).thenReturn(Optional.of(store));
        when(warehouseInventoryRepository.findWareHouseByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(List.of(warehouse));
        when(storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(new ArrayList<>());


        // Act
        TimeResponseDto responseDto = deliveryTimeService.calculateDeliveryTimes(productCode, cityId);

        // Assert
        assertEquals(1, responseDto.getShippingTimes().size());
        assertEquals("Express", responseDto.getShippingTimes().get(0).getMethod());
    }
    @Test
    void calculateDeliveryTimes_onlyPickUpAvailable_returnsOnlyPickUpShippingTime() {
        // Arrange
        when(storeRepository.findByCityId(cityId)).thenReturn(Optional.of(store));
        when(warehouseInventoryRepository.findWareHouseByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(new ArrayList<>());
        when(storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(List.of(store));


        // Act
        TimeResponseDto responseDto = deliveryTimeService.calculateDeliveryTimes(productCode, cityId);

        // Assert
        assertEquals(2, responseDto.getShippingTimes().size());
        assertEquals("Express", responseDto.getShippingTimes().get(0).getMethod());
        assertEquals("PickUp", responseDto.getShippingTimes().get(1).getMethod());
    }

    @Test
    void calculateDeliveryTimes_nearestWarehouseReturnsDeliveryOptions(){
        //Arrange

        when(storeRepository.findByCityId(cityId)).thenReturn(Optional.of(store));
        when(warehouseInventoryRepository.findWareHouseByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(new ArrayList<>());
        when(storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, cityId)).thenReturn(new ArrayList<>());
        List<Warehouse> warehousesWithProduct = new ArrayList<>();
        warehousesWithProduct.add(warehouse);


        WarehouseInventory warehouseInventory = new WarehouseInventory();
        warehouseInventory.setWarehouse(warehouse);
        warehouseInventory.setProduct(product);


        when(warehouseInventoryRepository.findAll()).thenReturn(List.of(warehouseInventory));
        when(storeDeliveryTimeRepository.findByWarehouseOriginIdAndStoreId(anyLong(),anyLong())).thenReturn(new StoreDeliveryTime(1L,warehouse,store,2));

        // Act
        TimeResponseDto responseDto = deliveryTimeService.calculateDeliveryTimes(productCode, cityId);

        // Assert
        assertEquals(2, responseDto.getShippingTimes().size());
        assertEquals("PickUp", responseDto.getShippingTimes().get(0).getMethod());
        assertEquals("Delivery", responseDto.getShippingTimes().get(1).getMethod());
    }
}
