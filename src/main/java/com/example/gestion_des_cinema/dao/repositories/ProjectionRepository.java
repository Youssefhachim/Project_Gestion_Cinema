package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Film;
import com.example.gestion_des_cinema.dao.entities.FilmProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<FilmProjection,Long > {

}
