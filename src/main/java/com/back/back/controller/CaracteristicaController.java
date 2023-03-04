package com.back.back.controller;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Caracteristica;
import com.back.back.model.DTO.CaracteristicaDTO;
import com.back.back.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    private CaracteristicaService caracteristicaService;

    @Autowired
    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @PostMapping
    public ResponseEntity<Caracteristica> registrarCaracteristica (@RequestBody CaracteristicaDTO caracteristicaDTO) throws BadRequestException {
        return ResponseEntity.ok(caracteristicaService.agregarCaracteristica(caracteristicaDTO));
    }

    @PutMapping
    public ResponseEntity<String> actualizarCaracteristica (@RequestBody Caracteristica caracteristica) throws BadRequestException {

        Optional<Caracteristica> caracteristicaBuscada = caracteristicaService.buscarCaracteristica(caracteristica.getId());

        if (caracteristicaBuscada.isPresent()){
            caracteristicaService.actualizarCaracteristica(caracteristica);
            return ResponseEntity.ok("Se actualiza la caracteristica con " + "nombre " + caracteristica.getNombre());
        } else {
            return ResponseEntity.badRequest().body("La caracteristica con nombre " + caracteristica.getNombre() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Caracteristica>> listarCaracteristica(){
        return ResponseEntity.ok(caracteristicaService.listaCaracteristica());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caracteristica> buscarCaracteristicaPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Caracteristica> caracteristicaBuscada = caracteristicaService.buscarCaracteristica(id);

        if (caracteristicaBuscada.isPresent()){
            return ResponseEntity.ok(caracteristicaBuscada.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Caracteristica> caracteristicaBuscada = caracteristicaService.buscarCaracteristica(id);

        if (caracteristicaBuscada.isPresent()){
            caracteristicaService.eliminarCaracteristica(id);
            return ResponseEntity.ok("Se elimino la caracteristica con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
