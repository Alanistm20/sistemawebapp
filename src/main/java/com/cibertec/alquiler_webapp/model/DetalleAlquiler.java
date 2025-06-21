package com.cibertec.alquiler_webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(DetalleAlquilerId.class)
public class DetalleAlquiler implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_alquiler",  nullable = false)
    private Alquiler alquiler;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pelicula",  nullable = false)
    private Pelicula pelicula;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private BigDecimal subtotal;
}
