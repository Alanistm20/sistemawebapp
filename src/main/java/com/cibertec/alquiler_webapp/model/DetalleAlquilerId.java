package com.cibertec.alquiler_webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAlquilerId implements Serializable {
 private Long alquiler;
private Long pelicula;

}
