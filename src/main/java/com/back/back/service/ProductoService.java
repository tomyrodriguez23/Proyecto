package com.back.back.service;

import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Caracteristica;
import com.back.back.model.Categoria;
import com.back.back.model.DTO.ProductoDTO;
import com.back.back.model.Producto;
import com.back.back.repository.CaracteristicaRepository;
import com.back.back.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public Producto agregarProducto(ProductoDTO productoDTO) throws BadRequestException {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Producto producto) throws BadRequestException{
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProducto(Long id) throws BadRequestException{
        return productoRepository.findById(id);
    }
    public List<Producto> listaProducto(){
        return productoRepository.findAll();
    }

    public void eliminarProducto(Long id) throws ResourceNotFoundException, BadRequestException {
        Optional<Producto> productoAEliminar = buscarProducto(id);
        if (productoAEliminar.isPresent()){
            productoRepository.deleteById(id);
        }
        else {

            throw new ResourceNotFoundException("La producto a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }

//    public Producto productoDtoAProducto(ProductoDTO productoDTO) throws BadRequestException {
//        Producto producto = new Producto();
//        Optional<Categoria> categoria = categoriaService.buscarCategoria(productoDTO.getCategoria());
//        producto.setTitulo(productoDTO.getTitulo());
//        producto.setCategoria(categoria.get());
//        return producto;
//    }

}
