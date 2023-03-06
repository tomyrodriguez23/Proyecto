package com.back.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String icono;
    @JsonIgnore
    @ManyToMany(mappedBy = "caracteristicas", fetch = FetchType.LAZY)
    private Set<Producto> productosSet = new HashSet<>();

//    public void setProductosSet(Set<Producto> productosSet) {
//        this.productosSet = productosSet;
//        this.productosSet.stream().peek(producto -> producto.setCaracteristicas(this)).collect(Collectors.toSet());
//    }
}
