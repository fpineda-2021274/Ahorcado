<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión // para poder comenzar con tu juego de AHORCADO</title>
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

    <header class="hidden-on-load">
        <h1>Bienvenido</h1>
        <p>Inicia sesión o regístrate para acceder a tu cuenta.</p>
    </header>


    <main class="login-background">
        <div class="login-card">
            <h2>Iniciar Sesión</h2>
            <form id="loginForm">
                <div class="input-group">
                    <label for="username">Usuario:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="input-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="login-button">Iniciar Sesión</button>
            </form>
            <p class="register-link">¿No tienes cuenta? <a href="#">Regístrate aquí</a></p>
            <p class="forgot-password-link"><a href="#">¿Olvidaste tu contraseña?</a></p>
        </div>
    </main>

    <footer>
    
    </footer>

    

    <script>
        document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    sessionStorage.setItem('loggedIn', 'true');
    alert('¡Inicio de sesión exitoso!');
    window.location.href = 'index.jsp'; // Redirige al juego
});
    </script>
</body>
</html>
