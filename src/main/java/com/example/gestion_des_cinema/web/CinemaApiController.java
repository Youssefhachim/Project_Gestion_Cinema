package com.example.gestion_des_cinema.web;

import com.example.gestion_des_cinema.dao.entities.Cinema;
import com.example.gestion_des_cinema.dao.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaApiController {
    @Autowired
    private CinemaRepository cinemaRepository;
    @GetMapping("/cinemas")
    public List<Cinema> getCinemasByVille(@RequestParam Long ville) {
        return cinemaRepository.findByVilleId(ville);
    }
}
