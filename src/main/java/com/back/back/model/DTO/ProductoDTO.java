package com.back.back.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class ProductoDTO{

    private Long id;
    private String titulo;
    private CategoriaDTO categoria;
    private CiudadDTO ciudad;
    private Set<CaracteristicaDTO> caracteristicas;
//    private List<ImagenDTO> listImagen;

}
