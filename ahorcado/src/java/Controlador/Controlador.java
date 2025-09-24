package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Palabra;
import modelo.PalabraDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Controlador extends HttpServlet {

    Palabra palabra = new Palabra();
    PalabraDAO palabraDAO = new PalabraDAO();
    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        if (menu.equalsIgnoreCase("Juego")) {
            List<Palabra> listaPalabras = palabraDAO.listar();
            request.setAttribute("listaPalabras", listaPalabras);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    List<Usuario> listaUsuarios = usuarioDAO.listar();
                    request.setAttribute("usuarios", listaUsuarios);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String correo = request.getParameter("txtCorreo");
                    String pass = request.getParameter("txtPass");
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setCorreo(correo);
                    usuario.setPass(pass);
                    usuarioDAO.agregar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idUsuario = Integer.parseInt(request.getParameter("id"));
                    usuarioDAO.eliminar(idUsuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int idUserEdit = Integer.parseInt(request.getParameter("id"));
                    Usuario userEditar = usuarioDAO.buscar(idUserEdit);
                    request.setAttribute("usuarioEdit", userEditar);
                    break;
                case "Actualizar":
                    int codUser = Integer.parseInt(request.getParameter("txtCodigo"));
                    String nombreAct = request.getParameter("txtNombre");
                    String apellidoAct = request.getParameter("txtApellido");
                    String correoAct = request.getParameter("txtCorreo");
                    String passAct = request.getParameter("txtPass");                    
                    usuario.setCodigo_Usuario(codUser);
                    usuario.setNombre(nombreAct);
                    usuario.setApellido(apellidoAct);
                    usuario.setCorreo(correoAct);
                    usuario.setPass(passAct);
                    usuarioDAO.actualizar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);

        } else if (menu.equals("Palabras")) {
            switch (accion) {
                case "Listar":
                    List<Palabra> listaPalabras = palabraDAO.listar();
                    request.setAttribute("palabras", listaPalabras);
                    break;
                case "Agregar":
                    String palabraTexto = request.getParameter("txtPalabra");
                    String pista1 = request.getParameter("txtPista1");
                    String pista2 = request.getParameter("txtPista2");
                    String pista3 = request.getParameter("txtPista3");
                    palabra.setPalabra(palabraTexto);
                    palabra.setPista_1(pista1);
                    palabra.setPista_2(pista2);
                    palabra.setPista_3(pista3);
                    palabraDAO.agregar(palabra);
                    request.getRequestDispatcher("Controlador?menu=Palabras&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idPalabra = Integer.parseInt(request.getParameter("id"));
                    palabraDAO.eliminar(idPalabra);
                    request.getRequestDispatcher("Controlador?menu=Palabras&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int idPalabraEdit = Integer.parseInt(request.getParameter("id"));
                    Palabra palabraEditar = palabraDAO.buscar(idPalabraEdit);
                    request.setAttribute("palabraEdit", palabraEditar);
                    break;
                case "Actualizar":
                    int codPalabra = Integer.parseInt(request.getParameter("txtCodigo"));
                    String palabraAct = request.getParameter("txtPalabra");
                    String pista1Act = request.getParameter("txtPista1");
                    String pista2Act = request.getParameter("txtPista2");
                    String pista3Act = request.getParameter("txtPista3");
                    palabra.setCodigo_Palabra(codPalabra);
                    palabra.setPalabra(palabraAct);
                    palabra.setPista_1(pista1Act);
                    palabra.setPista_2(pista2Act);
                    palabra.setPista_3(pista3Act);
                    palabraDAO.actualizar(palabra);
                    request.getRequestDispatcher("Controlador?menu=Palabras&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("palabras.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}