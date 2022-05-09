package com.proyectoFinal.TratoHecho.Controller;

import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import com.proyectoFinal.TratoHecho.Servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    public UsuarioServicio usuarioServicio;
    
    @PreAuthorize("hasAnyRole('ROLE_TRABAJADOR', 'ROLE_CLIENTE')")
    @GetMapping("")
    public String categoria(){
        return "categoria";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_TRABAJADOR', 'ROLE_CLIENTE')")
    @GetMapping("/trabajadores")
    public String trabajadores(@RequestParam(name = "profesion", required = false) String profesion, Model model) {

        List<Usuario> trabajadores = new ArrayList<>();
        if (profesion != null) {
            trabajadores = usuarioServicio.listarTrabajadoresPorProfesion(profesion);
        } else {
            trabajadores = usuarioServicio.listarTrabajadores();
        }
        
        model.addAttribute("trabajadores",trabajadores);
        return "trabajadores";
    }
    
    
    

}
