package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public List<Task> findAll();

    public List<Task> findAllByEstado(String estado);


    public Optional<Task> findById(Long id);

    public Task saveEtiqueta(Task task);

    public void deleteById(Long id);
}
