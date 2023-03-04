package com.back.back.controller;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Categoria;
import com.back.back.model.DTO.CategoriaDTO;
import com.back.back.model.DTO.ProductoDTO;
import com.back.back.model.Producto;
import com.back.back.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> registrarCategoria (@RequestBody CategoriaDTO categoriaDTO) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoriaDTO));
    }


    @PutMapping
    public ResponseEntity<String> actualizarCategoria (@RequestBody Categoria categoria) throws BadRequestException {

        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(categoria.getId());

        if (categoriaBuscada.isPresent()){
            categoriaService.actualizarCategoria(categoria);
            return ResponseEntity.ok("Se actualizó la categoria con " + "titulo " + categoria.getTitulo());
        } else {
            return ResponseEntity.badRequest().body("La categoria con titulo " + categoria.getTitulo() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listaCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(id);

        if (categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {
        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(id);
        if (categoriaBuscada.isPresent()){
            categoriaService.eliminarCategoria(id);
            return ResponseEntity.ok("Se elimino la categoria con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
