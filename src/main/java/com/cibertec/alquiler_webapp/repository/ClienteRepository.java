package com.cibertec.alquiler_webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.alquiler_webapp.model.Cliente;
// Al extender JpaRepository<Estudiante, Long>, obtenemos todo el CRUD para la entidad Estudiante.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
