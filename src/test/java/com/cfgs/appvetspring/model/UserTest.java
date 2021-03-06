package com.cfgs.appvetspring.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserTest {


    @Test
    @DisplayName("Creación Usuario")
    void create() {
        User user = new User();
        user.setNombre("Pablo");
        user.setApellido("Robles Tris");
        user.setEmail("pbr@gmail.com");
        user.setFoto("url");
        user.setId(1L);
        List<Task> tasks= new ArrayList<>();
//        tasks.add(new Task("Rayo","Pancreas",false,"pendiente","Ecografi", Instant.now(),"Pablo"));
//        tasks.add(new Task("Rayo","Pancreas",false,"pendiente","Ecografi", Instant.now(),"Pablo"));
//        tasks.add(new Task("Rayo","Pancreas",false,"pendiente","Ecografi", Instant.now(),"Pablo"));
//        user.setTasks(tasks);
//        assertEquals("Pablo",user.getNombre());
//        assertEquals("Robles Tris",user.getApellido());
//        assertEquals("pbr@gmail.com",user.getEmail());
//        assertEquals("url",user.getFoto());
//        assertEquals(1L, user.getId());
//        assertEquals(3, user.getTasks().size());



    }
}
