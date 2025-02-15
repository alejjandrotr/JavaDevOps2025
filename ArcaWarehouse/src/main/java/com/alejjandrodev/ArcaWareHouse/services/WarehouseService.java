package com.alejjandrodev.ArcaWareHouse.services;

import com.alejjandrodev.ArcaWareHouse.dtos.CreateWarehouseDto;
import com.alejjandrodev.ArcaWareHouse.dtos.ProductQuantityDTO;
import com.alejjandrodev.ArcaWareHouse.dtos.WarehouseDeliveryTimeDTO;
import com.alejjandrodev.ArcaWareHouse.dtos.WarehouseUpdateDTO;
import com.alejjandrodev.ArcaWareHouse.entities.City;
import com.alejjandrodev.ArcaWareHouse.entities.Warehouse;
import com.alejjandrodev.ArcaWareHouse.entities.WarehouseDeliveryTime;
import com.alejjandrodev.ArcaWareHouse.entities.WarehouseInventory;
import com.alejjandrodev.ArcaWareHouse.errors.CityNoFoundException;
import com.alejjandrodev.ArcaWareHouse.errors.ProductInWarehoueNotFoundException;
import com.alejjandrodev.ArcaWareHouse.errors.WarehouseNotFoundException;
import com.alejjandrodev.ArcaWareHouse.repositories.*;
import com.alejjandrodev.ArcaWareHouse.utils.loggerWriter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WarehouseDeliveryTimeRepository deliveryTimeRepository;

    @Autowired
    private WarehouseInventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private loggerWriter logger; // Inject the logger


    public List<Warehouse> findAll() {
        logger.info("Finding all warehouses", null);
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> findById(Long id) {
        logger.info("Finding warehouse by ID", id);
        return warehouseRepository.findById(id);
    }

    public Warehouse save(CreateWarehouseDto warehouseDTO) {
        logger.info("Saving warehouse", warehouseDTO);
        Optional<City> city = cityRepository.findById(warehouseDTO.getCityId());
        if (city.isEmpty()){
            CityNoFoundException exception = new CityNoFoundException(warehouseDTO.getCityId());
            logger.error("City not found while saving warehouse", exception);
            throw exception;
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setCity(city.get()); // Asignar ciudad por ID
        warehouse.setWarehouseType(warehouseDTO.getWarehouseType());
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        logger.info("Warehouse saved successfully", savedWarehouse);
        return savedWarehouse;
    }

    public Warehouse update(Long id, WarehouseUpdateDTO updateDTO) {
        logger.info("Updating warehouse with ID", id);
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> {
            WarehouseNotFoundException exception = new WarehouseNotFoundException(id);
            logger.error("Warehouse not found while updating", exception);
            return exception;
        });

        if (updateDTO.getCityId() != null) {
            Optional<City> city = cityRepository.findById(updateDTO.getCityId());
            if (city.isEmpty()){
                CityNoFoundException exception = new CityNoFoundException(updateDTO.getCityId());
                logger.error("City not found while updating warehouse", exception);
                throw exception;
            }
            warehouse.setCity(city.get());
        }
        if (updateDTO.getWarehouseType() != null) {
            warehouse.setWarehouseType(updateDTO.getWarehouseType());
        }
        if (updateDTO.getWarehouseName() != null) {
            warehouse.setWarehouseName(updateDTO.getWarehouseName());
        }

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);
        logger.info("Warehouse updated successfully", updatedWarehouse);
        return updatedWarehouse;
    }

    public void delete(Long id) {
        logger.info("Deleting warehouse with ID", id);
        try {
            warehouseRepository.deleteById(id);
            logger.info("Warehouse deleted successfully", id);
        } catch (Exception e) {
            logger.error("Error deleting warehouse", e);
            throw e;
        }
    }



    @Transactional
    public WarehouseDeliveryTime createWarehouseDeliveryTime(
            Long warehouseOriginId, Long warehouseDestinationId, WarehouseDeliveryTimeDTO deliveryTimeDTO) {

        logger.info("Creating warehouse delivery time", deliveryTimeDTO);
        Warehouse warehouseOrigin = warehouseRepository.findById(warehouseOriginId)
                .orElseThrow(() -> {
                    WarehouseNotFoundException exception = new WarehouseNotFoundException(warehouseOriginId);
                    logger.error("Warehouse origin not found", exception);
                    return exception;
                });

        Warehouse warehouseDestination = warehouseRepository.findById(warehouseDestinationId)
                .orElseThrow(() -> {
                    WarehouseNotFoundException exception = new WarehouseNotFoundException(warehouseDestinationId);
                    logger.error("Warehouse destination not found", exception);
                    return exception;
                });

        WarehouseDeliveryTime deliveryTime = new WarehouseDeliveryTime();
        deliveryTime.setWarehouseOrigin(warehouseOrigin);
        deliveryTime.setWarehouseDestination(warehouseDestination);
        deliveryTime.setDeliveryTimeHours(deliveryTimeDTO.getDeliveryTimeHours());

        WarehouseDeliveryTime savedDeliveryTime = deliveryTimeRepository.save(deliveryTime);
        logger.info("Warehouse delivery time created successfully", savedDeliveryTime);
        return savedDeliveryTime;
    }




    public ProductQuantityDTO getProductQuantity(String productCode, Long warehouseId) {
        logger.info("Getting product quantity",  String.format("productCode: %s, warehouseId: %d", productCode, warehouseId));
        // Verificar si el almacén existe
        warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> {
                    WarehouseNotFoundException exception = new WarehouseNotFoundException(warehouseId);
                    logger.error("Warehouse not found", exception);
                    return exception;
                });

        // Buscar el inventario del producto en el almacén
        WarehouseInventory inventory = inventoryRepository.findByProduct_ProductCodeAndWarehouse_Id(productCode, warehouseId)
                .orElseThrow(() -> {
                    ProductInWarehoueNotFoundException exception = new ProductInWarehoueNotFoundException(productCode,  warehouseId);
                    logger.error("Product not found in warehouse", exception);
                    return exception;
                });

        // Crear y devolver el DTO con la información
        ProductQuantityDTO quantityDTO = new ProductQuantityDTO(productCode, warehouseId, inventory.getQuantity());
        logger.info("Product quantity retrieved successfully", quantityDTO);
        return quantityDTO;
    }
}
