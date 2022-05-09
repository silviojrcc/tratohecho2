/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.TratoHecho.Controller;

import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import com.proyectoFinal.TratoHecho.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    public UsuarioServicio usuarioServicio;

    @GetMapping("")
    public String registro() {
        return "registro";

    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2,
            @RequestParam("rol") String rol,
            @RequestParam("email") String correoElectronico,
            @RequestParam("telefono") String numeroDeTelefono,
            @RequestParam("profesion") String profesion,
            MultipartFile archivo,
            ModelMap modelo) {
        //no se como hacer para poner una foto en caso de que llegue vacio el archivo
        //if (archivo.isEmpty()) {
          //  System.out.println("No hay foto, no se por que");
        //}
        
        System.out.println("usuario: " + username);
        System.out.println("rol: " + rol);
        System.out.println("Mail: " + correoElectronico);


        //}
        try {
            usuarioServicio.registrarUsuario(username, password, password2, rol, correoElectronico, numeroDeTelefono, profesion, archivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelo.addAttribute("username", username);
            modelo.addAttribute("password", password);
            modelo.addAttribute("password2", password2);
            modelo.addAttribute("error", ex.getMessage());
            return "registro";
        }
        
        modelo.put("titulo", "Bienvenido a Trato Hecho!");
        modelo.put("descripcion", "Tu usuario " + username + " se creó con éxito");

        return "exito.html";
        
    }

}
