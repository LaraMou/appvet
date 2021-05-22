package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.repository.UserRepository;
import com.cfgs.appvetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Recupera todos los usuarios
     * @return Lista
     */
    @Override
    public List<User> findAll() {
       return  userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User finById(Long id) {

            return userRepository.findById(id).orElse(null);

    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
