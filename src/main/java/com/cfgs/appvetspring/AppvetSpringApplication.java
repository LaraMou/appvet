package com.cfgs.appvetspring;

import com.cfgs.appvetspring.model.Estado;
import com.cfgs.appvetspring.model.Mensaje;
import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.service.MensajeService;
import com.cfgs.appvetspring.service.TaskService;
import com.cfgs.appvetspring.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AppvetSpringApplication implements CommandLineRunner {
    final UserService userService;
    final TaskService taskService;
    final MensajeService mensajeService;

    public AppvetSpringApplication(UserService userService, TaskService taskService, MensajeService mensajeService) {
        this.userService = userService;
        this.taskService = taskService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AppvetSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user1 = new User("Test1","testApellido","mlra@gmail.com");
//        User user2 = new User("Test2","testApellido","mlra2@gmail.com");
//        User user3 = new User("Test3","testApellido","mlra3@gmail.com");
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
// TODO: 21/05/2021
//        List<User> users = new ArrayList<>();
//        users.add(new User("Pablo","Prieto","unomas@gmail.com"));
//        users.add(new User("Ana","Torroja","Anaunomas@gmail.com"));
//
//        List<Task> tasks= new ArrayList<>();
//        tasks.add(new Task("Radiografía","Necesidad de Rayos transversal",false, Estado.PENDIENTE,"radiografia",Instant.now(),"Pablo", LocalDate.now()));
//        tasks.add(new Task("Ecografia","Necesidad de Rayos transversal",false, Estado.CURSO,"masa negra",Instant.now(),"Ana", LocalDate.now()));
//        tasks.add(new Task("Radiografía","Necesidad de Rayos transversal",true, Estado.FINALIZADO,"radiografia",Instant.now(),"Pablo", LocalDate.now()));
//
//        tasks.get(1).setUsers(users);
//        users.get(1).setTasks(tasks);
//        userService.saveUser(users.get(1));
//        taskService.saveEtiqueta(tasks.get(1));
////
//
//        Task task1 = new Task("Consulta","Flipy en consulta",false,"pendiente","analítica","Tano",LocalDate.now());
//        Task task2 = new Task("Consulta","Rufo en consulta",false,"pendiente","analítica","Tano",LocalDate.now());
//        Task task3 = new Task("Consulta","Pepito en consulta",false,"pendiente","analítica","Tano",LocalDate.now());
//        taskService.saveEtiqueta(task1);
//        taskService.saveEtiqueta(task2);
//        taskService.saveEtiqueta(task3);
//
//        List<Mensaje> mensajes = new ArrayList<>();
//        mensajes.add(new Mensaje("radiografia"));
//        mensajes.add(new Mensaje("analitica"));
//        mensajes.add(new Mensaje("cirugia"));
//        for (int i = 0; i <tasks.size(); i++) {
//            mensajes.get(i).setTask(task1);
//            mensajeService.saveMensaje(mensajes.get(i));
//
//        }


    }
}
