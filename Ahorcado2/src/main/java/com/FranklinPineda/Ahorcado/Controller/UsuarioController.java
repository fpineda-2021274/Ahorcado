package com.FranklinPineda.Ahorcado.Controller;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import com.FranklinPineda.Ahorcado.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuario();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    // Crear nuevo usuario
    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    // Actualizar usuario existente
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }
}
