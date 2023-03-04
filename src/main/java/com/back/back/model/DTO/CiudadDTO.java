package com.back.back.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class CiudadDTO {

    private Long id;
    private String nombre_ciudad;
    private String nombre_pais;

}
