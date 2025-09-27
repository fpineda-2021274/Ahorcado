// ==============================================================================
// 1. VARIABLES GLOBALES Y LISTA DE PALABRAS (Compatibilidad Máxima)
// ==============================================================================
const imagenesAhorcado = [
    "img/base.png", // Imagen inicial
    "img/1.png",
    "img/2.png",
    "img/3.png",
    "img/4.png",
    "img/5.png",
    "img/6.png"
];

// LISTA DE PALABRAS 100% SEGURA: No tiene caracteres especiales (tildes, ñ)
let palabras = [
    { palabra: "mariposa", pistas: ["insecto", "alas coloridas", "vuela en jardines"] },
    { palabra: "elefante", pistas: ["animal grande", "trompa larga", "vive en sabana"] },
    { palabra: "computador", pistas: ["maquina", "procesa datos", "usa electricidad"] }, // 'maquina' sin tilde
    { palabra: "cocodrilo", pistas: ["reptil", "vive en rios", "tiene dientes filosos"] }, // 'rios' sin tilde
    { palabra: "sandwich", pistas: ["comida", "pan relleno", "rapido de preparar"] }, // 'rapido' sin tilde
    { palabra: "jirafona", pistas: ["animal", "cuello largo", "vive en africa"] },
    { palabra: "volcanico", pistas: ["naturaleza", "montana", "erupcion de lava"] }, // 'montaña' y 'erupción' sin caracteres especiales
    { palabra: "esmeralda", pistas: ["piedra preciosa", "verde", "muy valiosa"] },
    { palabra: "camisetas", pistas: ["ropa", "algodon", "se usa en verano"] },
    { palabra: "astronauta", pistas: ["profesion", "espacio", "traje especial"] },
    { palabra: "carretera", pistas: ["camino", "autos", "asfalto"] },
    { palabra: "murcielago", pistas: ["animal", "vuela", "nocturno"] },
    { palabra: "pantallas", pistas: ["tecnologia", "televisor", "dispositivo visual"] },
    { palabra: "cangrejos", pistas: ["animal", "tenazas", "mariscos"] },
    { palabra: "microscopio", pistas: ["instrumento", "ciencia", "mira lo pequeno"] }, // 'pequeño' sin 'ñ'
    { palabra: "universos", pistas: ["espacio", "estrellas", "planetas"] },
    { palabra: "camisones", pistas: ["ropa", "para dormir", "muy comoda"] },
    { palabra: "orquideas", pistas: ["flores", "decoracion", "colores vivos"] },
    { palabra: "girasoles", pistas: ["plantas", "amarillas", "siguen al sol"] },
    { palabra: "escaleras", pistas: ["subir", "escalones", "arquitectura"] }
];

let palabraSeleccionada = ""; // Se inicializa vacía, se llenará en iniciarJuego
let pistasSeleccionadas = [];
let letrasAdivinadas = [];
let intentosIncorrectos = 0;
let tiempo = 0;
let intervalo;
let juegoPausado = true;


// ==============================================================================
// 2. LÓGICA DE PALABRA Y PISTAS
// ==============================================================================

function seleccionarPalabra() {
    const indice = Math.floor(Math.random() * palabras.length);
    let palabraOriginal = palabras[indice].palabra.toLowerCase();
    pistasSeleccionadas = palabras[indice].pistas;

    // Normalizamos para asegurar que las letras del teclado coincidan, aunque la palabra ya no tenga tilde.
    palabraSeleccionada = palabraOriginal.normalize("NFD").replace(/[\u0300-\u036f]/g, "");

    mostrarPistas();
}

function mostrarPistas() {
    const pistasDiv = document.getElementById('pistas');
    if (!pistasDiv) return;

    pistasDiv.innerHTML = '<h2>Pistas:</h2>'; // Añadimos un título de pistas
    pistasSeleccionadas.forEach((pista, i) => {
        const p = document.createElement('p');
        // Usamos la pista original (que puede tener tilde si la pusiste en el JS)
        p.textContent = `Pista ${i + 1}: ${pista}`;
        pistasDiv.appendChild(p);
    });
}


// ==============================================================================
// 3. FUNCIONES DE JUEGO
// ==============================================================================

function iniciarJuego() {
    try {
        // Resetear variables
        intentosIncorrectos = 0;
        letrasAdivinadas = [];
        tiempo = 0;
        juegoPausado = false;

        // Limpiamos el intervalo anterior (importante si se llama desde 'Reiniciar')
        clearInterval(intervalo); 

        // Resetear elementos del DOM
        document.getElementById('cronometro').textContent = 'Tiempo: 00:00';
        document.getElementById('imagen').src = imagenesAhorcado[0];
        document.getElementById('resultado').textContent = '';
        document.getElementById('resultado').style.color = 'inherit';

        // Selecciona una PALABRA NUEVA y sus PISTAS
        seleccionarPalabra();
        actualizarPalabra();
        cronometro();

        // Habilitar / Deshabilitar botones de control
        document.getElementById('jugar').disabled = true;
        document.getElementById('reiniciar').disabled = false;
        document.getElementById('pausa').disabled = false;
        document.getElementById('pausa').textContent = 'Pausa';
        document.getElementById('actualizar').disabled = false;

        document.querySelectorAll('#letras button').forEach((btn) => btn.disabled = false);

    } catch (e) {
        console.error("Error crítico al iniciar el juego:", e);
        document.getElementById('resultado').textContent = 'Error: No se pudo iniciar el juego. Revisa la consola (F12).';
        document.getElementById('resultado').style.color = '#f44336';
    }
}

function cronometro() {
    if (juegoPausado) return;
    clearInterval(intervalo);
    intervalo = setInterval(() => {
        if (!juegoPausado) {
            tiempo++;
            let minutos = Math.floor(tiempo / 60);
            let segundos = tiempo % 60;
            // Corregido: El padStart debe usar '0' y no '60' para rellenar
            document.getElementById('cronometro').textContent = `Tiempo: ${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}`;
        }
    }, 1000);
}

function actualizarPalabra() {
    const palabraDiv = document.getElementById('palabra_a_adivinar');
    if (!palabraDiv) return;

    let palabraOculta = '';
    for (let i = 0; i < palabraSeleccionada.length; i++) {
        palabraOculta += letrasAdivinadas.includes(palabraSeleccionada[i]) ? palabraSeleccionada[i].toUpperCase() : '_';
        palabraOculta += ' ';
    }
    palabraDiv.textContent = palabraOculta.trim();
}

function errores() {
    intentosIncorrectos++;

    if (intentosIncorrectos < imagenesAhorcado.length) {
        document.getElementById('imagen').src = imagenesAhorcado[intentosIncorrectos];
    } else {
        document.getElementById('resultado').textContent = '¡PERDISTE! La palabra era: ' + palabraSeleccionada.toUpperCase();
        document.getElementById('resultado').style.color = '#f44336';
        finalizarJuego();
    }
}

function verificarVictoria() {
    return palabraSeleccionada.split('').every(letra => letrasAdivinadas.includes(letra));
}

function finalizarJuego() {
    clearInterval(intervalo);
    document.querySelectorAll('#letras button').forEach((btn) => btn.disabled = true);
    document.getElementById('pausa').disabled = true;
    document.getElementById('jugar').disabled = false;
    juegoPausado = true;
}


// ==============================================================================
// 4. ASIGNACIÓN DE EVENTOS
// ==============================================================================

document.addEventListener('DOMContentLoaded', () => {

    // Asignar letras a los botones (Teclado)
    document.querySelectorAll('#letras button').forEach((btn) => {
        btn.addEventListener('click', () => {
            if (!juegoPausado) {
                const letra = btn.textContent.toLowerCase();

                // CRÍTICO: Normalizar la letra del teclado si fuera una 'Ñ' o una letra con tilde
                const letraLimpia = letra.normalize("NFD").replace(/[\u0300-\u036f]/g, "");

                if (letrasAdivinadas.includes(letraLimpia)) return;

                letrasAdivinadas.push(letraLimpia);
                btn.disabled = true;

                if (!palabraSeleccionada.includes(letraLimpia)) {
                    errores();
                }

                actualizarPalabra();

                if (verificarVictoria()) {
                    document.getElementById('resultado').textContent = '¡GANASTE! 🎉';
                    document.getElementById('resultado').style.color = '#4CAF50';
                    finalizarJuego();
                }
            }
        });
    });

    // Botón "Reiniciar"
    document.getElementById('reiniciar')?.addEventListener('click', () => {
        iniciarJuego();
    });

    // Botón "Pausar"
    document.getElementById('pausa')?.addEventListener('click', () => {
        const pausaBtn = document.getElementById('pausa');
        // Solo permitir pausa si el juego NO ha finalizado (botón jugar está deshabilitado)
        if (document.getElementById('jugar').disabled) {
            juegoPausado = !juegoPausado;
            if (juegoPausado) {
                clearInterval(intervalo);
                pausaBtn.textContent = 'Reanudar';
            } else {
                cronometro();
                pausaBtn.textContent = 'Pausa';
            }
        }
    });

    // Botón "Actualizar"
    document.getElementById('actualizar')?.addEventListener('click', () => {
        location.reload();
    });

    // Evento para el botón "Jugar"
    document.getElementById('jugar')?.addEventListener('click', iniciarJuego);

    // ==================================================
    // 💡 CAMBIO CLAVE: Iniciamos el juego con TODO el setup
    // ==================================================
    iniciarJuego(); 
});