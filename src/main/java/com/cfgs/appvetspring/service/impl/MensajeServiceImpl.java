package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.dao.MensajeDAO;
import com.cfgs.appvetspring.model.Mensaje;

import com.cfgs.appvetspring.repository.MensajeRepository;
import com.cfgs.appvetspring.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeServiceImpl implements MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private MensajeDAO mensajeDAO;

    @Override
    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    @Override
    public List<Mensaje> findAllByUserId(Long id) {
        return mensajeDAO.findByUserId(id);
    }

    @Override
    public List<Mensaje> findAllByTaskId(Long id) {
        System.out.println("entro aqui");
        return mensajeDAO.findByTaskId(id);
    }

    @Override
    public Mensaje saveMensaje(Mensaje mess) {
        return mensajeRepository.save(mess);
    }
    @Override
    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }
    @Override
    public void deleteById(Long id) {
        mensajeRepository.deleteById(id);

    }
}
