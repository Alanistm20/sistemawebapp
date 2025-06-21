package com.cibertec.alquiler_webapp.controller;

import com.cibertec.alquiler_webapp.model.Pelicula;
import com.cibertec.alquiler_webapp.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("peliculas", peliculaService.listarTodos());
        return "pelicula/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "pelicula/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Pelicula pelicula, BindingResult result) {
        if (result.hasErrors()) {
            return "pelicula/formulario";
        }
        peliculaService.guardar(pelicula);
        return "redirect:/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Pelicula pelicula = peliculaService.buscarPorId(id);//para buscar las peliculas
        if (pelicula == null) {
            return "redirect:/peliculas";
        }
        model.addAttribute("pelicula", pelicula);
        return "pelicula/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        peliculaService.eliminar(id);
        return "redirect:/peliculas";
    }
}

