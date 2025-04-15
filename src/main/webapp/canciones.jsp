<%@ page import="com.canciones.canciones_proyecto.models.Cancion" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Cancion canciones = (Cancion) request.getAttribute("canciones");
  boolean isEdit = (canciones != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><%= isEdit ? "Editar Canción" : "Nueva Canción" %></title>
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
      <h1 class="titulo"><%= isEdit ? "Editar Canción" : "Nueva Canción" %></h1>
    </div>
    <div class="form-bg"></div>
    <div class="container-form">
      <button><a href="canciones" class="volver-btn">Volver a la lista</a></button>
      <form action="canciones" class="form-cancion" method="post">
        <input type="hidden" name="action" value="save" />
        <% if (isEdit) { %>
        <input type="hidden" name="idCancion" value="<%= canciones.getIdCancion() %>" />
        <% } %>
        <div>
          <label for="titulo"></label>
          <input type="text" name="titulo" id="titulo" class="input-cancion" placeholder="Título" value="<%= isEdit ? canciones.getTitulo() : "" %>" required>
        </div>
        <div>
          <label for="artista"></label>
          <input type="text" name="artista" value="<%= isEdit ? canciones.getArtista() : "" %>" id="artista" class="input-cancion" placeholder="Artista">
        </div>
        <div>
          <label for="album"></label>
          <input type="text" name="album" value="<%= isEdit ? canciones.getAlbum() : "" %>" id="album" class="input-cancion" placeholder="Álbum">
        </div>
        <div>
          <label for="genero"></label>
          <input type="text" name="genero" value="<%= isEdit ? canciones.getGenero() : "" %>" id="genero" class="input-cancion" placeholder="Género">
        </div>
        <div>
          <label for="anioLanzamiento"></label>
          <input type="number" name="anioLanzamiento" value="<%= isEdit ? canciones.getAnioLanzamiento() : "" %>" id="anioLanzamiento" class="input-cancion" placeholder="Año Lanzamiento">
          <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
          <% } %>
        </div>
        <div>
          <label for="correo_usuario"></label>
          <input type="email" name="correo_usuario" value="<%= isEdit ? canciones.getUsuarioCorreo() : "" %>" id="correo_usuario" class="input-cancion" placeholder="Correo Usuario">
        </div>
        <input type="submit" value="submit" class="add-btn">
      </form>
    </div>
  </main>
</body>
</html>
