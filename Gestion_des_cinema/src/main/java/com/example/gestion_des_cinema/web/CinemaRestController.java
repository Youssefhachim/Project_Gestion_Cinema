package com.example.gestion_des_cinema.web;

import com.example.gestion_des_cinema.dao.entities.Film;
import com.example.gestion_des_cinema.dao.entities.TicketPlace;
import com.example.gestion_des_cinema.dao.repositories.FilmRepository;
import com.example.gestion_des_cinema.dao.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {

    /*@Autowired
    private FilmRepository filmRepository;
    @GetMapping("/listFilms")
    public List<Film>films(){
        return  filmRepository.findAll();
    }
    */

    //creation d'une methode qui retourner un tableau des byte
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

        @GetMapping(path ="/imageFilm{id}",produces = MediaType.IMAGE_JPEG_VALUE)
        public byte[] image(@PathVariable (name = "id")Long id) throws Exception {
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
        }

    @PostMapping("/payerTickets")
    @Transactional
    public List<TicketPlace> payerTickets(@RequestBody TicketForm ticketForm){
        List<TicketPlace> ticketList= new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            //System.out.println(idTicket);
            TicketPlace ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;

    }

}
    @Data
    class TicketForm{
        private String nomClient;
        private int codePayement;
        private List<Long> tickets =new ArrayList<>();
    }
