package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.dao.UserDao;
import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import com.cfgs.appvetspring.repository.UserRepository;
import com.cfgs.appvetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

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
        System.out.println("estoy aqui");
            Optional<User> user = userRepository.findById(id);
        System.out.println("MMMMM"+user.get().getNombre());

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

    @Override
    public void insertUserTask(Long idUser, Long idTask) {
        userDao.insertUserTask(idUser,idTask);
    }


}
