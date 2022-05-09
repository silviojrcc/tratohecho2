package com.proyectoFinal.TratoHecho.Repositori;

import com.proyectoFinal.TratoHecho.Entidades.Contratacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratacionRepositorio extends JpaRepository<Contratacion, String>{
    
}
