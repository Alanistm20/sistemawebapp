package com.cibertec.alquiler_webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "alquileres")
@EqualsAndHashCode(exclude = "alquileres")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un correo electrónico válido")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore // Para evitar loops infinitos en respuestas JSON
    private List<Alquiler> alquileres;
}
