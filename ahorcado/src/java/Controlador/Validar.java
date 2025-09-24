package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author informatica
 */
@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // No se necesita el código de prueba en el método processRequest.
        // La lógica principal se manejará en doPost.
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige al método doPost para manejar la lógica de la solicitud.
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        UsuarioDAO usuarioDao = new UsuarioDAO();

        if (accion.equalsIgnoreCase("Ingresar")) {
            String correo = request.getParameter("txtCorreo");
            String pass = request.getParameter("txtPassword");

            Usuario usuario = usuarioDao.validar(correo, pass);
            
            if (usuario != null && usuario.getCorreo() != null) {
                // Si el usuario es encontrado, redirige a la página principal del juego
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("Controlador?menu=Juego&accion=Iniciar").forward(request, response);
            } else {
                // Si el usuario no es encontrado, redirige de nuevo al login con un mensaje de error
                request.setAttribute("errorLogin", "Correo o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            // Si la acción no es "Ingresar" o se accede directamente, redirige al login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para validar el inicio de sesión de usuarios.";
    }// </editor-fold>

}