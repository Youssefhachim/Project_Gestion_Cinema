package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Categorie;
import com.example.gestion_des_cinema.dao.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface CinemaRepository extends JpaRepository<Cinema,Long> {
    List<Cinema> findByVilleId(Long villeId);
}
