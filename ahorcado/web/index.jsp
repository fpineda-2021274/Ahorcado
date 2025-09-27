<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Carter+One&display=swap" rel="stylesheet" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="estilos.css">
    <link rel="stylesheet" href="login.css">
       <title>AHORCADO</title>
</head>
<body>
<header>
        <h1>AHORCADO</h1>
    </header>

    <main>
        <div class="game-container">
            <img id="imagen" src="img/base.png" alt="AHORCADO">
            <div class="game-info">
                <div id="pistas"></div>
                <p id="palabra_a_adivinar"></p>
                <p id="resultado"></p>
                <p id="cronometro">Tiempo: 00:00</p>
                <div class="button-group">
                    <button id="jugar">Jugar</button>
                    <button id="reiniciar" disabled>Reiniciar</button>
                    <button id="pausa" disabled>Pausa</button>
                    <button id="actualizar">Actualizar</button>
                </div>
            </div>
        </div>

        <div id="letras">
            <button>A</button><button>B</button><button>C</button><button>D</button>
            <button>E</button><button>F</button><button>G</button><button>H</button>
            <button>I</button><button>J</button><button>K</button><button>L</button>
            <button>M</button><button>N</button><button>Q</button><button>O</button>
            <button>P</button><button>Q</button><button>R</button><button>S</button>
            <button>T</button><button>U</button><button>V</button><button>W</button>
            <button>X</button><button>Y</button><button>Z</button>
        </div>
    </main>

    <script src="script/juego.js"></script>

    <script>
    // Verifica si el usuario inició sesión
    if (sessionStorage.getItem('loggedIn') !== 'true') {
        // Si no inició sesión, redirige al login
        window.location.href = 'login.jsp';
    }
</script>
</body>
</html>