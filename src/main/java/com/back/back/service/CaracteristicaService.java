package com.back.back.service;

import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.Caracteristica;
import com.back.back.model.DTO.CaracteristicaDTO;
import com.back.back.repository.CaracteristicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CaracteristicaService {

    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CaracteristicaService.class);

    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }


    public Caracteristica agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws BadRequestException {
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class);
        LOGGER.info("Se inicio una operacion de guardado de la caracteristica con nombre " + caracteristicaDTO.getNombre());
        return caracteristicaRepository.save(caracteristica);
    }

    public Caracteristica actualizarCaracteristica(Caracteristica caracteristica) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de caracteristica con ID= " + caracteristica.getId());
        return caracteristicaRepository.save(caracteristica);
    }

    public Optional<Caracteristica> buscarCaracteristica(Long id) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de busqueda de caracteristica con ID " + id);
        return caracteristicaRepository.findById(id);
    }


    public List<Caracteristica> listaCaracteristica(){
        LOGGER.info("Se inicio una operacion de listado de caracteristicas ");
        return caracteristicaRepository.findAll();
    }

    public void eliminarCaracteristica(Long id) throws ResourceNotFoundException, BadRequestException {
        Optional<Caracteristica> caracteristicaAEliminar = buscarCaracteristica(id);
        if (caracteristicaAEliminar.isPresent()){
            caracteristicaRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de caracteristica con id " + id);
        }
        else {

            throw new ResourceNotFoundException("La caracteristica a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }
}
