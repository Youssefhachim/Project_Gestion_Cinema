package com.example.gestion_des_cinema;

import com.example.gestion_des_cinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionDesCinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInitService iCinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(GestionDesCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();


    }
}
