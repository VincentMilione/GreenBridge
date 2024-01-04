document.addEventListener('DOMContentLoaded', function () {
    const chatbot = {
        respondToCommand: function (command) {
            this.sendMessage(command, 'messages__item--operator');
            switch (command) {
                case '/start':
                    this.sendMessage('Ecco una lista di agricoltori da noi consigliati:', 'messages__item--visitor');
                    break;
                // Aggiungi altri comandi e risposte qui
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

  /*  input.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            const command = input.value.trim();
            if (command !== '') {
                chatbot.respondToCommand(command);
                input.value = '';
            }
        }
    });
});*/

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
//dddd
    sendButton.addEventListener('click', sendMessage);
});