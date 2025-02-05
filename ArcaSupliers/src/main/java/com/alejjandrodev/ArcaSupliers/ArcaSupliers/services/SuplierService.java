package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.CreateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.SuplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuplierService {

    @Autowired
    SuplierRepository repository;

    private List<Supplier> getAll(){
        return repository.findAllByIsActive(true);
    }

    private Supplier get(Long id){
        return repository.findById(id).orElseThrow( /* Excepcion*/);
    }

    private Supplier create(CreateSuplierDto createSuplierDto) {
        Supplier newSuplier = new Supplier();
        newSuplier.setName(createSuplierDto.getName());
        newSuplier.setActive(true);
        newSuplier.setDescription(createSuplierDto.getDescription());
        newSuplier.setEmail(createSuplierDto.getEmail());
        newSuplier.setTelefono(createSuplierDto.getTelefono());
        newSuplier.setSitioWeb(newSuplier.getSitioWeb());
        return repository.save(newSuplier);
    }

    /*

    private  Suplier update (Long id){

    }

    */

    private  String delete(Long id){
        Supplier suplier = this.get(id);

        if (suplier.getFirstPurchase() != null){
            //Lanzar Exception indicado que no se pueden eliminar spluier con venta
        }
        repository.deleteById(id);
        return "Se ha eliminado el proveedor";
    }

    private String registrarVenta(Long id){
        Supplier suplier = this.get(id);
        suplier.setFirstPurchase(new Date());
        repository.save(suplier);
        return "Se ha comprado al proveedor";
    }
}
