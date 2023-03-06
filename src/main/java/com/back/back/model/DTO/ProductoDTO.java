package com.back.back.model.DTO;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductoDTO{

    private Long id;
    private String titulo;
    private CategoriaDTO categoria;
    private CiudadDTO ciudad;
    private Set<CaracteristicaDTO> caracteristicas;
    private Set<ImagenDTO> setImagen;

}
