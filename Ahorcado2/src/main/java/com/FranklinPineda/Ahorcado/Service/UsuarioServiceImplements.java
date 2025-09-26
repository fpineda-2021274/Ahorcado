package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import com.FranklinPineda.Ahorcado.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        if (usuario.getCorreo() == null || !usuario.getCorreo().toLowerCase().endsWith("@gmail.com")) {
            usuario.setCorreo("correoCorrecto");
            return usuario;
        }

        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        for (Usuario usuarioExistente : listaUsuarios) {
            if (usuarioExistente.getNombre().equalsIgnoreCase(usuario.getNombre()) && usuarioExistente.getApellido().equalsIgnoreCase(usuario.getApellido())) {
                usuario.setNombre("NombresDuplicados");
                return usuario;
            }
            if (usuarioExistente.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                usuario.setCorreo("CorreoDuplicado");
                return usuario;
            }
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getCorreo() == null || !usuario.getCorreo().toLowerCase().endsWith("@gmail.com")) {
                usuario.setCorreo("correoCorrecto");
                return usuario;
            }
            List<Usuario> listaUsuarios = usuarioRepository.findAll();
            for (Usuario usuarioExistente : listaUsuarios) {
                if (!usuarioExistente.getCodigoUsuario().equals(id)) {
                    if (usuarioExistente.getNombre().equalsIgnoreCase(usuario.getNombre()) &&
                            usuarioExistente.getApellido().equalsIgnoreCase(usuario.getApellido())) {
                        usuario.setNombre("NombresExisten");
                        return usuario;
                    }
                    if (usuarioExistente.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                        usuario.setCorreo("correoExiste");
                        return usuario;
                    }
                }
            }

            // If validations pass, update
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setPass(usuario.getPass());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    @Override
    public boolean deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}