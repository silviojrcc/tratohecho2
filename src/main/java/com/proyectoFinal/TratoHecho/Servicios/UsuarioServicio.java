/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoFinal.TratoHecho.Servicios;

import com.proyectoFinal.TratoHecho.Entidades.Enum.Rol;
import com.proyectoFinal.TratoHecho.Entidades.Foto;
import com.proyectoFinal.TratoHecho.Entidades.Usuario;
import com.proyectoFinal.TratoHecho.Repositori.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private FotoServicio fotoServicio;

    public Usuario registrarUsuario(String username, String password, String password2, String rol, String correoElectronico, String numeroDeTelefono,String profesion, MultipartFile archivo) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("debe ingresar un nombre de usuario");
        }
        if (password.isEmpty()) {
            throw new Exception("debe ingresar una contraseña");
        }

        Usuario usuario = usuarioRepositorio.buscarUsuario(username);
        if (usuario != null) {
            throw new Exception("el nombre de usuario ya existe");
        }

        if (!password.equals(password2)) {
            throw new Exception("las Contraseñas no coinciden");
        }

        usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setProfesion(profesion);
        usuario.setNumeroDeTelefono(numeroDeTelefono);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setRol(Rol.valueOf(rol));
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setPassword(encoder.encode(password));
        
        Foto foto = fotoServicio.guardar(archivo);
        usuario.setFoto(foto);
        
        return usuarioRepositorio.save(usuario);
    }
    
     public Usuario buscarUId(String id) {
        return usuarioRepositorio.getById(id);
    }
     
      public List<Usuario> listarTrabajadores(){
         return  usuarioRepositorio.listarTrabajadores();
     }
      
      public List<Usuario> listarTrabajadoresPorProfesion(String profesion){
          return usuarioRepositorio.listarTrabajadoresPorProfesion(profesion);
      }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarUsuario(username);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();
            permisos.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuario", usuario);

            User user = new User(username, usuario.getPassword(), permisos);
            return user;
        } else {
            return null;
        }
        
    }

}

