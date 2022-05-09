/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.TratoHecho.Controller;

import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import com.proyectoFinal.TratoHecho.Repositori.UsuarioRepositorio;
import com.proyectoFinal.TratoHecho.Servicios.ContratacionServicio;
import com.proyectoFinal.TratoHecho.Servicios.UsuarioServicio;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ContratacionServicio contratacionServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    //@PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_OFFICEADMIN','ROLE_EMPLOYEE')") 
    @PreAuthorize("hasAnyRole('ROLE_TRABAJADOR', 'ROLE_CLIENTE')")
    @GetMapping("")
    public String inicio() {
        return "usuario.html";
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil(@RequestParam String id, ModelMap model) {
        try {
            Usuario usuario = usuarioServicio.buscarUId(id);
            model.addAttribute("perfil", usuario);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "editar-perfil";
    }

    @GetMapping("/contratar")
    public String contratacion(@RequestParam("idTrabajador") String idTrabajador,
            Principal principal,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession,
            ModelMap modelo) {

        try {
            System.out.println("hola");
            if (principal == null) {
                System.out.println("la persona no está autenticada");
                redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para poder contratar un trabajador!");
                return "redirect:/login";
            }

            Usuario usuario = (Usuario) httpSession.getAttribute("usuario");

            contratacionServicio.contratar(usuario.getId(), idTrabajador, " ");

            return "index";
        } catch (Exception ex) {
            
            modelo.addAttribute("error", ex.getMessage());
            System.out.println("error: " + ex.getMessage());
            System.out.println("error: " + ex.getMessage());

            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/categoria/trabajadores";
        }

    }

}
