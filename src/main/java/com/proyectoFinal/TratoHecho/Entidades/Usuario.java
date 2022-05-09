/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoFinal.TratoHecho.Entidades;

import com.proyectoFinal.TratoHecho.Entidades.Enum.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data

public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String direccion;
    private String numeroDeTelefono;
    private String correoElectronico;
    private String Profesion ;
    
    @OneToOne
    private Foto foto;
                

}
