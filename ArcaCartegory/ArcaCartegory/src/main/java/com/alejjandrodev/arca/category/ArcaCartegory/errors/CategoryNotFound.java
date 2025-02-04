package com.alejjandrodev.arca.category.ArcaCartegory.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFound extends ResponseStatusException {
    public CategoryNotFound(Long id) {
        super(HttpStatus.NOT_FOUND, "Error: Category with id "+ id + " not found");
    }
}
