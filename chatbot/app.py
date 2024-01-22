import requests
from flask import Flask, request, jsonify
from entities import db

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:password@localhost:3306/greenbridgedb'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db.init_app(app)

@app.route('/start-module', methods=['POST'])
def startModule():
    #Qui va l'effettiva chiamata al modulo
    dati_da_inviare = "{'key': 'value'}"
    send_data_to_spring_boot(dati_da_inviare)

    return jsonify(
        {
            "code": 200,
            "status": "success",
            'Message': 'Data received correctly'}
    )

def send_data_to_spring_boot(dati):
    url_spring_boot = "http://localhost:8080/api/ricevi-dati"

    # Esegui la chiamata POST a Spring Boot
    response = requests.post(url_spring_boot, data=dati)

    if response.status_code == 200:
        print("Dati inviati con successo a Spring Boot")
    else:
        print("Errore nell'invio dei dati a Spring Boot")

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)