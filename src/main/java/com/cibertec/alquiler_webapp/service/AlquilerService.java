package com.cibertec.alquiler_webapp.service;

import com.cibertec.alquiler_webapp.model.Alquiler;
import java.util.List;

public interface AlquilerService {
    List<Alquiler> listarTodos();
    void guardar(Alquiler alquiler); // <- ahora devuelve void
    Alquiler buscarPorId(Long id);
    void eliminar(Long id);
    
}
