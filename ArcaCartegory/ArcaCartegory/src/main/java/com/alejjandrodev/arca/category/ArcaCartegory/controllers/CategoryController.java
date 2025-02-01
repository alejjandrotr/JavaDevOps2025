package com.alejjandrodev.arca.category.ArcaCartegory.controllers;

import com.alejjandrodev.arca.category.ArcaCartegory.dtos.CreateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.UpdateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.entities.Category;
import com.alejjandrodev.arca.category.ArcaCartegory.reposotories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @RequestMapping(value = "/category/")
    public List<Category> all(){
        return this.repository.findAll();
    }

    @GetMapping(value = "/category/{id}")
    public Category get(@PathVariable(value = "id") Long id){
        System.out.println("Category " + id );
        return this.repository.findById(id).get();
    }

    @GetMapping(value = "/category/search")
    public List<Category> search(@RequestParam(value = "nombre") String name){
        return  this.repository.findByName(name);
    }

    @PostMapping(value = "/category")
    public Category create(@Valid @RequestBody() CreateCategoryDto category){
        Category newCategory = new Category();
        newCategory.setName(category.getNombre());
        newCategory.setActiveSince(new Date());
        newCategory.setDescription(category.getDescripcion());
        newCategory.setImagen(category.getImagen());

        return this.repository.save(newCategory);
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
    public String delete(@PathVariable(value = "id") Long id){
        this.repository.deleteById(id);
        return "Se ha eliminado una categoria";
    }
}
