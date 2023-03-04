package com.back.back.controller;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Ciudad;
import com.back.back.model.DTO.CiudadDTO;
import com.back.back.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    private CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @PostMapping
    public ResponseEntity<Ciudad> registrarCiudad (@RequestBody CiudadDTO ciudadDTO) throws BadRequestException {
        return ResponseEntity.ok(ciudadService.agregarCiudad(ciudadDTO));
    }


    @PutMapping
    public ResponseEntity<String> actualizarCiudad (@RequestBody Ciudad ciudad) throws BadRequestException {

        Optional<Ciudad> ciudadBuscada = ciudadService.buscarCiudad(ciudad.getId());

        if (ciudadBuscada.isPresent()){
            ciudadService.actualizarCiudad(ciudad);
            return ResponseEntity.ok("Se actualizó la ciudad con " + "titulo " + ciudad.getNombre_ciudad());
        } else {
            return ResponseEntity.badRequest().body("La ciudad con titulo " + ciudad.getNombre_ciudad() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarCiudad(){
        return ResponseEntity.ok(ciudadService.listaCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarCiudadPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Ciudad> ciudadBuscado = ciudadService.buscarCiudad(id);

        if (ciudadBuscado.isPresent()){
            return ResponseEntity.ok(ciudadBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Ciudad> ciudadBuscada = ciudadService.buscarCiudad(id);

        if (ciudadBuscada.isPresent()){
            ciudadService.eliminarCiudad(id);
            return ResponseEntity.ok("Se elimino la ciudad con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
