package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Task;
import com.cfgs.appvetspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public Iterable<Task> findAll();
    public Page<Task> findAll(Pageable pageable);

    public List<Task> findAllTasksByEstado(String estado);

    public List<Task> findAllTasksByFinish(Boolean finish);
    public Optional<Task> findById(Long id);

    public Task saveEtiqueta(Task task);

    public void deleteById(Long id);

    public List<Task> findTaskByUser(Long id);
    public void deleteTaskById(Long id);
}
