package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitreContainingIgnoreCase(String titre);
    List<Film> findByCategorieId(Long categorieId);
}
