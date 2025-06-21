package com.cibertec.alquiler_webapp.controller;
import com.cibertec.alquiler_webapp.model.Cliente;
import com.cibertec.alquiler_webapp.model.Pelicula;
import com.cibertec.alquiler_webapp.model.Alquiler;
import com.cibertec.alquiler_webapp.model.DetalleAlquiler;
import com.cibertec.alquiler_webapp.model.EstadoAlquiler;
import com.cibertec.alquiler_webapp.service.AlquilerService;
import com.cibertec.alquiler_webapp.service.ClienteService;
import com.cibertec.alquiler_webapp.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("listaPeliculas", peliculaService.listarTodos());
        model.addAttribute("estados", EstadoAlquiler.values());
        return "alquiler/formulario";
    }

    @PostMapping("/guardar")
public String guardar(@Valid @ModelAttribute("alquiler") Alquiler alquiler,
                      BindingResult result,
                      Model model) {

    if (result.hasErrors()) {
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("listaPeliculas", peliculaService.listarTodos());
        model.addAttribute("estados", EstadoAlquiler.values());
        return "alquiler/formulario";
    }

    // Calcular el total del alquiler
    BigDecimal total = BigDecimal.ZERO;

    if (alquiler.getDetalles() != null) {
        for (var detalle : alquiler.getDetalles()) {
            var pelicula = peliculaService.buscarPorId(detalle.getPelicula().getId());

            // Validar que exista la película
            if (pelicula != null) {
                 detalle.setPelicula(pelicula); // asegura que esté gestionado
                detalle.setPrecioUnitario(pelicula.getPrecio());
                detalle.setSubtotal(pelicula.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())));
                detalle.setAlquiler(alquiler); // Muy importante para JPA
                total = total.add(detalle.getSubtotal());
            }
        }
    }

    alquiler.setMontoTotal(total);
    alquilerService.guardar(alquiler);
    return "redirect:/alquileres";
}


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alquileres", alquilerService.listarTodos());
        return "alquiler/lista";
    }

    @GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    Alquiler alquiler = alquilerService.buscarPorId(id);
    if (alquiler == null) {
        return "redirect:/alquileres";
    }

    

    model.addAttribute("alquiler", alquiler);
    model.addAttribute("listaClientes", clienteService.listarTodos());
    model.addAttribute("listaPeliculas", peliculaService.listarTodos());
    model.addAttribute("estados", EstadoAlquiler.values());

    return "alquiler/formulario"; // Asegúrate de que este archivo exista en templates/alquiler/
}
         @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        alquilerService.eliminar(id);
        return "redirect:/alquileres";
    }
}
