package com.FranklinPineda.Ahorcado.Controller;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import com.FranklinPineda.Ahorcado.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping
    public String createUsuario(@RequestBody Usuario usuario) {
        Usuario result = usuarioService.saveUsuario(usuario);
        if ("correoCorrecto".equals(result.getCorreo())) {
            return "El correo debe ser de dominio @gmail.com";
        }
        if ("NombresDuplicados".equals(result.getNombre())) {
            return "El nombre ya existe en los registros";
        }
        if ("CorreoDuplicado".equals(result.getCorreo())) {
            return "El correo electrónico ya está en uso";
        }
        return "Nuevo usuario: AGREGADO";
    }

    @PutMapping("/{id}")
    public String updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario buscarUsuario = usuarioService.updateUsuario(id, usuario);
        if (buscarUsuario == null) {
            return "No existe el usuario seleccionado";
        }
        if ("correoCorrecto".equals(buscarUsuario.getCorreo())) {
            return "El correo debe ser de dominio @gmail.com";
        }
        if ("NombresExisten".equals(buscarUsuario.getNombre())) {
            return "El nombre ya está registrado";
        }
        if ("correoExiste".equals(buscarUsuario.getCorreo())) {
            return "El correo ya está en uso";
        }
        return "Usuario: ACTUALIZADO";
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Integer id) {
        boolean eliminado = usuarioService.deleteUsuario(id);
        if (eliminado) {
            return "Usuario eliminado con éxito";
        } else {
            return "La id seleccionada no existe";
        }
    }
}