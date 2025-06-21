package com.cibertec.alquiler_webapp.service.impl;

import com.cibertec.alquiler_webapp.model.Pelicula;
import com.cibertec.alquiler_webapp.repository.PeliculaRepository;
import com.cibertec.alquiler_webapp.service.PeliculaService;
import org.springframework.stereotype.Service;

import java.util.List;



//import com.cibertec.alquiler_webapp.model.Pelicula;
//import com.cibertec.alquiler_webapp.repository.PeliculaRepository;
//import com.cibertec.alquiler_webapp.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> listarTodos() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula buscarPorId(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        peliculaRepository.deleteById(id);
    }
    // Implementación del método para actualizar stock porque quiero que se vea al alquilar una pelicula y al eliminar el alquiler
    @Override
    public void actualizarStock(Long idPelicula, int nuevoStock) {
        Pelicula pelicula = buscarPorId(idPelicula);
        if (pelicula != null) {
            pelicula.setStock(nuevoStock);
            peliculaRepository.save(pelicula);
        }
    }
}
