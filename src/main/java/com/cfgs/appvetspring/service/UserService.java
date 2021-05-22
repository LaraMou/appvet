package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public Page<User> findAll(Pageable pageable);
    public User finById(Long id);

    public User saveUser(User user);

    public void deleteById(Long id);
}
