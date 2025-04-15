<%@ page import="com.canciones.canciones_proyecto.models.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Usuario usuarios = (Usuario) request.getAttribute("usuarios");
  boolean isEdit = (usuarios != null);
%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><%= isEdit ? "Editar Usuario" : "Añadir Usuario" %></title>
  <link rel="stylesheet" href="css/style.css">
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
      <h1 class="titulo"><%= isEdit ? "Editar Usuario" : "Añadir Usuario" %></h1>
    </div>
    <div class="form-bg"></div>
    <div class="container-form">
      <button><a href="usuarios" class="volver-btn">Volver a la lista</a></button>
      <form action="usuarios?action=save" class="form-usuario" method="post">
        <input type="hidden" name="action" value="edit" />
        <% if (isEdit) { %>
        <input type="hidden" name="idUsuario" value="<%= usuarios.getIdUsuario() %>" />
        <% } %>
        <div>
          <label for="nombre"></label>
          <input type="text" name="nombre" id="nombre" class="input-usuario" placeholder="Nombre" value="<%= isEdit ? usuarios.getNombre() : "" %>" required>
        </div>
        <div>
          <label for="apellido"></label>
          <input type="text" name="apellido"<%= isEdit ? usuarios.getApellido() : "" %> id="apellido" class="input-usuario" placeholder="Apellido">
        </div>
        <div>
          <label for="correo"></label>
          <input type="text" name="correo"<%= isEdit ? usuarios.getCorreo() : "" %> id="correo" class="input-usuario" placeholder="Correo">
        </div>
        <div>
          <label for="contrasena"></label>
          <input type="text" name="contrasena"<%= isEdit ? usuarios.getContrasena() : "" %> id="contrasena" class="input-usuario" placeholder="Contraseña">
        </div>
        <input type="submit" value="submit" class="add-btn">
      </form>
    </div>
  </main>
</body>
</html>


