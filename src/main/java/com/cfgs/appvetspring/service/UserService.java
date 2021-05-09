package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User finById(Long id);

    public User saveUser(User user);

    public void deleteById(Long id);
}
