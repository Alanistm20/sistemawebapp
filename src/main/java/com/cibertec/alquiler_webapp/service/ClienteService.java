package com.cibertec.alquiler_webapp.service;

import com.cibertec.alquiler_webapp.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodos();
    Cliente guardar(Cliente cliente);
    Cliente buscarPorId(Long id);
    void eliminar(Long id);
}
