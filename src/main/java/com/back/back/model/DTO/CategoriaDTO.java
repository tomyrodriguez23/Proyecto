package com.back.back.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class CategoriaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;

}
