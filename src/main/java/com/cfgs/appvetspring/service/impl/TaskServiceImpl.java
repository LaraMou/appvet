package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.dao.TaskDao;
import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.repository.TaskRepository;
import com.cfgs.appvetspring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDao taskDao;
    @Override
    public List<Task> findAll() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> findAllTasksByEstado(String estado) {
        return taskDao.findAllTasksByEstado(estado);
    }

    @Override
    public List<Task> findAllTasksByFinish(Boolean finish) {
        return taskDao.findAllTaskByFinish(finish);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveEtiqueta(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findTaskByUser(Long id) {
        return taskDao.findTaskByUser(id);
    }
}
