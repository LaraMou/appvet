package com.cfgs.appvetspring.service;

import com.cfgs.appvetspring.model.Mensaje;
import com.cfgs.appvetspring.model.Task;

import java.util.List;
import java.util.Optional;

public interface MensajeService {
    public List<Mensaje> findAll();

    public List<Mensaje> findAllByUserId(Long id);


    public List<Mensaje> findAllByTaskId(Long id);

    Optional<Mensaje> findById(Long id);

    public Mensaje saveMensaje(Mensaje mess);

    public void deleteById(Long id);

}
