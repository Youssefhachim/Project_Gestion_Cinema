package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.entities.Film;
import com.example.gestion_des_cinema.dao.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    public List<Film> searchFilms(String keyword) {
        if (keyword != null) {
            return filmRepository.findByTitreContainingIgnoreCase(keyword);
        }
        return filmRepository.findAll();
    }
}
