package com.alejjandrodev.ArcaSupliers.ArcaSupliers.controllers;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.CreateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.UpdateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody CreateSuplierDto createSuplierDto) {
        Supplier newSupplier = supplierService.create(createSuplierDto);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAll();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.get(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSuplierDto updateSuplierDto) {
        Supplier updatedSupplier = supplierService.update(id, updateSuplierDto);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/product-add/{idProduct}")
    public ResponseEntity<Supplier> addProductToSupplier(@PathVariable Long id, @PathVariable Long idProduct) {
        Supplier updatedSupplier = supplierService.addProductToSupplier(id, idProduct);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }
}
