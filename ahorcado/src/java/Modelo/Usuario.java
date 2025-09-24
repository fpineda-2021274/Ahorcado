package modelo;

public class Usuario {

    private int codigo_Usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String pass;

    public Usuario() {
    }

    public Usuario(int codigo_Usuario, String nombre, String apellido, String correo, String pass) {
        this.codigo_Usuario = codigo_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
    }

    public int getCodigo_Usuario() {
        return codigo_Usuario;
    }

    public void setCodigo_Usuario(int codigo_Usuario) {
        this.codigo_Usuario = codigo_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}