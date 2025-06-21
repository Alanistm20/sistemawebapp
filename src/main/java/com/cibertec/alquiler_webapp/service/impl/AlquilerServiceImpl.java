package com.cibertec.alquiler_webapp.service.impl;

import com.cibertec.alquiler_webapp.model.Alquiler;
import com.cibertec.alquiler_webapp.model.DetalleAlquiler;
import com.cibertec.alquiler_webapp.model.Pelicula;
import com.cibertec.alquiler_webapp.repository.AlquilerRepository;
import com.cibertec.alquiler_webapp.service.AlquilerService;
import com.cibertec.alquiler_webapp.service.PeliculaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private PeliculaService peliculaService;

    @Override
    public List<Alquiler> listarTodos() {
        return alquilerRepository.findAll();
    }

    @Transactional
    @Override
    public void guardar(Alquiler alquiler) {
        if (alquiler.getDetalles() != null && !alquiler.getDetalles().isEmpty()) {
            for (DetalleAlquiler detalle : alquiler.getDetalles()) {
                detalle.setAlquiler(alquiler);

                Pelicula pelicula = detalle.getPelicula();

                if (pelicula != null) {
                    int cantidadSolicitada = detalle.getCantidad();

                    // Validar stock suficiente
                    if (pelicula.getStock() < cantidadSolicitada) {
                        throw new IllegalArgumentException("No hay stock suficiente para la película: " + pelicula.getTitulo());
                    }

                    // Descontar stock
                    pelicula.setStock(pelicula.getStock() - cantidadSolicitada);
                    peliculaService.guardar(pelicula); // Guardar nuevo stock
                }
            }
        }

        alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler buscarPorId(Long id) {
        return alquilerRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        Alquiler alquiler = alquilerRepository.findById(id).orElse(null);

        if (alquiler != null && alquiler.getDetalles() != null) {
            for (DetalleAlquiler detalle : alquiler.getDetalles()) {
                Pelicula pelicula = detalle.getPelicula();
                if (pelicula != null) {
                    // Restaurar el stock
                    pelicula.setStock(pelicula.getStock() + detalle.getCantidad());
                    peliculaService.guardar(pelicula); // Guardar el nuevo stock
                }
            }
            alquilerRepository.delete(alquiler); // Finalmente eliminar el alquiler
        }
    }
}
