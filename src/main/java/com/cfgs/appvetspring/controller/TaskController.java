package com.cfgs.appvetspring.controller;


import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.Task;
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
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * metodo que crea una tarea en la BD
     * @param task
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        log.debug("Create Task");
        Task resultado=null;
        if (task.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=taskService.saveEtiqueta(task);
        return  ResponseEntity.created(new URI("/api/tasks/"+resultado.getId())).body(resultado);
    }

    /**
     * metodo para modificar una tarea de la BD
     * @param task
     * @return ResponseEntity<Task>
     */
    @PutMapping(value = "/tasks")
    public ResponseEntity<Task> modifyTask(@RequestBody Task task) {
        log.debug("Modify Task");
        if (task.getId()==null) {
            log.error("Error en Modify Task");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Task resultado = taskService.saveEtiqueta(task);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Metodo que devuelve una lista con todas la tareas
     * @return List<Task>
     */
    @GetMapping("/tasks")
    @ApiOperation("Encontrar todas las tareas sin paginación")
    public List<Task> findAllTasks(){
        log.debug("Rest request all Task");
        return taskService.findAll();
    }

    /**
     * metodo que filtra una tarea filtrando por su ID
     * @param id Long
     * @return ResponseEntity<Task>
     */
    @GetMapping("/tasks/{id}")
    @ApiOperation("Encontrar  tarea por id")
    public ResponseEntity<Task> findOneTask(@ApiParam("id long")@PathVariable Long id) {
        log.debug("Rest request a Task with id: ",id);
        Optional<Task> taskOpt = taskService.findById(id);
        if (taskOpt.isPresent())
            return ResponseEntity.ok().body(taskOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo que devuelve una lista de tareas filtrando por finalizar
     * @param estado String estado
     * @return
     */
    @GetMapping("/tasks/finalizada/{estado}")
    @ApiOperation("Encontrar  tareas por estado")
    public ResponseEntity<List<Task>> findAllTaskByFinish(@ApiParam("Busqueda por estado Booleano")@PathVariable String estado) {
        log.debug("Rest request a Task with finish: "+estado);
        List<Task> tasks=taskService.findAllByEstado(estado);
        if (!tasks.isEmpty())
            return ResponseEntity.ok().body(tasks);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Request para el borrado de tareas por id
     * @param id Long
     * @return httpStatus.ok
     */
    @DeleteMapping("/task/{id}")
    @ApiOperation("Borrado de tarea")
    public ResponseEntity<?> delete(@ApiParam("Identficador id")@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Task> taskOpt = taskService.findById(id);
            if (taskOpt.isPresent())
                taskService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el user de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El user eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
