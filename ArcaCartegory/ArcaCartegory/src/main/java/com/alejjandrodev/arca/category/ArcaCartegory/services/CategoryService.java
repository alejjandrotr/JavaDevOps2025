package com.alejjandrodev.arca.category.ArcaCartegory.services;

import com.alejjandrodev.arca.category.ArcaCartegory.dtos.CreateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.ProductoWebDto;
import com.alejjandrodev.arca.category.ArcaCartegory.dtos.UpdateCategoryDto;
import com.alejjandrodev.arca.category.ArcaCartegory.entities.Category;
import com.alejjandrodev.arca.category.ArcaCartegory.errors.CategoryNotFound;
import com.alejjandrodev.arca.category.ArcaCartegory.reposotories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRespository;

    @Autowired
    RestTemplate restTemplate;

    public Category create(CreateCategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setName(categoryDto.getNombre());
        newCategory.setImagen(categoryDto.getImagen());
        newCategory.setDescription(categoryDto.getDescripcion());
        newCategory.setActiveSince(new Date());
        return categoryRespository.save(newCategory);
    }

    public List<Category> getAll(){
        return categoryRespository.findAll();
    }

    public Category get(Long id){
        return categoryRespository.findById(id).orElseThrow(() -> new CategoryNotFound(id));
    }

    public List<Category> getByName(String name){
        return categoryRespository.findByName(name);
    }

    public Category update(Long id, UpdateCategoryDto categoryDto){

        Category toUpdateCategory = this.get(id);

        if (categoryDto.getNombre() != null)
            toUpdateCategory.setName(categoryDto.getNombre() );
        if (categoryDto.getImagen() != null)
            toUpdateCategory.setImagen(categoryDto.getImagen());
        if (categoryDto.getDescripcion() != null)
            toUpdateCategory.setDescription(categoryDto.getDescripcion());

        return categoryRespository.save(toUpdateCategory);
    }

    public  String delete(Long id){
        Category toDeleteCategory = this.get(id);
        categoryRespository.deleteById(id);
        return "Se ha eliminado una categoria";
    }

    public List<ProductoWebDto> getProducts(){
        return List.of(
                restTemplate.getForObject("https://my-json-server.typicode.com/alejjandrotr/JavaDevOps2025/products", ProductoWebDto[].class));
    }


}
