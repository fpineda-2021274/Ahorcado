package com.FranklinPineda.Ahorcado.Repository;

import com.FranklinPineda.Ahorcado.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}