package com.cibertec.alquiler_webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.alquiler_webapp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
