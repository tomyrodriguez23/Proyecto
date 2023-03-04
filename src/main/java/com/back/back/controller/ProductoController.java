package com.back.back.controller;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.DTO.ProductoDTO;
import com.back.back.model.Producto;
import com.back.back.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> registrarProducto (@RequestBody ProductoDTO productoDTO) throws BadRequestException {
        return ResponseEntity.ok(productoService.agregarProducto(productoDTO));
    }


    @PutMapping
    public ResponseEntity<String> actualizarProducto (@RequestBody Producto producto) throws BadRequestException {
        Optional<Producto> productoBuscado = productoService.buscarProducto(producto.getId());
        if (productoBuscado.isPresent()){
            productoService.actualizarProducto(producto);
            return ResponseEntity.ok("Se actualizó la producto con " + "titulo " + producto.getTitulo());
        } else {
            return ResponseEntity.badRequest().body("La producto con titulo " + producto.getTitulo() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return ResponseEntity.ok(productoService.listaProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Producto> productoBuscado = productoService.buscarProducto(id);

        if (productoBuscado.isPresent()){
            return ResponseEntity.ok(productoBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Producto> productoBuscada = productoService.buscarProducto(id);

        if (productoBuscada.isPresent()){
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Se elimino la producto con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
