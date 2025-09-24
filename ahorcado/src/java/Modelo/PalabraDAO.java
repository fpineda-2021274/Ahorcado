package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PalabraDAO {

    Conexion cn = new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public List<Palabra> listar() {
        List<Palabra> lista = new ArrayList<>();
        String sql = "{CALL sp_verpalabras()}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            while (rs.next()) {
                Palabra pal = new Palabra();
                pal.setCodigo_Palabra(rs.getInt(1));
                pal.setPalabra(rs.getString(2));
                pal.setPista_1(rs.getString(3));
                pal.setPista_2(rs.getString(4));
                pal.setPista_3(rs.getString(5));
                lista.add(pal);
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

    public int agregar(Palabra pal) {
        String sql = "{CALL sp_agregarpalabra(?, ?, ?, ?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setString(1, pal.getPalabra());
            cst.setString(2, pal.getPista_1());
            cst.setString(3, pal.getPista_2());
            cst.setString(4, pal.getPista_3());
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

    public int actualizar(Palabra pal) {
        String sql = "{CALL sp_actualizarpalabra(?, ?, ?, ?, ?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setInt(1, pal.getCodigo_Palabra());
            cst.setString(2, pal.getPalabra());
            cst.setString(3, pal.getPista_1());
            cst.setString(4, pal.getPista_2());
            cst.setString(5, pal.getPista_3());
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
    
    public Palabra buscar(int id) {
        Palabra pal = new Palabra();
        String sql = "{CALL sp_buscarlapalabra(?)}";
        try {
            con = cn.Conexion();
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                pal.setCodigo_Palabra(rs.getInt(1));
                pal.setPalabra(rs.getString(2));
                pal.setPista_1(rs.getString(3));
                pal.setPista_2(rs.getString(4));
                pal.setPista_3(rs.getString(5));
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
        return pal;
    }

    public void eliminar(int id) {
        String sql = "{CALL sp_eliminarpalabra(?)}";
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