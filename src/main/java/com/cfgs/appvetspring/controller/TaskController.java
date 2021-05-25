package com.cfgs.appvetspring.controller;


import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//@CrossOrigin(origins = { "http://localhost:4200" })
@CrossOrigin(origins = { "https://appvetfront-b3j0hxlv4-laramou.vercel.app" },methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
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
        return (List<Task>) taskService.findAll();
    }
    @GetMapping("/tasks/page/{page}")
    @ApiOperation("Encuentra todos las tareas con paginación")
    public Page<Task> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return taskService.findAll(pageable);
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
    @GetMapping("/tasks/estado/{estado}")
    @ApiOperation("Encontrar  tareas por estado")
    public ResponseEntity<List<Task>> findAllByEstado(@ApiParam("Busqueda por estado Estado")@PathVariable String estado) {
        log.debug("Rest request a Task with estado: "+estado);
        List<Task> tasks=taskService.findAllTasksByEstado(estado);
        if (!tasks.isEmpty())
            return ResponseEntity.ok().body(tasks);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    /**
     * Metodo que devuelve una lista de tareas filtrando por finalizar
     * @param finish Booleano
     * @return Lista de tareas completadas
     */
    @GetMapping("/tasks/completa/{finish}")
    @ApiOperation("Encontrar  tareas completadas")
    public ResponseEntity<List<Task>> findAllTaskByFinish(@ApiParam("Busqueda por finalizado Booleano")@PathVariable Boolean finish) {
        log.debug("Rest request a Task with finish: "+finish);
        List<Task> tasks=taskService.findAllTasksByFinish(finish);
        if (!tasks.isEmpty())
            return ResponseEntity.ok().body(tasks);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Método para recuperar tareas asociadas a un usuario
     * @param id Long
     * @return devuelve una lista
     */

    @GetMapping("/tasks/user/{id}")
    @ApiOperation("Encontrar  tareas por usuario")
    public ResponseEntity<List<Task>> findTaskByUser(@PathVariable Long id){
        log.debug("Rest request All cuentas with user id: "+ id );
        List<Task> taskList = taskService.findTaskByUser(id);
        if(taskList!= null){
            return ResponseEntity.ok().body(taskList);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Request para el borrado de tareas por id
     * @param id Long
     * @return httpStatus.ok
     */
    @DeleteMapping("/tasks/{id}")
    @ApiOperation("Borrado de tarea")
    public ResponseEntity<?> delete(@ApiParam("Identficador id")@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Task> taskOpt = taskService.findById(id);
            if (taskOpt.isPresent())
                taskService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la tarea de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Tarea eliminada con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    /**
     * Request para el borrado de tareas por id
     * @param id Long
     * @return httpStatus.ok
     */
    @DeleteMapping("/tasks/user/{id}")
    @ApiOperation("Borrado de tarea")
    public ResponseEntity<?> deleteTaskUser(@ApiParam("Identficador id")@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Task> taskOpt = taskService.findById(id);
            if (taskOpt.isPresent())
                taskService.deleteTaskById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la tarea de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Tarea eliminada con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
