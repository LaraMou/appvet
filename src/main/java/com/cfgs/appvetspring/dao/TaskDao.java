package com.cfgs.appvetspring.dao;



import com.cfgs.appvetspring.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    List<Task> findAllTaskByFinish(Boolean finish);
    List<Task> findByEstado(String estado);

    List<Task> findAllTasks();

    List<Task> findTaskByUser(Long id);
    Optional<Task> findTaskByID(Long id);

}
