package com.alejjandrodev.arca.category.ArcaCartegory.controllers;

import com.alejjandrodev.arca.category.ArcaCartegory.dtos.CreateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.UpdateCategoryDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class CategoryController {

    @RequestMapping(value = "/")
    public String HolaMundo(){
        return "Hola Mundo";
    }

    @GetMapping(value = "/category/{id}")
    public CreateCategoryDto get(@PathVariable(value = "id") int id){
        System.out.println("Category " + id );
        return new CreateCategoryDto("Camara Web", "Aca hay camara webs", "imagen");
    }

    @GetMapping(value = "/category/search")
    public List<CreateCategoryDto> search(@RequestParam(value = "nombre") String name){
        LinkedList<CreateCategoryDto> list = new LinkedList<>();
        list.add(new CreateCategoryDto("Camara Web", "Aca hay camara webs", "imagen"));
        list.add(new CreateCategoryDto("Monitores", "Aca hay Monitores", "imagen"));
        list.add(new CreateCategoryDto("Ratones", "Aca hay ratones", "imagen"));
        return  list;
    }

    @PostMapping(value = "/category")
    public CreateCategoryDto create(@Valid @RequestBody() CreateCategoryDto category){
        category.setNombre(category.getNombre() + " Guardado");
        return category;
    }


    @PatchMapping(value = "/category/{id}")
    public CreateCategoryDto update(@PathVariable(value = "id") int id, @Valid @RequestBody() UpdateCategoryDto category){
        CreateCategoryDto mockCreatedCategory = new CreateCategoryDto("Camara Web", "Aca hay camara webs", "imagen");

        if (category.getNombre() != null)
            mockCreatedCategory.setNombre(category.getNombre() );
        if (category.getImagen() != null)
            mockCreatedCategory.setImagen(category.getImagen());
        if (category.getDescripcion() != null)
            mockCreatedCategory.setDescripcion(category.getDescripcion());

        return mockCreatedCategory;
    }


    @DeleteMapping(value = "/category/{id}")
    public String delete(@PathVariable(value = "id") int id){
        System.out.println("Category " + id );
        return "Se ha eliminado una categoria";
    }
}
