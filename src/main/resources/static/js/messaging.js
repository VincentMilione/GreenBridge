document.addEventListener('DOMContentLoaded', function () {
    const chatbot = {
        respondToCommand: function (command) {
            let bot=this;
            this.sendMessage(command, 'messages__item--operator');
            switch (command) {
                case '/start':
                    submitForm("/api/sendDataToFlask").done(function (){
                        bot.sendMessage('Ecco una lista di agricoltori da noi consigliati:', 'messages__item--visitor');})
                    break;
                default:
                    this.sendMessage('Comando non riconosciuto', 'messages__item--visitor');
            }
        },
        sendMessage: function (message, className) {
            const chat = document.getElementById('chat');
            const newMessage = document.createElement('div');
            newMessage.textContent = message;
            newMessage.classList.add('messages__item', className);
            chat.appendChild(newMessage);
        }
    };

    const input = document.getElementById('input');
    const sendButton = document.getElementById('sendButton');

    function sendMessage() {
        const command = input.value.trim();
        if (command !== '') {
            chatbot.respondToCommand(command);
            input.value = '';
        }
    }

    input.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });

    sendButton.addEventListener('click', sendMessage);
});

function inviaComandoAlServer(command) {
    // Definisci l'URL del tuo endpoint Spring Boot
    const url = '/api/executeCommand';

    // Effettua la richiesta utilizzando fetch
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ command: command })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore durante la richiesta al server');
            }
            return response.json();
        })
        .then(data => {
            // Gestisci la risposta del server
            console.log('Risposta dal server:', data);
            // Visualizza il messaggio ricevuto dal server
            bot.sendMessage(data, 'messages__item--visitor');
        })
        .catch(error => {
            // Gestisci gli errori durante la richiesta
            console.error('Errore:', error.message);
            // Visualizza un messaggio di errore personalizzato se lo stato è BAD_REQUEST
            if (error.response && error.response.status === 400) {
                console.error('Messaggio di errore dal server:', error.response.data);
                // Visualizza il messaggio di errore ricevuto dal server
                bot.sendMessage(error.response.data, 'messages__item--visitor');
            }
        });
}
