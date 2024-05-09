package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CategoriesRepository extends JpaRepository<Categorie,Long> {

    Page<Categorie> findByNameContains(String keyword, PageRequest of);
}
