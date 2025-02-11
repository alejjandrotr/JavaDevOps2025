package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.controllers;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.City;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Warehouse;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.NotFoundStoreInCity;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryTimeControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseInventoryRepository warehouseInventoryRepository;

    @Autowired
    private StoreInventoryRepository storeInventoryRepository;

    @Test
    void getDeliveryTime_storeExists_returnsPickupShippingTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deliveryTime")
                        .param("productCode", "PROD001")
                        .param("city", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingTimes", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingTimes[0].method").value("Express"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingTimes[1].method").value("PickUp"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingTimes[2].method").value("Delivery"));
    }



}
