document.addEventListener('DOMContentLoaded', function () {
    const chatbot = {
        respondToCommand: function (command) {
            let bot=this;
            this.sendMessage(command, 'messages__item--operator');
            /** switch (command) {
                case '/start':
                    submitForm("/sendDataToFlask", "start").done(function (){
                        bot.sendMessage('Ecco una lista di agricoltori da noi consigliati:', 'messages__item--visitor');})
                    break;
                default:
                    this.sendMessage('Comando non riconosciuto', 'messages__item--visitor');
            }*/
            if(command == "/start"){
                submitForm("/send_command_to_flaskToFlask", "start").done(function (){
                    bot.sendMessage('Ecco una lista di agricoltori da noi consigliati:', 'messages__item--visitor');})
            } else if (command.includes("/refine")){
                let product=command.substring(7);
                submitForm("/send_command_to_flaskToFlask", product).done(function (){
                    bot.sendMessage('Ecco una lista di agricoltori da noi consigliati:', 'messages__item--visitor');})
            } else {
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

function submitForm(url, command) {
    if(command=='start'){
        return $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            command: 'start'
        });
    } else {
        return $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            command: command
        });
    }
}