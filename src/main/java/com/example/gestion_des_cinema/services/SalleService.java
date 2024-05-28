package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.repositories.SalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    @Transactional
    public void deleteSalleById(Long id) {
        salleRepository.deleteById(id);
    }
}
