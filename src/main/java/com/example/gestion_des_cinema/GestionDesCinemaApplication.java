package com.example.gestion_des_cinema;

import com.example.gestion_des_cinema.dao.entities.Client;
import com.example.gestion_des_cinema.services.ClientManager;
import com.example.gestion_des_cinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionDesCinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInitService iCinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(GestionDesCinemaApplication.class, args);
    }

    @Autowired
    ClientManager clientManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    /*    iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();

     */
        Client admin = clientManager.findByUsername("admin");
        if (admin == null) {
            Client client1 = new Client(null, "admin", passwordEncoder.encode("admin"), "admin");
            clientManager.addClient(client1);
        }
        Client user = clientManager.findByUsername("user");
        if (user == null) {
            Client user1 = new Client(null, "user", passwordEncoder.encode("user"), "user");
            clientManager.addClient(user1);
        }
    }
}

