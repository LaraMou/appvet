package com.cfgs.appvetspring.repository;


import com.cfgs.appvetspring.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MensajeRepository extends CrudRepository<Mensaje,Long>, JpaRepository<Mensaje,Long> {
}
