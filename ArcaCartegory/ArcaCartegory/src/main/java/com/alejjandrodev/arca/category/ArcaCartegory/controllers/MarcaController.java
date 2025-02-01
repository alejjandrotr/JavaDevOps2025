package com.alejjandrodev.arca.category.ArcaCartegory.controllers;

import com.alejjandrodev.arca.category.ArcaCartegory.dtos.CreateMarcaDto;
import com.alejjandrodev.arca.category.ArcaCartegory.entities.Marca;
import com.alejjandrodev.arca.category.ArcaCartegory.enums.Paises;
import com.alejjandrodev.arca.category.ArcaCartegory.reposotories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarcaController {

    @Autowired
    MarcaRepository repository;

    @GetMapping("/marca")
    public Page<Marca> all(Pageable pageable){
        return repository.findAll(pageable);
    }

    @GetMapping("/marca/findByPais")
    public Page<Marca> serchByCountry(Pageable pageable, @RequestParam Paises pais){
        return repository.findByPaisOrigen(pais, pageable);
    }

    @PostMapping("/marca")
    public Marca create(@RequestBody CreateMarcaDto dto){
        Marca newMarca = new Marca();
        newMarca.setName(dto.getName());
        newMarca.setDescripcion(dto.getDescripcion());
        newMarca.setLogo(dto.getLogo());
        newMarca.setPaisOrigen(dto.getPaisOrigen());
        return repository.save(newMarca);
    }
}
