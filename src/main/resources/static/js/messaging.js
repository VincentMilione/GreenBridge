document.addEventListener('DOMContentLoaded', function () {
    const chatbot = {
        respondToCommand: function (command) {
            let bot=this;
            this.sendMessage(command, 'messages__item--operator');
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
                        //bot.sendMessage(error.message,'messages__item--visitor');
                        // Visualizza un messaggio di errore personalizzato se lo stato è BAD_REQUEST
                        if (error.response && error.response.status === 400) {
                            console.error('Messaggio di errore dal server:', error.response.data);
                            // Visualizza il messaggio di errore ricevuto dal server
                            bot.sendMessage(error.response.data, 'messages__item--visitor');
                        }
                    });
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

    function submitMessage() {
        const command = input.value.trim();
        if (command !== '') {
            chatbot.respondToCommand(command);
            input.value = '';
        }
    }

    input.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            submitMessage();
        }
    });

    sendButton.addEventListener('click', submitMessage);
});

/**
function submitForm(command) {
    let bot=this;
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
}*/
