package com.canciones.canciones_proyecto.controllers;

import com.canciones.canciones_proyecto.BBDD.DAO.CancionDAO;
import com.canciones.canciones_proyecto.models.Cancion;
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

@WebServlet(name = "CancionesServlet", urlPatterns = "/canciones")
public class CancionController extends HttpServlet {

    private CancionDAO cancionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        cancionDAO = new CancionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                editCanciones(request, response);
                break;
            case "delete":
                deleteCanciones(request, response);
                break;
            case "list":
            default:
                listCanciones(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if("save".equals(action)) {
            saveCanciones(request, response);
        }
    }

    private void listCanciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cancion> canciones = new ArrayList<>();

        try {
            canciones = cancionDAO.getAllCanciones();
        }catch (SQLException e) {
            e.getStackTrace();
        }

        request.setAttribute("canciones", canciones);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-canciones.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("canciones.jsp");
        dispatcher.forward(request, response);
    }

    private void editCanciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int  idCancion = Integer.parseInt(request.getParameter("id"));
        Cancion canciones = null;

        try {
            canciones = cancionDAO.getCancionById(idCancion);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("canciones", canciones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("canciones.jsp");
        dispatcher.forward(request, response);
    }

    private void saveCanciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("idCancion");
        String titulo = request.getParameter("titulo");
        String artista = request.getParameter("artista");
        String album = request.getParameter("album");
        String genero = request.getParameter("genero");
        String anioLanzamiento = request.getParameter("anioLanzamiento");
        String correoUsuario = request.getParameter("correo_usuario");
        String usuarioIdParam = request.getParameter("usuario_id");

        int idCan = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        int anioLanza = 0;
        try {
            anioLanza = Integer.parseInt(anioLanzamiento);
            if (anioLanza <= 0) {
                throw new NumberFormatException("Año negativo o cero");
            }
        }catch (NumberFormatException e) {
            request.setAttribute("error", "El año de lanzamiento debe ser un número positivo válido");

            request.setAttribute("titulo", titulo);
            request.setAttribute("artista", artista);
            request.setAttribute("album", album);
            request.setAttribute("genero", genero);
            request.setAttribute("anioLanzamiento", anioLanzamiento);
            request.setAttribute("correo_usuario", correoUsuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("canciones.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int usuarioId = 0;
        try {
            usuarioId = cancionDAO.getUsuarioIdByCorreo(correoUsuario);
            if (usuarioId == 0) {
                request.setAttribute("error", "El correo proporcionado no está registrado.");
                request.setAttribute("titulo", titulo);
                request.setAttribute("artista", artista);
                request.setAttribute("album", album);
                request.setAttribute("genero", genero);
                request.setAttribute("anioLanzamiento", anioLanzamiento);
                request.setAttribute("correo_usuario", correoUsuario);
                RequestDispatcher dispatcher = request.getRequestDispatcher("canciones.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar el usuario.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("canciones.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Cancion can = new Cancion();
        can.setTitulo(titulo);
        can.setArtista(artista);
        can.setAlbum(album);
        can.setGenero(genero);
        can.setAnioLanzamiento(anioLanza);
        can.setUsuarioId(usuarioId);

        try {
            if (idCan == 0) {
                cancionDAO.addCancion(can);
            }else {
                can.setIdCancion(idCan);
                cancionDAO.updateCancion(can);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("canciones?action=list");
    }

    private void deleteCanciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCanciones = Integer.parseInt(request.getParameter("id"));

        try {
            cancionDAO.deleteCancion(idCanciones);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("canciones?action=list");
    }
}
