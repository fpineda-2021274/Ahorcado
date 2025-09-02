let selectedWord = "";
let guessedLetters = [];
let incorrectGuesses = 0;
const maxAttempts = 6;
let gameActive = false;
let timer = 60;
let timerInterval = null;

const wordDisplay = document.getElementById('word-display');
const keyboard = document.getElementById('keyboard');
const messageBox = document.getElementById('message-box');
const cluesList = document.getElementById('clues-list');
const startBtn = document.getElementById('start-btn');
const restartBtn = document.getElementById('restart-btn');
const pauseBtn = document.getElementById('pause-btn');
const exitBtn = document.getElementById('exit-btn');
const timerDisplay = document.getElementById('timer-display');
const canvas = document.getElementById('hangman-canvas');
const ctx = canvas.getContext('2d');

document.addEventListener('DOMContentLoaded', () => {
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

    const hangmanParts = [
        () => { ctx.beginPath(); ctx.arc(100, 70, 20, 0, Math.PI * 2, true); ctx.stroke(); }, // Cabeza
        () => { ctx.beginPath(); ctx.moveTo(100, 90); ctx.lineTo(100, 150); ctx.stroke(); }, // Cuerpo
        () => { ctx.beginPath(); ctx.moveTo(100, 110); ctx.lineTo(60, 130); ctx.stroke(); }, // Brazo izq
        () => { ctx.beginPath(); ctx.moveTo(100, 110); ctx.lineTo(140, 130); ctx.stroke(); }, // Brazo der
        () => { ctx.beginPath(); ctx.moveTo(100, 150); ctx.lineTo(60, 180); ctx.stroke(); }, // Pierna izq
        () => { ctx.beginPath(); ctx.moveTo(100, 150); ctx.lineTo(140, 180); ctx.stroke(); }  // Pierna der
    ];

    function drawHangmanBase() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.lineWidth = 4;
        ctx.strokeStyle = '#212121';

        ctx.beginPath(); ctx.moveTo(10, 240); ctx.lineTo(190, 240); ctx.stroke(); // Base
        ctx.beginPath(); ctx.moveTo(40, 240); ctx.lineTo(40, 20); ctx.stroke();   // Poste
        ctx.beginPath(); ctx.moveTo(40, 20); ctx.lineTo(100, 20); ctx.stroke();  // Viga
        ctx.beginPath(); ctx.moveTo(100, 20); ctx.lineTo(100, 50); ctx.stroke(); // Cuerda
    }

    function drawHangman() {
        if (incorrectGuesses > 0 && incorrectGuesses <= maxAttempts) {
            hangmanParts[incorrectGuesses - 1]();
        }
    }

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

    function updateCluesDisplay() {
        const wordData = words.find(item => item.word === selectedWord);
        cluesList.innerHTML = wordData.clues.map(clue => `<li>${clue}</li>`).join('');
    }

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

    function checkGameState(solved) {
        if (solved) endGame(true);
        else if (incorrectGuesses >= maxAttempts) endGame(false);
    }

    function endGame(isWin) {
        gameActive = false;
        clearInterval(timerInterval);
        keyboard.querySelectorAll('button').forEach(btn => btn.disabled = true);

        if (isWin) {
            messageBox.textContent = `¡Felicidades! Adivinaste la palabra: "${selectedWord.toUpperCase()}".`;
            messageBox.style.color = 'var(--correct-guess)';
        } else {
            messageBox.textContent = `¡Perdiste! La palabra era: "${selectedWord.toUpperCase()}".`;
            messageBox.style.color = 'var(--incorrect-guess)';
        }

        startBtn.disabled = false;
        restartBtn.disabled = false;
        pauseBtn.disabled = true;
    }

    function updateTimer() {
        timer++;
        const minutes = Math.floor(timer / 60).toString().padStart(2, '0');
        const seconds = (timer % 60).toString().padStart(2, '0');
        timerDisplay.textContent = `${minutes}:${seconds}`;
    }

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

    function restartGame() {
        startGame();
    }

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

    function exitGame() {
        window.location.reload();
    }

    startBtn.addEventListener('click', startGame);
    restartBtn.addEventListener('click', restartGame);
    pauseBtn.addEventListener('click', pauseGame);
    exitBtn.addEventListener('click', exitGame);

    // Inicialización
    generateKeyboard();
    drawHangmanBase();
});
