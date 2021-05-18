package com.cfgs.appvetspring.dao;

import com.cfgs.appvetspring.model.Mensaje;

import java.util.List;

public interface MensajeDAO {
    List<Mensaje> findByUserId(Long id); // TODO posterior implementacion
    List<Mensaje> findByTaskId(Long id);
}
