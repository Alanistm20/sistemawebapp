package com.cibertec.alquiler_webapp.controller;

import com.cibertec.alquiler_webapp.model.Cliente;
import com.cibertec.alquiler_webapp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Mostrar todos los clientes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "cliente/lista"; // Asegúrate de tener cliente/lista.html
    }

    // Formulario para nuevo cliente
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/formulario"; // Asegúrate de tener cliente/formulario.html
    }

    // Guardar cliente (POST)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cliente/formulario";
        }
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    // Editar cliente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "cliente/formulario";
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}
