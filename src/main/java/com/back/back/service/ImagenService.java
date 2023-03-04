package com.back.back.service;
import com.back.back.exception.BadRequestException;
import com.back.back.exception.ResourceNotFoundException;
import com.back.back.model.DTO.ImagenDTO;
import com.back.back.model.Imagen;
import com.back.back.repository.ImagenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    ObjectMapper mapper;
    private
    ImagenRepository imagenRepository;

    private static final Logger LOGGER = Logger.getLogger(ImagenService.class);

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public Imagen agregarImagen(ImagenDTO imagenDTO) {
        LOGGER.info("Se inicio una operacion de guardado de la imagen con titulo " + imagenDTO.getTitulo());
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class);
        return imagenRepository.save(imagen);
    }

    public Imagen actualizarImagen(Imagen imagen) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de imagen con ID= " + imagen.getId());
        return imagenRepository.save(imagen);
    }

    public Optional<Imagen> buscarImagen(Long id) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de busqueda de imagen con ID " + id);
        return imagenRepository.findById(id);
    }


    public List<Imagen> listaImagen(){
        LOGGER.info("Se inicio una operacion de listado de imagenes ");
        return imagenRepository.findAll();
    }

    public void eliminarImagen(Long id) throws ResourceNotFoundException,BadRequestException {
        Optional<Imagen> imagenAEliminar = buscarImagen(id);
        if (imagenAEliminar.isPresent()){
            imagenRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de imagen con id " + id);
        }
        else {

            throw new ResourceNotFoundException("La imagen a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }
}
