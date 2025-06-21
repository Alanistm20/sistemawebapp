
package com.cibertec.alquiler_webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaAlquiler;

    private LocalDate fechaDevolucion;
    private BigDecimal montoTotal; //usare bigdecimal para manejar el dinero

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)//esto para que no sea nulo
    private EstadoAlquiler estado;

 @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleAlquiler> detalles = new ArrayList<>();
}
