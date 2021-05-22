package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public Iterable<Task> findAll();

    public List<Task> findAllTasksByEstado(String estado);

    public List<Task> findAllTasksByFinish(Boolean finish);
    public Optional<Task> findById(Long id);

    public Task saveEtiqueta(Task task);

    public void deleteById(Long id);

    public List<Task> findTaskByUser(Long id);
}
