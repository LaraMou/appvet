package com.cfgs.appvetspring.controller;


import com.cfgs.appvetspring.model.Mensaje;
import com.cfgs.appvetspring.model.Mensaje;
import com.cfgs.appvetspring.service.MensajeService;
import com.cfgs.appvetspring.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MensajeController {

    private final Logger log = LoggerFactory.getLogger(MensajeController.class);

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * metodo que crea un mensaje en la BD
     * @param mensaje
     * @return ResponseEntity<Mensaje>
     * @throws URISyntaxException
     */
    @PostMapping("/mensaje")
    public ResponseEntity<Mensaje> createTask(@RequestBody Mensaje mensaje) throws URISyntaxException {
        log.debug("Create Mensaje");
        Mensaje resultado=null;
        if (mensaje.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=mensajeService.saveMensaje(mensaje);
        return  ResponseEntity.created(new URI("/api/mensaje/"+resultado.getId())).body(resultado);
    }

    /**
     * metodo para modificar una mensaje de la BD
     * @param mensaje
     * @return ResponseEntity<Mensaje>
     */
    @PutMapping(value = "/mensaje")
    public ResponseEntity<Mensaje> modifyTask(@RequestBody Mensaje mensaje) {
        log.debug("Modify Mensaje");
        if (mensaje.getId()==null) {
            log.error("Error en Modify Mensaje");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Mensaje resultado = mensajeService.saveMensaje(mensaje);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Metodo que devuelve una lista con todas la tareas
     * @return List<Mensaje>
     */
    @GetMapping("/mensaje")
    @ApiOperation("Encontrar todas las tareas sin paginación")
    public List<Mensaje> findAllMensajes(){
        log.debug("Rest request all Mensaje");
        return mensajeService.findAll();
    }

    /**
     * metodo que filtra una mensaje filtrando por su ID
     * @param id Long
     * @return ResponseEntity<Mensaje>
     */
    @GetMapping("/mensaje/{id}")
    @ApiOperation("Encontrar  mensaje por id")
    public ResponseEntity<Mensaje> findOneMensaje(@ApiParam("id long")@PathVariable Long id) {
        log.debug("Rest request a Mensaje with id: ",id);
        Optional<Mensaje> mensajeOpt = mensajeService.findById(id);
        if (mensajeOpt.isPresent())
            return ResponseEntity.ok().body(mensajeOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo que devuelve una lista de mensajes filtrando por tareas
     * @param id Long id
     * @return
     */
    @GetMapping("/mensaje/task/{id}")
    @ApiOperation("Encontrar  mensaje por tarea")
    public ResponseEntity<List<Mensaje>> findAllTaskByFinish(@ApiParam("Busqueda por idtarea")@PathVariable Long id) {
        log.debug("Rest request a Mensaje: "+id);
        List<Mensaje> mensaje=mensajeService.findAllByTaskId(id);
        if (!mensaje.isEmpty())
            return ResponseEntity.ok().body(mensaje);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Metodo que devuelve una lista de mensaje filtrando por usuario
     * @param id Long id
     * @return
     */
    @GetMapping("/mensaje/user/{id}")
    @ApiOperation("Encontrar  mensaje por tarea")
    public ResponseEntity<List<Mensaje>> findAllByUser(@ApiParam("Busqueda por idtarea")@PathVariable Long id) {
        log.debug("Rest request a Mensaje: "+id);
        List<Mensaje> mensaje=mensajeService.findAllByUserId(id);
        if (!mensaje.isEmpty())
            return ResponseEntity.ok().body(mensaje);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Request para el borrado de tareas por id
     * @param id Long
     * @return httpStatus.ok
     */
    @DeleteMapping("/mensaje/{id}")
    @ApiOperation("Borrado de mensaje")
    public ResponseEntity<?> delete(@ApiParam("Identficador id")@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Mensaje> taskOpt = mensajeService.findById(id);
            if (taskOpt.isPresent())
                mensajeService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el mensaje de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El mensaje eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
