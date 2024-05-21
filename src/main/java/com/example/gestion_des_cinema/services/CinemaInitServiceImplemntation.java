package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.entities.*;
import com.example.gestion_des_cinema.dao.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Transactional
@Service
public class CinemaInitServiceImplemntation implements ICinemaInitService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;





    @Transactional
    @Override
    public void initVilles() {
        /*
        Stream.of("casablanca","Marrakech","Rabat","Tanger","Fes","Agadir").forEach(villeName->{
                Ville ville=new Ville();
                ville.setId(null);
                ville.setName(villeName);
                villeRepository.save(ville);
                });

         */
    }

    @Transactional
    @Override
    public void initCinemas() {
        /*
        villeRepository.findAll().forEach(v->{
            Stream.of("MegaRama","IMax","Founoun","Chahrazad","Daouliz").forEach(nameCinema->{
                Cinema cinema=new Cinema();
                cinema.setName(nameCinema);
                cinema.setNombre_salle(3+(int)(Math.random()*7));
                cinema.setVille(v);
                cinemaRepository.save(cinema);
            });
        });

         */
    }


    @Transactional
    @Override
    public void initSalles() {
        /*
        cinemaRepository.findAll().forEach(cinema->{
            for (int i=0;i< cinema.getNombre_salle();i++)
            {
                Salle salle=new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15+(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });

         */

    }



    @Transactional
    @Override
    public void initPlaces() {
        /*
        salleRepository.findAll().forEach(salle->{
            for (int i=0;i<salle.getNombrePlace();i++){
                Place place=new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

         */
    }

    @Transactional
    @Override
    public void initSeances() {
        /*
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","14:00","16:00","17:00","19:00","21:00").forEach(s->{
            Seance seance = new Seance();

            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        });

         */

    }

    @Transactional
    @Override
    public void initCategories() {
        /*
        Stream.of("history","Action","Fiction","Drama","history","Action","Fiction","Drama","history","Action","Fiction","Drama").forEach(cat->{
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categoriesRepository.save(categorie);
        });

         */
    }

    @Transactional
    @Override
    public void initFilms() {
        /*
        double[] durees = new double[]{1, 1.5, 2, 2.5, 3};
        List<Categorie> categories = categoriesRepository.findAll();
        Stream.of("Game of thrones", "THE 1000", "Seigneur des anneaux", "Spider man", "IRON Man", "cat women").forEach(titreFilm -> {
            Film film = new Film();
            film.setTitre(titreFilm);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(titreFilm.replaceAll(" ", "")+".jpg");
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });

         */

    }

    @Transactional
    @Override
    public void initProjections() {
        /*
        double[] prices=new double[]{30,50,60,70,80,90,100};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle ->{
                    filmRepository.findAll().forEach(film->{
                        seanceRepository.findAll().forEach(seance -> {
                            FilmProjection projection=new FilmProjection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });

         */
    }

    @Transactional
    @Override
    public void initTickets() {
        /*
        projectionRepository.findAll().forEach(t->{
            t.getSalle().getPlaces().forEach(place -> {
                TicketPlace ticket=new TicketPlace();
                ticket.setPlace(place);
                ticket.setPrix(t.getPrix());
                ticket.setFilmProjection(t);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });

         */
    }
}
