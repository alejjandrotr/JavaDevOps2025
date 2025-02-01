package com.alejjandrodev.arca.category.ArcaCartegory.reposotories;

import com.alejjandrodev.arca.category.ArcaCartegory.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}
