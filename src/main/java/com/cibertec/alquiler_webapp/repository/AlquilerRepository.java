package com.cibertec.alquiler_webapp.repository;
import com.cibertec.alquiler_webapp.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlquilerRepository     extends JpaRepository<Alquiler, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
