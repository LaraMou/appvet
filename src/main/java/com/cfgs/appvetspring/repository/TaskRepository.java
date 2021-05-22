package com.cfgs.appvetspring.repository;


import com.cfgs.appvetspring.model.Estado;
import com.cfgs.appvetspring.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {


}
