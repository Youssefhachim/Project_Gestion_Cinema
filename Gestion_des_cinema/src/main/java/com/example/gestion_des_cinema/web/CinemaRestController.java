package com.example.gestion_des_cinema.web;

import com.example.gestion_des_cinema.dao.entities.*;
import com.example.gestion_des_cinema.dao.repositories.*;
import com.example.gestion_des_cinema.services.FilmService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller

public class CinemaRestController {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private FilmService filmService;


    @GetMapping(path = "/images/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        Path imagePath = Paths.get(System.getProperty("user.home") + "/cinema/images/" + filename);
        return Files.readAllBytes(imagePath);
    }

    @GetMapping(path ="/imageFilm{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception {
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Long ville, @RequestParam(required = false) String search) {
        List<Film> films;
        if (search != null && !search.isEmpty()) {
            films = filmRepository.findByTitreContainingIgnoreCase(search);
        } else if (ville != null) {
            Ville selectedVille = villeRepository.findById(ville).orElseThrow(() -> new RuntimeException("Ville not found"));
            films = selectedVille.getCinemas().stream()
                    .flatMap(cinema -> cinema.getSalles().stream())
                    .flatMap(salle -> salle.getFilmProjections().stream())
                    .map(FilmProjection::getFilm)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            films = filmRepository.findAll();
        }
        model.addAttribute("films", films);
        return "index";
    }

    @GetMapping("/villes")
    public String villes(Model model) {
        model.addAttribute("villes", villeRepository.findAll());
        return "villes";
    }

    @PostMapping("/villes")
    public String saveVille(Ville ville) {
        villeRepository.save(ville);
        return "redirect:/villes";
    }

    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        model.addAttribute("villes", villeRepository.findAll());
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "cinemas";
    }

    @PostMapping("/cinemas")
    public String saveCinema(@RequestParam String name, @RequestParam String ville, @RequestParam(required = false) String newCinemaName) {
        String cinemaName = "new".equals(name) ? newCinemaName : name;

        Cinema cinema = new Cinema();
        cinema.setName(cinemaName);
        Ville villeEntity = villeRepository.findById(Long.parseLong(ville)).orElseThrow(() -> new RuntimeException("Ville not found"));
        cinema.setVille(villeEntity);

        cinemaRepository.save(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/films")
    public String films(Model model) {
        model.addAttribute("categories", categoriesRepository.findAll());
        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("salles", salleRepository.findAll());
        return "films";
    }

    @PostMapping("/films")
    public String saveFilm(@RequestParam("titre") String titre,
                           @RequestParam("description") String description,
                           @RequestParam("realisateur") String realisateur,
                           @RequestParam("dateSortie") String dateSortieStr,
                           @RequestParam("duree") int duree,
                           @RequestParam("categorie") Long categorieId,
                           @RequestParam("photo") MultipartFile photo,
                           @RequestParam("salles") List<Long> salleIds) {
        try {
            // Conversion de la date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateSortie = dateFormat.parse(dateSortieStr);

            // Enregistrer le fichier
            if (!photo.isEmpty()) {
                byte[] bytes = photo.getBytes();
                Path path = Paths.get(System.getProperty("user.home") + "/cinema/images/" + photo.getOriginalFilename());
                Files.write(path, bytes);
            }

            // Enregistrer les autres informations du film
            Film film = new Film();
            film.setTitre(titre);
            film.setDescription(description);
            film.setRealisateur(realisateur);
            film.setDateSortie(dateSortie);
            film.setDuree(duree);
            film.setPhoto(photo.getOriginalFilename());

            // Chercher et associer la catégorie
            Categorie categorie = categoriesRepository.findById(categorieId).orElse(null);
            film.setCategorie(categorie);

            // Associer les salles sélectionnées
            Set<Salle> salles = salleRepository.findAllById(salleIds).stream().collect(Collectors.toSet());
            film.setSalles(salles);

            filmRepository.save(film);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/films";
    }

    @GetMapping("/filmDetails")
    public String filmDetails(@RequestParam Long id, Model model) {
        Film selectedFilm = filmRepository.findById(id).orElse(null);
        if (selectedFilm == null) {
            return "redirect:/"; // Rediriger vers la page d'accueil si le film n'est pas trouvé
        }
        model.addAttribute("film", selectedFilm);
        return "filmDetails";
    }

    // Méthode pour la modification d'une ville
    @GetMapping("/editVille")
    public String editVillePage(@RequestParam Long id, Model model) {
        // Récupérer la ville à modifier par son ID depuis le repository
        Ville ville = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville not found"));
        model.addAttribute("ville", ville);
        return "editVilles"; // Assurez-vous que le nom du fichier HTML correspond à celui utilisé ici
    }
    @PostMapping("/updateVille")
    public String updateVille(@RequestParam Long villeId, @RequestParam String nomVille) {
        // Récupérer la ville à mettre à jour par son ID depuis le repository
        Ville ville = villeRepository.findById(villeId)
                .orElseThrow(() -> new RuntimeException("Ville not found"));

        // Mettre à jour le nom de la ville
        ville.setName(nomVille);


        // Sauvegarder les changements dans le repository
        villeRepository.save(ville);

        return "redirect:/villes"; // Rediriger vers la liste des villes après la mise à jour
    }


    // Méthode pour la suppression d'une ville
    @PostMapping("/deleteVille")
    public String deleteVille(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        // Vérifiez s'il existe des contraintes d'intégrité référentielle (par exemple, des cinémas associés à cette ville)
        // Si nécessaire, gérez ces contraintes pour éviter les suppressions involontaires

        // Supprimez la ville par son ID depuis le repository
        villeRepository.deleteById(id);

        // Ajoutez un message de confirmation pour l'afficher sur la page de redirection
        redirectAttributes.addFlashAttribute("successMessage", "La ville a été supprimée avec succès.");

        return "redirect:/villes"; // Redirigez vers la liste des villes après la suppression
    }

    @GetMapping("/editCinema")
    public String editCinemaPage(@RequestParam Long id, Model model) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
        model.addAttribute("cinema", cinema);
        model.addAttribute("villes", villeRepository.findAll());
        return "editCinema"; // Ensure you have an editCinema.html template
    }

    // Update the cinema with new details
    @PostMapping("/updateCinema")
    public String updateCinema(@RequestParam Long cinemaId,
                               @RequestParam String name,
                               @RequestParam Long villeId) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        cinema.setName(name);
        Ville ville = villeRepository.findById(villeId)
                .orElseThrow(() -> new RuntimeException("Ville not found"));
        cinema.setVille(ville);

        cinemaRepository.save(cinema);

        return "redirect:/cinemas";
    }

    @PostMapping("/deleteCinema")
    public String deleteCinema(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        // Supprimez le cinéma par son ID depuis le repository
        cinemaRepository.deleteById(id);

        // Ajoutez un message de confirmation pour l'afficher sur la page de redirection
        redirectAttributes.addFlashAttribute("successMessage", "Le cinéma a été supprimé avec succès.");

        return "redirect:/cinemas"; // Redirigez vers la liste des cinémas après la suppression
    }


}

@Data
class TicketForm {
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}
