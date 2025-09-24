package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import com.FranklinPineda.Ahorcado.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        List<Usuario> lista = usuarioRepository.findAll();
        for (Usuario u : lista) {
            if (u.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                usuario.setNombre("ERROR_NOMBRE_REPETIDO");
                return usuario;
            }
            if (u.getApellido().equalsIgnoreCase(usuario.getApellido())) {
                usuario.setApellido("ERROR_APELLIDO_REPETIDO");
                return usuario;
            }
            if (u.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                usuario.setCorreo("ERROR_CORREO_REPETIDO");
                return usuario;
            }
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);
        if (existingUsuario != null) {
            List<Usuario> lista = usuarioRepository.findAll();
            for (Usuario u : lista) {
                if (!u.getId().equals(id)) {
                    if (u.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                        usuario.setNombre("ERROR_NOMBRE_REPETIDO");
                        return usuario;
                    }
                    if (u.getApellido().equalsIgnoreCase(usuario.getApellido())) {
                        usuario.setApellido("ERROR_APELLIDO_REPETIDO");
                        return usuario;
                    }
                    if (u.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                        usuario.setCorreo("ERROR_CORREO_REPETIDO");
                        return usuario;
                    }
                }
            }
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setPass(usuario.getPass());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}