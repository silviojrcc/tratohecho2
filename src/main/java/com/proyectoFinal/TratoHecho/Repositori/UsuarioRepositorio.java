/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.TratoHecho.Repositori;

import com.proyectoFinal.TratoHecho.Entidades.Enum.Rol;
import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    @Query("Select u from Usuario u where u.username = :name  ")
    public Usuario buscarUsuario(@Param("name") String username);
    
    @Query("Select u from Usuario u where u.rol = 'TRABAJADOR'")
    public List<Usuario> listarTrabajadores();
    
    @Query("Select u from Usuario u where u.Profesion = :profesion")
    public List<Usuario> listarTrabajadoresPorProfesion(@Param("profesion") String profesion);
}
