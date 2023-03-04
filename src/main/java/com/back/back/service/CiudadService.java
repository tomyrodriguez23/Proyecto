package com.back.back.service;

import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Ciudad;
import com.back.back.model.DTO.CiudadDTO;
import com.back.back.repository.CiudadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CiudadService {
    private CiudadRepository ciudadRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CiudadService.class);

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Ciudad agregarCiudad(CiudadDTO ciudadDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de guardado de la ciudad con titulo " + ciudadDTO.getNombre_ciudad());
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        return ciudadRepository.save(ciudad);
    }

    public Ciudad actualizarCiudad(Ciudad ciudad) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de ciudad con ID= " + ciudad.getId());
        return ciudadRepository.save(ciudad);
    }

    public Optional<Ciudad> buscarCiudad(Long id) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de busqueda de ciudad con ID " + id);
        return ciudadRepository.findById(id);
    }


    public List<Ciudad> listaCiudad(){
        LOGGER.info("Se inicio una operacion de listado de ciudades ");
        return ciudadRepository.findAll();
    }

    public void eliminarCiudad(Long id) throws ResourceNotFoundException,BadRequestException {
        Optional<Ciudad> ciudadAEliminar = buscarCiudad(id);
        if (ciudadAEliminar.isPresent()){
            ciudadRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de ciudad con id " + id);
        }
        else {

            throw new ResourceNotFoundException("La ciudad a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }
}
