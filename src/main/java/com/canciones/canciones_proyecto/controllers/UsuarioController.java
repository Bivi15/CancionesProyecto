package com.canciones.canciones_proyecto.controllers;

import com.canciones.canciones_proyecto.BBDD.DAO.UsuarioDAO;
import com.canciones.canciones_proyecto.models.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UsuariosServlet", urlPatterns = "/usuarios")
public class UsuarioController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        System.out.println("Acción recibida: " + action);


        if ("delete".equals(action)) {
            deleteUsuario(request,response);
        }else if ("list".equals(action)) {
            listUsuarios(request, response);
        }else if ("edit".equals(action)){
            editUsuarios(request,response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            addUsuario(request, response);
        }
    }

    protected void listUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            usuarios = usuarioDAO.GetAllUsuarios();
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios.jsp");
        dispatcher.forward(request, response);
    }
    protected void addUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        int idUsuario = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        if(nombre == null || apellido == null || correo == null || contrasena == null || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios");
            request.getRequestDispatcher("add-usuarios.jsp").forward(request, response);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        try {
            if (idUsuario == 0) {
                usuarioDAO.AddUsuario(usuario);
            }else {
                usuario.setIdUsuario(idUsuario);
                usuarioDAO.UpdateUsuario(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("usuarios?action=list");
    }

    protected void editUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int  idUsuario = Integer.parseInt(request.getParameter("id"));
        Usuario usuarios = null;

        try {
            usuarios = usuarioDAO.GetUsuarioByID(idUsuario);
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (usuarios != null) {
            request.setAttribute("usuarios", usuarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-usuario.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("usuarios?action=list");
        }
    }

    protected void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUsuario = request.getParameter("id");
        if (idUsuario != null &&   !idUsuario.isEmpty()) {
            try {
                int idUsua = Integer.parseInt(request.getParameter("id"));
                usuarioDAO.DeleteUsuario(idUsua);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("usuarios?action=list");
    }
}
