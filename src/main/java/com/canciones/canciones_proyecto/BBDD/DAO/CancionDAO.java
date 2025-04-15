package com.canciones.canciones_proyecto.BBDD.DAO;

import com.canciones.canciones_proyecto.BBDD.BBDDConnector;
import com.canciones.canciones_proyecto.models.Cancion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {

    public List<Cancion> GetAllCanciones() throws SQLException {
        List<Cancion> canciones = new ArrayList<>();

        String sql = "SELECT * FROM canciones";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Cancion c = new Cancion();
            c.setIdCancion(rs.getInt("id_cancion"));
            c.setTitulo(rs.getString("titulo"));
            c.setArtista(rs.getString("artista"));
            c.setAlbum(rs.getString("album"));
            c.setGenero(rs.getString("genero"));
            c.setAnioLanzamiento(rs.getInt("anio_lanzamiento"));
            c.setUsuarioId(rs.getInt("usuario_id"));

            canciones.add(c);
        }

        return canciones;
    }

    public Cancion GetCancionById(int id) throws SQLException {
        Cancion c = null;
        String sql = "SELECT * FROM canciones WHERE id_cancion = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            c = new Cancion();
            c.setIdCancion(rs.getInt("id_cancion"));
            c.setTitulo(rs.getString("titulo"));
            c.setArtista(rs.getString("artista"));
            c.setAlbum(rs.getString("album"));
            c.setGenero(rs.getString("genero"));
            c.setAnioLanzamiento(rs.getInt("anio_lanzamiento"));
            c.setUsuarioId(rs.getInt("usuario_id"));
        }

        return c;
    }

    public int getUsuarioIdByCorreo(String correo) throws SQLException {
        int usuarioId = 0;
        String query = "SELECT id_usuario FROM usuarios WHERE correo = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, correo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuarioId = rs.getInt("id_usuario");
        }
        return usuarioId;
    }

    public boolean AddCancion(Cancion cancion) throws SQLException {
        String sql = "INSERT INTO canciones (titulo, artista, album, genero, anio_lanzamiento, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cancion.getTitulo());
        ps.setString(2,cancion.getArtista());
        ps.setString(3,cancion.getAlbum());
        ps.setString(4,cancion.getGenero());
        ps.setInt(5,cancion.getAnioLanzamiento());
        ps.setInt(6, cancion.getUsuarioId());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public boolean UpdateCancion(Cancion cancion) throws SQLException {
        String sql = "UPDATE canciones SET titulo = ?, artista = ?, album = ?, genero = ?, anio_lanzamiento = ?, usuario_id = ? WHERE id_cancion = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cancion.getTitulo());
        ps.setString(2,cancion.getArtista());
        ps.setString(3,cancion.getAlbum());
        ps.setString(4, cancion.getGenero());
        ps.setInt(5,cancion.getAnioLanzamiento());
        ps.setInt(6, cancion.getUsuarioId());
        ps.setInt(7, cancion.getIdCancion());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public boolean DeleteCancion(int id) throws SQLException {
        String sql = "DELETE FROM canciones WHERE id_cancion = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        return rows > 0;
    }
}
