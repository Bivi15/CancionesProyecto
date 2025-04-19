package com.canciones.canciones_proyecto.controllers;

import com.canciones.canciones_proyecto.BBDD.DAO.UsuarioDAO;
import com.canciones.canciones_proyecto.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("registar".equals(action)) {
            registarUsuario(request, response);
        } else if ("login".equals(action)) {
            loginUsuario(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("registar".equals(action)) {
            registarUsuario(request, response);
        }
    }

    private void registarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String confirmarContrasena = request.getParameter("confirmarcontrasena");

        if (nombre == null || nombre.trim().isEmpty()) {
            request.setAttribute("error", "El nombre es requerido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (correo == null || correo.trim().isEmpty()) {
            request.setAttribute("error", "El correo es requerido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (contrasena == null || contrasena.trim().isEmpty()) {
            request.setAttribute("error", "La contraseña es requerida.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        try {
            if (usuarioDAO.registrarUsuario(usuario)) {
                request.setAttribute("registroExitoso", true);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorRegistro", "Error en el registro.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch (SQLException e){
            e.printStackTrace();
            request.setAttribute("error", "Error en el registro." + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuario = usuarioDAO.authenticate(correo, contrasena);
            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Credenciales incorrectas.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al iniciar sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
