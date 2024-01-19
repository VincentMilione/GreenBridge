from flask import Flask, request, jsonify
from entities import db

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:8872.Giov@localhost:3306/greenbridgedb'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db.init_app(app)

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
    with app.app_context():
        db.create_all()
    app.run(debug=True)