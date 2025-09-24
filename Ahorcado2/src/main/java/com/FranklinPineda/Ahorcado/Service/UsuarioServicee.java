package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import java.util.List;

public interface UsuarioServicee {

    List<Usuario> getAllUsuario();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);

}
