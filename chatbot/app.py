from flask import Flask, jsonify, request

app = Flask(__name__)


@app.route('/receive-data', methods=['POST'])
def receiveData():
    received_data = request.get_json()

    # operazioni di trattamento dei dati e invio dati al modulo AI

    # Invia una risposta
    return jsonify(
        {
            "code": 200,
            "status": "success",
            'Message': 'Data received correctly'}
    )


if __name__ == '__main__':
    app.run(debug=True)
