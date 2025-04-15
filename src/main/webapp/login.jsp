<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <script src = "js/script.js" defer></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400..700;1,400..700&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <title>Login / Registro</title>
</head>
<body>
<main class="main-login">
    <div class="registro">
        <span>No eres usuario?</span>
        <button type="button" class="button">Registrate</button>
    </div>
    <div class="login">
        <span>Ya tienes cuenta?</span>
        <button type="button" class="button">Ingresa</button>
    </div>
    <div class="back-layer">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="login" id="form-login" class="login-form active" method="post">
            <h2 class="login-titulo">Ingresa</h2>
            <input type="hidden" name="action" value="login">
            <label for="correo"></label>
            <input type="email" class="input-login" id="correo" name="correo" placeholder="Correo" required>
            <label for="contrasena"></label>
            <input type="password" class="input-login" id="contrasena" name="contrasena" placeholder="Contraseña">
            <button type="submit" class="button-login">Ingresar</button>
        </form>

        <form action="login" id="form-registro" class="registro-form" method="post">
            <h2 class="login-titulo">Registro</h2>
            <input type="hidden" name="action" value="registar">
            <label for="nombre"></label>
            <input type="text" class="input-login" id="nombre" name="nombre" placeholder="Nombre" required>
            <label for="apellido"></label>
            <input type="text" class="input-login" id="apellido" name="apellido" placeholder="Apellido" required>
            <label for="correo"></label>
            <input type="email" class="input-login" name="correo" placeholder="Correo" required>
            <label for="contrasenar"></label>
            <input type="password" class="input-login" id="contrasenar" name="contrasena" placeholder="Contraseña" required>
            <% String errorRegistro = (String) request.getAttribute("errorRegistro");
                if (errorRegistro != null) { %>
            <div class="mensaje-error"><%= errorRegistro %></div>
            <% } %>
            <label for="confirmarContrasena"></label>
            <input type="password" class="input-login" id="confirmarContrasena" name="confirmarcontrasena" placeholder="Confirmar Contraseña" required>

            <button type="submit" class="button-login">Registrar</button>
        </form>
    </div>
</main>
</body>
</html>

