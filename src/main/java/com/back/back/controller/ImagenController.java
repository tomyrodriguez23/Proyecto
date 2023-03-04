package com.back.back.controller;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.DTO.ImagenDTO;
import com.back.back.model.Imagen;
import com.back.back.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    private ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping
    public ResponseEntity<Imagen> registrarImagen (@RequestBody ImagenDTO imagenDTO) throws BadRequestException {
        return ResponseEntity.ok(imagenService.agregarImagen(imagenDTO));
    }


    @PutMapping
    public ResponseEntity<String> actualizarImagen (@RequestBody Imagen imagen) throws BadRequestException {

        Optional<Imagen> imagenBuscada = imagenService.buscarImagen(imagen.getId());

        if (imagenBuscada.isPresent()){
            imagenService.actualizarImagen(imagen);
            return ResponseEntity.ok("Se actualizó la imagen con " + "titulo " + imagen.getTitulo());
        } else {
            return ResponseEntity.badRequest().body("La imagen con titulo " + imagen.getTitulo() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Imagen>> listarImagen(){
        return ResponseEntity.ok(imagenService.listaImagen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> buscarImagenPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Imagen> imagenBuscado = imagenService.buscarImagen(id);

        if (imagenBuscado.isPresent()){
            return ResponseEntity.ok(imagenBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Imagen> imagenBuscada = imagenService.buscarImagen(id);

        if (imagenBuscada.isPresent()){
            imagenService.eliminarImagen(id);
            return ResponseEntity.ok("Se elimino la imagen con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
