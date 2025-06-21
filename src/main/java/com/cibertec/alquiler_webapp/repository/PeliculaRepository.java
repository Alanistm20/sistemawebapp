package com.cibertec.alquiler_webapp.repository;
import com.cibertec.alquiler_webapp.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
}
