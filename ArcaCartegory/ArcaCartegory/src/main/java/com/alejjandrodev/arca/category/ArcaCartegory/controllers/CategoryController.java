package com.alejjandrodev.arca.category.ArcaCartegory.controllers;

import com.alejjandrodev.arca.category.ArcaCartegory.dtos.CreateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.ProductoWebDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.UpdateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.entities.Category;
import com.alejjandrodev.arca.category.ArcaCartegory.reposotories.CategoryRepository;
import com.alejjandrodev.arca.category.ArcaCartegory.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
        CategoryService service;

    @RequestMapping(value = "/category/")
    public List<Category> all(){
        return service.getAll();
    }

    @GetMapping(value = "/category/{id}")
    public Category get(@PathVariable(value = "id") Long id){
        System.out.println("Category " + id );
        return service.get(id);
    }

    @GetMapping(value = "/category/search")
    public List<Category> search(@RequestParam(value = "nombre") String name){
        return  this.service.getByName(name);
    }

    @GetMapping(value = "/category/products")
    public List<ProductoWebDto> getProduct(){
        return service.getProducts();
    }

    @PostMapping(value = "/category")
    public Category create(@Valid @RequestBody() CreateCategoryDto category){
        return this.service.create(category);
    }


    @PatchMapping(value = "/category/{id}")
    public Category update(@PathVariable(value = "id") Long id, @Valid @RequestBody() UpdateCategoryDto category){
        return service.update(id, category);
    }


    @DeleteMapping(value = "/category/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }


}
