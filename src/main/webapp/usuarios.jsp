<%@ page import="com.canciones.canciones_proyecto.models.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>usuarios</title>
  <link rel="stylesheet" href="css/style.css">
  <script src="js/scriptusuario.js" defer></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400..700;1,400..700&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
  <header class="inicio">
    <p>
      <a href="index.jsp" class="home effect"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-house-door" viewBox="0 0 16 16">
        <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4z"/>
      </svg></a>
    </p>
  </header>
  <main>
    <div class="container-titulo">
      <h1 class="titulo">Listado de Usuarios</h1>
    </div>
    <div class="form-bg"></div>
    <div class="container-listas">
      <button><a href="add-usuario.jsp" class="crear-btn">Crear Usuario</a></button>
      <div class="tabla-listas">
        <table>
          <thead>
          <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Correo</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <%
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            if (usuarios != null) {
              for(Usuario usua : usuarios) {
          %>
            <tr>
              <td><%= usua.getNombre() %></td>
              <td><%= usua.getApellido() %></td>
              <td><%= usua.getCorreo() %></td>
              <td>
                <span class="action-btn">
                    <a href="<%= request.getContextPath() %>/usuarios?action=edit&id=<%= usua.getIdUsuario() %>" class="edit-btn" id="edit-usuario">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                      </svg></a>
                    <a href="usuarios?action=delete&id=<%= usua.getIdUsuario() %>" class="delete-btn" id="delete-usuario">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
                      </svg></a>
                </span>
              </td>
            </tr>
          <%
            }
          } else {
          %>
          <tr>
            <td colspan="5" class="no-registro">No hay usuarios registrados.</td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>
    </div>
    <div id="toast" style="display: none;">
      <span id="toast-message"></span>
      <div class="toast-button">
        <button id="toast-yes">Sí</button>
        <button id="toast-no">No</button>
      </div>
    </div>
  </main>
</body>
</html>
