package com.back.back.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    private Ciudad ciudad;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(
            name = "productos_caracteristicas",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    private Set<Caracteristica> caracteristicas = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "productos_caracteristicas",
//            joinColumns = @JoinColumn(name = "id_producto"),
//            inverseJoinColumns = @JoinColumn(name = "id_caracteristica")
//    )
//    private Set<Caracteristica> caracteristicas = new HashSet<>();

//    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Imagen> listImagen = new ArrayList<>();

}
