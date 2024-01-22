
import random
from flask import Flask, jsonify

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:password@localhost:3306/greenbridgedb'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

@app.route('/start-module', methods=['POST'])
def startModule():
    idList = random.sample(range(1, 20), 5)
    dati=jsonify({'id_list': idList})
    return dati



if __name__ == '__main__':
    app.run(debug=True)