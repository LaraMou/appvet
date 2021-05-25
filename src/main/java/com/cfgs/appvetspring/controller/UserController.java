package com.cfgs.appvetspring.controller;

import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.service.IUploadFileService;
import com.cfgs.appvetspring.service.TaskService;
import com.cfgs.appvetspring.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins = { "http://localhost:4200" })
@CrossOrigin(origins = { "https://appvetfront-b3j0hxlv4-laramou.vercel.app" },methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private IUploadFileService uploadService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    @ApiOperation("Encuentra todos los empleados sin paginación")
    public List<User> index() {
        return userService.findAll();
    }
    @GetMapping("/users/page/{page}")
    @ApiOperation("Encuentra todos los empleados con paginación")
    public Page<User> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return userService.findAll(pageable);
    }


    @GetMapping("/users/{id}")

    @ApiOperation("Encuentra un empleado por su id")
    public ResponseEntity<?> show(@ApiParam("Clave primaria del empleado en formato Long")@PathVariable Long id) {

        User user = null;
        Map<String, Object> response = new HashMap<>();

        try {
            user = userService.finById(id);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(user == null) {
            response.put("mensaje", "El user ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

  
    @PostMapping("/users")
    @ApiOperation("Creación  de usuarios")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

        User userNew = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            userNew = userService.saveUser(user);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El user ha sido creado con éxito!");
        response.put("user", userNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    @ApiOperation("Modificación  de usuarios")
    public ResponseEntity<User> modifyUser(@RequestBody User user) {
        log.debug("Modify Usuario");
        if (user.getId()==null) {
            log.error("Error en Modify Usuario");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User resultado = userService.saveUser(user);
        return ResponseEntity.ok().body(resultado);
    }

    @PutMapping("/users/{id}")
    @ApiOperation("Actualización de Usuario")
    public ResponseEntity<?> update(@ApiParam("Usuario por id")@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        System.out.println("entro en usuario");
        User usert = user;
        System.out.println(usert.getId());
        System.out.println("y el id es "+id);
        userService.insertUserTask(usert.getId(),id);
        User userActual = userService.finById(id);

        User userUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (userActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el user ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            userActual.setApellido(user.getApellido());
            userActual.setNombre(user.getNombre());
//            userActual.setEmail(user.getEmail());



            userUpdated = userService.saveUser(userActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el user en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El user ha sido actualizado con éxito!");
        response.put("user", userUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation("Borrado de usuario por id")
    public ResponseEntity<?> delete(@ApiParam("Id long" )@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            User user = userService.finById(id);
           // String nombreFotoAnterior = user.getFoto();

            //uploadService.eliminar(nombreFotoAnterior);

            userService.deleteById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el user de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El user eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    @PostMapping("/users/upload")
    @ApiOperation("Subida archivos  de usuario por id")
    public ResponseEntity<?> upload(@ApiParam("Id long,  multipartFile subida archivo" )@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();
        System.out.println("user"+id);
        User user = userService.finById(id);

        if(!archivo.isEmpty()) {

            String nombreArchivo = null;
            try {
                System.out.println("nombreArchivo"+nombreArchivo + archivo);

                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del user");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = user.getFoto();

            uploadService.eliminar(nombreFotoAnterior);

            user.setFoto(nombreArchivo);

            userService.saveUser(user);

            response.put("user", user);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

        Resource recurso = null;

        try {
            recurso = uploadService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
