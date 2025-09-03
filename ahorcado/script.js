// ----------------------------
// VARIABLES PRINCIPALES
// ----------------------------
 
// selectedWord almacena la palabra que se selecciona aleatoriamente para adivinar.
// Inicialmente está vacía hasta que el jugador presiona iniciar.
let selectedWord = "";
 
// guessedLetters almacena las letras que el jugador ha adivinado correctamente.
// Se usa para mostrar la palabra y validar aciertos.
let guessedLetters = [];
 
// incorrectGuesses lleva la cuenta de las letras incorrectas seleccionadas.
// Esto sirve para dibujar partes del muñeco del ahorcado.
let incorrectGuesses = 0;
 
// maxAttempts define la cantidad máxima de errores permitidos.
// Si se excede, el jugador pierde automáticamente.
const maxAttempts = 6;
 
// gameActive indica si el juego está activo o en pausa.
// Esto evita que el jugador pueda seleccionar letras cuando no debería.
let gameActive = false;
 
// timer almacena los segundos transcurridos desde el inicio del juego.
// Se usa para mostrar un temporizador en pantalla.
let timer = 0;
 
// timerInterval controla la ejecución del temporizador con setInterval.
// Se inicializa con 60 pero luego se reemplaza con la función de actualización.
let timerInterval = 60;
 
// ----------------------------
// ELEMENTOS DEL HTML
// ----------------------------
 
// wordDisplay es donde se muestran los guiones o letras adivinadas de la palabra.
const wordDisplay = document.getElementById('word-display');
 
// keyboard es el contenedor de los botones de letras que el jugador puede presionar.
const keyboard = document.getElementById('keyboard');
 
// messageBox muestra mensajes al jugador, como aciertos, errores o fin del juego.
const messageBox = document.getElementById('message-box');
 
// cluesList es la lista de pistas que ayudan al jugador a adivinar la palabra.
const cluesList = document.getElementById('clues-list');
 
// startBtn inicia una nueva partida.
const startBtn = document.getElementById('start-btn');
 
// restartBtn reinicia la partida actual o comienza una nueva.
const restartBtn = document.getElementById('restart-btn');
 
// pauseBtn pausa o continúa el juego.
const pauseBtn = document.getElementById('pause-btn');
 
// exitBtn permite salir o reiniciar el juego completamente.
const exitBtn = document.getElementById('exit-btn');
 
// timerDisplay muestra el tiempo transcurrido del juego.
const timerDisplay = document.getElementById('timer-display');
 
// canvas es el área donde se dibuja el muñeco del ahorcado.
const canvas = document.getElementById('hangman-canvas');
 
// ctx es el contexto 2D del canvas que permite dibujar.
const ctx = canvas.getContext('2d');
 
// ----------------------------
// EVENTO PRINCIPAL DE CARGA
// ----------------------------
 
document.addEventListener('DOMContentLoaded', () => {
 
    // words es un arreglo de objetos que contiene la palabra y sus pistas.
    // Cada objeto tiene la palabra y un arreglo con 3 pistas que ayudan a adivinarla.
    const words = [
        { word: "computadora", clues: ["Objeto tecnológico", "Se usa para trabajar y jugar", "Tiene un teclado"] },
        { word: "paralelepipedo", clues: ["Figura geométrica", "Tiene 6 caras", "Parecido a una caja"] },
        { word: "biblioteca", clues: ["Lugar público", "Tiene muchos libros", "Es silenciosa"] },
        { word: "elefante", clues: ["Animal mamífero", "Tiene trompa larga", "Es muy grande y gris"] },
        { word: "termometro", clues: ["Instrumento", "Mide la temperatura", "Puede ser digital o de mercurio"] },
        { word: "murciélago", clues: ["Animal mamífero", "Tiene alas", "Es pequeño y negro"] },
        { word: "zarigueya", clues: ["Animal mamífero", "Tiene cola grande", "Es pequeño y gris, sale en películas"] },
        { word: "escritorio", clues: ["Cuadrado", "Sirve para colocar cuadernos", "Lo usan los estudiantes"] }
    ];
 
    // hangmanParts contiene funciones que dibujan cada parte del muñeco.
    // Se van llamando según la cantidad de errores del jugador.
    const hangmanParts = [
        () => { ctx.beginPath(); ctx.arc(100, 70, 20, 0, Math.PI * 2, true); ctx.stroke(); }, // Cabeza
        () => { ctx.beginPath(); ctx.moveTo(100, 90); ctx.lineTo(100, 150); ctx.stroke(); }, // Cuerpo
        () => { ctx.beginPath(); ctx.moveTo(100, 110); ctx.lineTo(60, 130); ctx.stroke(); }, // Brazo izquierdo
        () => { ctx.beginPath(); ctx.moveTo(100, 110); ctx.lineTo(140, 130); ctx.stroke(); }, // Brazo derecho
        () => { ctx.beginPath(); ctx.moveTo(100, 150); ctx.lineTo(60, 180); ctx.stroke(); }, // Pierna izquierda
        () => { ctx.beginPath(); ctx.moveTo(100, 150); ctx.lineTo(140, 180); ctx.stroke(); }  // Pierna derecha
    ];
 
    // ----------------------------
    // FUNCIONES PRINCIPALES
    // ----------------------------
 
    // drawHangmanBase dibuja la horca base donde se colgará el muñeco.
    // Se limpia el canvas y se dibujan la base, el poste, la viga y la cuerda.
    // Esta función se llama al inicio de cada partida para mostrar la horca vacía.
    function drawHangmanBase() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.lineWidth = 4;
        ctx.strokeStyle = '#212121';
 
        ctx.beginPath(); ctx.moveTo(10, 240); ctx.lineTo(190, 240); ctx.stroke(); // Base
        ctx.beginPath(); ctx.moveTo(40, 240); ctx.lineTo(40, 20); ctx.stroke();   // Poste
        ctx.beginPath(); ctx.moveTo(40, 20); ctx.lineTo(100, 20); ctx.stroke();  // Viga
        ctx.beginPath(); ctx.moveTo(100, 20); ctx.lineTo(100, 50); ctx.stroke(); // Cuerda
    }
 
    // drawHangman dibuja una parte del muñeco según los errores cometidos.
    // Se llama cada vez que el jugador selecciona una letra incorrecta.
    // Esto permite que el jugador vea visualmente su progreso y errores.
    function drawHangman() {
        if (incorrectGuesses > 0 && incorrectGuesses <= maxAttempts) {
            hangmanParts[incorrectGuesses - 1]();
        }
    }
 
    // updateWordDisplay actualiza la palabra en pantalla mostrando letras adivinadas y guiones.
    // Recorre cada letra de la palabra y verifica si está en guessedLetters.
    // Devuelve true si la palabra completa fue adivinada o false si faltan letras.
    function updateWordDisplay() {
        let display = "";
        let solved = true;
        for (const letter of selectedWord) {
            if (guessedLetters.includes(letter)) {
                display += letter.toUpperCase();
            } else {
                display += "_";
                solved = false;
            }
            display += " ";
        }
        wordDisplay.textContent = display.trim();
        return solved;
    }
 
    // updateCluesDisplay muestra las pistas de la palabra actual en la lista HTML.
    // Busca la palabra seleccionada en el arreglo words y obtiene sus pistas.
    // Inserta cada pista como un elemento <li> para que se muestren en pantalla.
    function updateCluesDisplay() {
        const wordData = words.find(item => item.word === selectedWord);
        cluesList.innerHTML = wordData.clues.map(clue => `<li>${clue}</li>`).join('');
    }
 
    // generateKeyboard crea botones para cada letra del alfabeto.
    // Cada botón tiene un evento click que llama a handleGuess con la letra correspondiente.
    // Esto permite que el jugador seleccione letras directamente desde la interfaz.
    function generateKeyboard() {
        keyboard.innerHTML = '';
        const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑ";
        for (const letter of letters) {
            const button = document.createElement('button');
            button.textContent = letter;
            button.addEventListener('click', () => handleGuess(letter.toLowerCase()));
            keyboard.appendChild(button);
        }
    }
 
    // handleGuess procesa la letra que selecciona el jugador.
    // Si la letra está en la palabra, se agrega a guessedLetters; si no, aumenta incorrectGuesses.
    // También actualiza mensajes en pantalla y dibuja el muñeco si hay error.
    function handleGuess(letter) {
        if (!gameActive) return;
 
        const button = Array.from(keyboard.children).find(btn => btn.textContent.toLowerCase() === letter);
        if (button) button.disabled = true;
 
        if (selectedWord.includes(letter)) {
            guessedLetters.push(letter);
            messageBox.textContent = `¡La letra "${letter.toUpperCase()}" es correcta!`;
            messageBox.style.color = 'var(--correct-guess)';
        } else {
            incorrectGuesses++;
            messageBox.textContent = `La letra "${letter.toUpperCase()}" es incorrecta.`;
            messageBox.style.color = 'var(--incorrect-guess)';
            drawHangman();
        }
 
        const solved = updateWordDisplay();
        checkGameState(solved);
    }
 
    // checkGameState verifica si la partida terminó por acierto o por alcanzar el máximo de errores.
    function checkGameState(solved) {
        if (solved) endGame(true);
        else if (incorrectGuesses >= maxAttempts) endGame(false);
    }
 
    // endGame finaliza la partida mostrando mensaje y desactivando el teclado.
    function endGame(isWin) {
        gameActive = false;
        clearInterval(timerInterval);
        keyboard.querySelectorAll('button').forEach(btn => btn.disabled = true);
 
        if (isWin) {
            messageBox.textContent = `¡Felicidades! Adivinaste la palabra: "${selectedWord.toUpperCase()}"`;
            messageBox.style.color = 'var(--correct-guess)';
        } else {
            messageBox.textContent = `¡Perdiste! La palabra era: "${selectedWord.toUpperCase()}"`;
            messageBox.style.color = 'var(--incorrect-guess)';
        }
 
        startBtn.disabled = false;
        restartBtn.disabled = false;
        pauseBtn.disabled = true;
    }
 
    // updateTimer incrementa y muestra el tiempo transcurrido del juego.
    function updateTimer() {
        timer++;
        const minutes = Math.floor(timer / 60).toString().padStart(2, '0');
        const seconds = (timer % 60).toString().padStart(2, '0');
        timerDisplay.textContent = `${minutes}:${seconds}`;
    }
 
    // startGame inicia una nueva partida y resetea variables.
    function startGame() {
        const randomIndex = Math.floor(Math.random() * words.length);
        selectedWord = words[randomIndex].word;
        guessedLetters = [];
        incorrectGuesses = 0;
        gameActive = true;
        timer = 0;
 
        messageBox.textContent = "¡Adivina la palabra!";
        messageBox.style.color = 'var(--primary-color)';
 
        updateCluesDisplay();
        updateWordDisplay();
        drawHangmanBase();
        generateKeyboard();
 
        startBtn.disabled = true;
        restartBtn.disabled = false;
        pauseBtn.disabled = false;
 
        clearInterval(timerInterval);
        timerInterval = setInterval(updateTimer, 1000);
    }
 
    // restartGame reinicia la partida llamando a startGame.
    function restartGame() {
        startGame();
    }
 
    // pauseGame pausa o continúa la partida según el estado actual.
    function pauseGame() {
        if (gameActive) {
            gameActive = false;
            clearInterval(timerInterval);
            pauseBtn.textContent = "Continuar";
            messageBox.textContent = "Juego en pausa. Presiona Continuar para seguir.";
            keyboard.querySelectorAll('button').forEach(btn => btn.disabled = true);
        } else {
            gameActive = true;
            timerInterval = setInterval(updateTimer, 1000);
            pauseBtn.textContent = "Pausa";
            messageBox.textContent = "¡Adivina la palabra!";
            keyboard.querySelectorAll('button').forEach(btn => {
                const letter = btn.textContent.toLowerCase();
                if (!guessedLetters.includes(letter)) btn.disabled = false;
            });
        }
    }
 
    // exitGame recarga la página para salir completamente del juego.
    function exitGame() {
        window.location.reload();
    }
 
    // ----------------------------
    // EVENTOS DE LOS BOTONES
    // ----------------------------
 
    startBtn.addEventListener('click', startGame);
    restartBtn.addEventListener('click', restartGame);
    pauseBtn.addEventListener('click', pauseGame);
    exitBtn.addEventListener('click', exitGame);
 
    // ----------------------------
    // INICIALIZACIÓN DEL JUEGO
    // ----------------------------
 
    generateKeyboard();
    drawHangmanBase();
});
