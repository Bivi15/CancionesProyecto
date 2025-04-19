package com.canciones.canciones_proyecto.BBDD.DAO;

import com.canciones.canciones_proyecto.BBDD.BBDDConnector;
import com.canciones.canciones_proyecto.models.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario authenticate(String correo, String contrasena) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE correo = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String contrasenaGuardada = rs.getString("contrasena");
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setCorreo(rs.getString("correo"));

            if(contrasenaGuardada.startsWith("$2a$")) {
                if(BCrypt.checkpw(contrasena, contrasenaGuardada)) {
                    usuario.setContrasena(contrasenaGuardada);
                    return usuario;
                }
            }else {
                if(contrasenaGuardada.equals(contrasena)) {
                    String contrasenaHash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                    String query2 = "UPDATE usuarios SET contrasena = ? WHERE id_usuario = ?";
                    PreparedStatement ps2 = conn.prepareStatement(query2);
                    ps2.setString(1, contrasenaHash);
                    ps2.setInt(2, usuario.getIdUsuario());
                    ps2.executeUpdate();

                    usuario.setContrasena(contrasenaHash);
                    return usuario;
                }
            }
        }
        return null;
    }

    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido, correo, contrasena) VALUES (?, ?, ?, ?)";

        Connection conn = BBDDConnector.GetInstance().GetConnection();

        String validarCorreo = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        PreparedStatement ps2 = conn.prepareStatement(validarCorreo);
        ps2.setString(1, usuario.getCorreo());
        ResultSet rs = ps2.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            throw new SQLException("Usuario ya existe");
        }

        usuario.setContrasena(BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getContrasena());

        int rows =  ps.executeUpdate();
        return rows > 0;
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT id_usuario, nombre, apellido, correo FROM usuarios";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo(rs.getString("correo"));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public Usuario getUsuarioByID(int id) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT id_usuario,nombre, apellido, correo FROM usuarios WHERE id_usuario = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo(rs.getString("correo"));
        }

        return usuario;
    }

    public String getUsuarioCorreoById(int usuarioId) throws SQLException {
        String correo = null;
        String query = "SELECT correo FROM usuarios WHERE id_usuario = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, usuarioId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            correo = rs.getString("correo");
        }

        return correo;
    }

    public boolean addUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido, correo, contrasena) VALUES (?, ?, ?, ?)";
        String validarCorreo = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(validarCorreo);
        ps.setString(1, usuario.getCorreo());
        ResultSet rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            throw new SQLException("Correo ya registrado");
        }

        usuario.setContrasena(BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));

        PreparedStatement ps2 = conn.prepareStatement(query);
        ps2.setString(1, usuario.getNombre());
        ps2.setString(2, usuario.getApellido());
        ps2.setString(3, usuario.getCorreo());
        ps2.setString(4, usuario.getContrasena());

        int rows = ps2.executeUpdate();
        return rows > 0;
    }

    public boolean updateUsuario(Usuario usuario) throws SQLException {
        Connection conn = BBDDConnector.GetInstance().GetConnection();

        if(!usuario.getContrasena().startsWith("$2a$")) {
            usuario.setContrasena(BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
        }

        String query = "UPDATE usuarios SET nombre = ?, apellido = ?, correo = ?, contrasena = ? WHERE id_usuario = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getContrasena());
        ps.setInt(5, usuario.getIdUsuario());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public boolean deleteUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id_usuario = ?";

        Connection conn = BBDDConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        return rows > 0;
    }
}
