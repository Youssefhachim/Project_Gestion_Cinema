package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Film;
import com.example.gestion_des_cinema.dao.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface PlaceRepository extends JpaRepository<Place,Long > {
}
