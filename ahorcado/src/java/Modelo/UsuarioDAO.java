package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public Usuario validar(String correo, String pass) {
        Usuario usuario = new Usuario();
        String sql = "{CALL sp_validarUsuario(?, ?)}"; 
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setString(1, correo);
            cst.setString(2, pass);
            rs = cst.executeQuery();
            if (rs.next()) {
                usuario.setCodigo_Usuario(rs.getInt("codigo_Usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
    
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "{CALL sp_VerUsuarios()}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setCodigo_Usuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setCorreo(rs.getString(4));
                user.setPass(rs.getString(5));
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public int agregar(Usuario user) {
        String sql = "{CALL sp_AgregarUsuario(?, ?, ?, ?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setString(1, user.getNombre());
            cst.setString(2, user.getApellido());
            cst.setString(3, user.getCorreo());
            cst.setString(4, user.getPass());
            r = cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    
    public Usuario buscar(int id) {
        Usuario user = new Usuario();
        String sql = "{CALL sp_BuscarUsuario(?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                user.setCodigo_Usuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setCorreo(rs.getString(4));
                user.setPass(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public int actualizar(Usuario user) {
        String sql = "{CALL sp_ActualizarUsuario(?, ?, ?, ?, ?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setInt(1, user.getCodigo_Usuario());
            cst.setString(2, user.getNombre());
            cst.setString(3, user.getApellido());
            cst.setString(4, user.getCorreo());
            cst.setString(5, user.getPass());
            r = cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public void eliminar(int id) {
        String sql = "{CALL sp_EliminarUsuario(?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) cst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}