/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.TratoHecho.Controller;

import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import com.proyectoFinal.TratoHecho.Servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("")
    public String Index(@RequestParam(name = "profesion", required = false) String profesion, Model model) {

//        List<Usuario> trabajadores = new ArrayList<>();
//        if (profesion != null) {
//            trabajadores = usuarioServicio.listarTrabajadoresPorProfesion(profesion);
//        } else {
//            trabajadores = usuarioServicio.ListarTrabajadores();
//        }
//        
//        model.addAttribute("trabajadores",trabajadores);
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Usted se ha desloogeado correctamentee");
        }
        return "login.html";
    }

}
