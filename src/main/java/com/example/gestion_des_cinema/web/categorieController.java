package com.example.gestion_des_cinema.web;

import com.example.gestion_des_cinema.dao.entities.Categorie;
import com.example.gestion_des_cinema.dao.repositories.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
class CategorieController{
    private CategoriesRepository categoriesRepository;
    @GetMapping("/categorie")
    public String categories(Model model,
                             @RequestParam(name="page",defaultValue = "0") int page ,
                             @RequestParam(name="size",defaultValue = "5") int size,
                             @RequestParam(name="keyword",defaultValue = "") String keyword) {

        Page<Categorie> PageCategorie;

        // Vérifier si le mot-clé est vide
        if (keyword.isEmpty()) {
            // Réinitialiser la recherche en passant une chaîne vide
            PageCategorie = categoriesRepository.findAll(PageRequest.of(page, size));
        } else {
            // Effectuer la recherche en utilisant le mot-clé fourni
            PageCategorie = categoriesRepository.findByNameContains(keyword, PageRequest.of(page, size));
        }

        model.addAttribute("listCategories", PageCategorie.getContent());
        model.addAttribute("pages", new int[PageCategorie.getTotalPages()]);
        model.addAttribute("PageCurrent", page);
        model.addAttribute("keyword", keyword);

        return "redirect:/categorie?page"+page+"&keyword"+keyword;
    }
    @GetMapping("/delete")
    public String Delete(Long id,String keyword,int page)
    {
        categoriesRepository.deleteById(id);
        return "redirect:/categorie";
    }


}
