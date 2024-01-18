from flask import Flask, jsonify, request
from adapter_module import SpringBootAdapter

app = Flask(__name__)

# Configura l'URL del server Spring Boot
spring_boot_url = "http://localhost:8080"
adapter = SpringBootAdapter(spring_boot_url)

@app.route('/get_data_from_db', methods=['GET'])
def get_data_from_db():
    try:
        # Usa l'adapter per ottenere i dati dal database tramite il server Spring Boot
        data_from_db = adapter.get_data_from_db()

        # Elabora i dati nel modulo Flask se necessario
        processed_data = process_data_locally(data_from_db)

        return jsonify({"processed_data": processed_data})

    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/receive_command_from_spring_boot', methods=['POST'])
def receive_command_from_spring_boot():
    try:
        # Ricevi il comando dal server Spring Boot
        command = request.json.get('command')

        # Esegui l'azione associata al comando
        result = perform_command_action(command)

        return jsonify({"result": result})

    except Exception as e:
        return jsonify({"error": str(e)}), 500

def process_data_locally(data_from_db):
    # Logica di elaborazione dei dati nel modulo Flask
    # ...

    # Restituisci i dati elaborati
    return {"result": "Dati elaborati localmente"}

def perform_command_action(command):
    # Logica per eseguire un'azione in base al comando ricevuto
    # ...

    # Restituisci un risultato
    return {"result": "Azione eseguita con successo"}

if __name__ == '__main__':
    app.run(debug=True)
