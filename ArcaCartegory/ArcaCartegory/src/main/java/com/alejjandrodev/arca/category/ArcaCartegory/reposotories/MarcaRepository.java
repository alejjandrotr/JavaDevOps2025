package com.alejjandrodev.arca.category.ArcaCartegory.reposotories;

import com.alejjandrodev.arca.category.ArcaCartegory.entities.Marca;
import com.alejjandrodev.arca.category.ArcaCartegory.enums.Paises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Page<Marca> findAll(Pageable pageable);
    Page<Marca> findByPaisOrigen(Paises pais, Pageable pageable);
}
