package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Mock
    User user = new User();
    @Mock
    UserRepository userRepository;
    @Test
    void create() {

        user.setNombre("Pablo");
        user.setApellido("Robles Tris");
        user.setEmail("pbr@gmail.com");
        userRepository.save(user);
        System.out.println(">>>>>>>>user"+user.getId());
        assertNotNull(user.getId());

    }

    @Test
    void finById() {
    }
}
