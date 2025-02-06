package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.CreateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.UpdateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Product;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.SupplierContact;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.ProductRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.SupplierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository repository;

    @Autowired
    ProductRepository productRepository;

    public List<Supplier> getAll(){
        return repository.findAllByIsActive(true);
    }

    public Supplier get(Long id){
        return repository.findById(id).orElseThrow( /* Excepcion*/);
    }

    public Supplier create(CreateSuplierDto createSuplierDto) {
        Supplier newSuplier = new Supplier();
        newSuplier.setName(createSuplierDto.getName());
        newSuplier.setIsActive(true);
        newSuplier.setDescription(createSuplierDto.getDescription());
        newSuplier.setEmail(createSuplierDto.getEmail());
        newSuplier.setTelefono(createSuplierDto.getTelefono());
        newSuplier.setSitioWeb(createSuplierDto.getSitioWeb());
        newSuplier.setAddress(createSuplierDto.getAddress());

        /** INTSTACIANDO EL OBJETO**/
        SupplierContact supplierContactNew =  new SupplierContact();
        BeanUtils.copyProperties(createSuplierDto.getContact(), supplierContactNew);

        newSuplier.setContact(supplierContactNew);
        return repository.save(newSuplier);
    }

    public Supplier update(Long id, UpdateSuplierDto updateSuplierDto) {
        Supplier existingSupplier = get(id);

        if (updateSuplierDto.getName() != null) {
            existingSupplier.setName(updateSuplierDto.getName());
        }
        if (updateSuplierDto.getDescription() != null) {
            existingSupplier.setDescription(updateSuplierDto.getDescription());
        }
        if (updateSuplierDto.getEmail() != null) {
            existingSupplier.setEmail(updateSuplierDto.getEmail());
        }
        if (updateSuplierDto.getTelefono() != null) {
            existingSupplier.setTelefono(updateSuplierDto.getTelefono());
        }
        if (updateSuplierDto.getSitioWeb() != null) {
            existingSupplier.setSitioWeb(updateSuplierDto.getSitioWeb());
        }

        return repository.save(existingSupplier);
    }

    public  String delete(Long id){
        Supplier suplier = this.get(id);

        if (suplier.getFirstPurchase() != null){
            //Lanzar Exception indicado que no se pueden eliminar spluier con venta
        }
        repository.deleteById(id);
        return "Se ha eliminado el proveedor";
    }

    public String registrarVenta(Long id){
        Supplier suplier = this.get(id);
        suplier.setFirstPurchase(new Date());
        repository.save(suplier);
        return "Se ha comprado al proveedor";
    }

    public Supplier addProductToSupplier(Long supplierId, Long productId) {
        Optional<Supplier> supplierOptional = repository.findById(supplierId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (supplierOptional.isPresent() && productOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            Product product = productOptional.get();

            supplier.getProducts().add(product);
            return repository.save(supplier);
        } else {
            throw new RuntimeException("Proveedor o producto no encontrado");
        }
    }
}

