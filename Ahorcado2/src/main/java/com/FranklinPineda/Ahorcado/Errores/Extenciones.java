package com.FranklinPineda.Ahorcado.Errores;

import java.util.List;  // Import necesario

// Asegúrate de tener tu clase Usuario en el paquete correcto
import com.FranklinPineda.Ahorcado.Model.Usuario;

public class Extenciones {

    public String ValidarUsuario(String nombre, String correo, String telefono, int edad, String password, List<Usuario> listaUsuarios) {

        //  Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: El nombre no puede estar vacío.";
        }

        //  Validar que el correo no sea nulo y contenga '@'
        if (correo == null || !correo.contains("@")) {
            return "Error: El correo debe contener '@'.";
        }

        //  Validar que el teléfono no sea nulo y tenga exactamente 8 dígitos
        if (telefono == null || telefono.length() != 8) {
            return "Error: El teléfono debe tener exactamente 8 dígitos.";
        }

        //  Validar que la edad sea mayor o igual a 18
        if (edad < 18) {
            return "Error: Debes ser mayor de 18 años.";
        }

        //  Validar que la contraseña tenga al menos 6 caracteres
        if (password == null || password.length() < 6) {
            return "Error: La contraseña debe tener al menos 6 caracteres.";
        }

        // Validar que la contraseña tenga al menos un número
        if (!password.matches(".*\\d.*")) {
            return "Error: La contraseña debe contener al menos un número.";
        }

        //  No duplicar el nombre
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return "Error: El nombre ya existe.";
            }
        }

        // No duplicar el correo
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return "Error: El correo ya está registrado.";
            }
        }

        //  Validar límite de caracteres en correo (ejemplo: máximo 50)
        if (correo.length() > 50) {
            return "Error: El correo no puede tener más de 50 caracteres.";
        }

        //  Nombre sin límite de caracteres (intencional, no validamos longitud)

        return " Todas las validaciones fueron correctas.";
    }
}
