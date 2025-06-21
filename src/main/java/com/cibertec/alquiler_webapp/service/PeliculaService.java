package com.cibertec.alquiler_webapp.service;

import com.cibertec.alquiler_webapp.model.Pelicula;
import java.util.List;

public interface PeliculaService {
    List<Pelicula> listarTodos();
    Pelicula guardar(Pelicula pelicula);
    Pelicula buscarPorId(Long id);
    void eliminar(Long id);

    // Nuevo método: actualizar stock necesario para pelicula
    void actualizarStock(Long idPelicula, int nuevoStock);
}
