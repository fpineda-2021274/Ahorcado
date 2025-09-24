// Ahora las palabras se leen desde la base de datos a través de la página JSP.
// La variable 'palabrasDelDB' ya está definida en el HTML antes de este script.

const imagen = document.getElementById("imagen");
const palabraAdivinar = document.getElementById("palabra_a_adivinar");
const resultadoTexto = document.getElementById("resultado");
const pistasDiv = document.getElementById("pistas");
const cronometro = document.getElementById("cronometro");
const letrasDiv = document.getElementById("letras");
const botonJugar = document.getElementById("jugar");
const botonReiniciar = document.getElementById("reiniciar");
const botonPausa = document.getElementById("pausa");

let palabraActual = {};
let palabraOculta = [];
let errores = 0;
let tiempo = 0;
let intervaloCronometro;
let juegoPausado = false;
let juegoTerminado = false;

// Array de rutas de imágenes del ahorcado
const imagenesAhorcado = [
    "/img/base.png",
    "/img/1.png",
    "/img/2.png",
    "/img/3.png",
    "/img/4.png",
    "/img/5.png",
    "/img/6.png"
];

// --- Funciones del juego ---

function iniciarJuego() {
    // Si no hay palabras de la base de datos, no se puede iniciar el juego.
    if (!palabrasDelDB_Juego || palabrasDelDB_Juego.length === 0) {
        resultadoTexto.textContent = "No hay palabras disponibles en la base de datos. Por favor, agregue algunas.";
        resultadoTexto.style.color = "red";
        return; // Detiene la ejecución si no hay palabras
    }

    // Restablecer el estado del juego
    errores = 0;
    tiempo = 0;
    juegoPausado = false;
    juegoTerminado = false;
    palabraOculta = [];
    resultadoTexto.textContent = "";
    pistasDiv.textContent = "";
    imagen.src = imagenesAhorcado[0];

    // Habilitar y deshabilitar botones
    botonJugar.disabled = true;
    botonReiniciar.disabled = false;
    botonPausa.disabled = false;
    
    // Habilitar los botones de las letras
    letrasDiv.querySelectorAll('button').forEach(btn => {
        btn.disabled = false;
        btn.style.opacity = "1";
    });

    // Elegir una palabra al azar de la lista de la base de datos
    const indiceAleatorio = Math.floor(Math.random() * palabrasDelDB_Juego.length);
    palabraActual = palabrasDelDB_Juego[indiceAleatorio];

    // Ocultar la palabra
    for (let i = 0; i < palabraActual.palabra.length; i++) {
        palabraOculta.push("_");
    }
    actualizarPalabraOculta();

    // Mostrar las pistas
    mostrarPistas();

    // Iniciar el cronómetro
    iniciarCronometro();
}

function actualizarPalabraOculta() {
    palabraAdivinar.textContent = palabraOculta.join(" ");
}

function mostrarPistas() {
    pistasDiv.innerJSP = "<h4>Pistas:</h4>";
    palabraActual.pistas.forEach(pista => {
        if(pista && pista.trim() !== "") {
            pistasDiv.innerHTML += `<p>- ${pista}</p>`;
        }
    });
}

function comprobarLetra(letra) {
    if (juegoTerminado || juegoPausado) return;

    const letraMayus = letra.toUpperCase();
    let acierto = false;
    
    // Recorrer la palabra para ver si la letra está presente
    for (let i = 0; i < palabraActual.palabra.length; i++) {
        if (palabraActual.palabra[i] === letraMayus) {
            palabraOculta[i] = letraMayus;
            acierto = true;
        }
    }

    if (acierto) {
        actualizarPalabraOculta();
        if (palabraOculta.join("") === palabraActual.palabra) {
            // Ganó el juego
            juegoTerminado = true;
            resultadoTexto.textContent = "¡Felicitaciones, has ganado! 🎉";
            resultadoTexto.style.color = "green";
            finalizarJuego();
        }
    } else {
        // Falló la letra
        errores++;
        imagen.src = imagenesAhorcado[errores];
        if (errores === imagenesAhorcado.length - 1) {
            // Perdió el juego
            juegoTerminado = true;
            resultadoTexto.textContent = `¡Has perdido! La palabra era: ${palabraActual.palabra} 😢`;
            resultadoTexto.style.color = "red";
            finalizarJuego();
        }
    }
}

function iniciarCronometro() {
    clearInterval(intervaloCronometro);
    intervaloCronometro = setInterval(() => {
        tiempo++;
        const minutos = Math.floor(tiempo / 60).toString().padStart(2, '0');
        const segundos = (tiempo % 60).toString().padStart(2, '0');
        cronometro.textContent = `Tiempo: ${minutos}:${segundos}`;
    }, 1000);
}

function pausarJuego() {
    if (juegoPausado) {
        iniciarCronometro();
        botonPausa.textContent = "Pausa";
        juegoPausado = false;
        letrasDiv.querySelectorAll('button').forEach(btn => btn.disabled = false);
    } else {
        clearInterval(intervaloCronometro);
        botonPausa.textContent = "Reanudar";
        juegoPausado = true;
        letrasDiv.querySelectorAll('button').forEach(btn => btn.disabled = true);
    }
}

function finalizarJuego() {
    clearInterval(intervaloCronometro);
    botonJugar.disabled = false;
    botonPausa.disabled = true;
    letrasDiv.querySelectorAll('button').forEach(btn => btn.disabled = true);
}

// --- Event Listeners ---

// Botón de jugar
botonJugar.addEventListener("click", iniciarJuego);

// Botón de reiniciar
botonReiniciar.addEventListener("click", iniciarJuego);

// Botón de pausa
botonPausa.addEventListener("click", pausarJuego);

// Botones de las letras
letrasDiv.addEventListener("click", (e) => {
    if (e.target.tagName === "BUTTON" && !e.target.disabled) {
        const letra = e.target.textContent;
        comprobarLetra(letra);
        e.target.disabled = true;
        e.target.style.opacity = "0.5";
    }
});