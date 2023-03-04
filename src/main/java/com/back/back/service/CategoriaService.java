package com.back.back.service;

import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Categoria;
import com.back.back.model.DTO.CategoriaDTO;
import com.back.back.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CategoriaService.class);

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }



    public Categoria agregarCategoria(CategoriaDTO categoriaDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de guardado de la categoria con titulo " + categoriaDTO.getTitulo());
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class );
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Categoria categoria) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de categoria con ID= " + categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarCategoria(Long id) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de busqueda de categoria con ID " + id);
        return categoriaRepository.findById(id);
    }


    public List<Categoria> listaCategoria(){
        LOGGER.info("Se inicio una operacion de listado de categorias ");
        return categoriaRepository.findAll();
    }

    public void eliminarCategoria(Long id) throws ResourceNotFoundException,BadRequestException {
        Optional<Categoria> categoriaAEliminar = buscarCategoria(id);
        if (categoriaAEliminar.isPresent()){
            categoriaRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de categoria con id " + id);
        }
        else {

            throw new ResourceNotFoundException("La categoria a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }
    }


}
