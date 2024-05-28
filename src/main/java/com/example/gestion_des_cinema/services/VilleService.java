package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.repositories.VilleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService {
    @Autowired
    private VilleRepository villeRepository;

    @Transactional
    public void deleteVilleById(Long id) {
        villeRepository.deleteById(id);
    }
}
