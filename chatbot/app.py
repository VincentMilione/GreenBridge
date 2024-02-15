from genetic_algorithm import *
import random
from flask import Flask, jsonify

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:password@localhost:3306/greenbridgedb'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False


@app.route('/start-module', methods=['POST'])
def startModule():
    """
Questa funzione gestisce la richiesta POST per avviare il modulo.
Genera una lista casuale di ID agricoltori e restituisce la risposta JSON contenente l'elenco.

Returns:
    flask.Response: Risposta JSON contenente l'elenco casuale di ID agricoltori generato.
"""
    # Genera una lista casuale di ID agricoltori
    idList = random.sample(range(1, 20), 5)
    #solutions = main()
    #idList = solutions.tolist()

    # Crea una risposta JSON contenente l'elenco casuale di ID agricoltori
    dati = jsonify({'id_list': list(idList)})
    return dati


if __name__ == '__main__':
    app.run(debug=True)