package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.repositories.CinemaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Transactional
    public void deleteCinemaById(Long id) {
        cinemaRepository.deleteById(id);
    }
}
