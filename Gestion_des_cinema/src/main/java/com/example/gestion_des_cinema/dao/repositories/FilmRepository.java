package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Categorie;
import com.example.gestion_des_cinema.dao.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface FilmRepository extends JpaRepository<Film,Long >{
}