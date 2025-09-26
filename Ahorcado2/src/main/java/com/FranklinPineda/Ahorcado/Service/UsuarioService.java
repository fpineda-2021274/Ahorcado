package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer id, Usuario usuario);
    boolean deleteUsuario(Integer id);
}