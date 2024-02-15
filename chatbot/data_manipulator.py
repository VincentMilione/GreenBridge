import mysql.connector

conn = mysql.connector.connect(
    host='localhost',
    user='root',
    password='password',
    database='greenbridgedb'
)

cursor = conn.cursor()

def calcolanumOrdini(agricoltore_id):

    query = "SELECT COUNT(*) FROM ordine WHERE id_agricoltore = %s"
    cursor.execute(query, (agricoltore_id,))
    numordini = cursor.fetchone()[0]
    return numordini

def calcolanumCertificati(agricoltore_id):

    query = "SELECT COUNT(*) FROM certificato WHERE id_agricoltore = %s"
    cursor.execute(query, (agricoltore_id,))
    numCertificati = cursor.fetchone()[0]
    return numCertificati

def calcolamediaRecensioniAgr(agricoltore_id):

    query = "SELECT AVG(voto) FROM recensione_agricoltore WHERE id_agricoltore = %s"
    cursor.execute(query, (agricoltore_id,))
    media = float(cursor.fetchone()[0])
    return media

def calcolamediaRecensioniPro(agricoltore_id):

    query = "SELECT AVG(rp.voto) AS media_recensioni FROM recensione_prodotti rp INNER JOIN prodotto p ON rp.id_prodotto = p.id INNER JOIN agricoltore a ON p.id_agricoltore = a.id WHERE a.id = %s;"
    cursor.execute(query, (agricoltore_id,))
    media = float(cursor.fetchone() [0])

    return media

def getNumAgricoltori():
    query = "SELECT COUNT(*) FROM agricoltore"
    cursor.execute(query)
    num_agricoltori = cursor.fetchone() [0]
    return num_agricoltori
"""
def getnumProdotti(agricoltore_id, prodotto):
    query = "SELECT COUNT(*) AS num_prod FROM prodotto WHERE id_agricoltore = %s AND nome = %s;"
    cursor.execute(query, (agricoltore_id, prodotto,))
    num_prodotti = cursor.fetchone() [0]
    return num_prodotti """
def getFitness(id):
    return Agricoltore(id).fitness

class Agricoltore:
    def __init__(self, agricoltore_id): #, prodotto)
        self.id = agricoltore_id
        self.fitness = ((1.5*calcolanumOrdini(agricoltore_id)) +
                        (2*calcolanumCertificati(agricoltore_id)) +
                        (2*calcolamediaRecensioniAgr(agricoltore_id)) +
                        (1.5*calcolamediaRecensioniPro(agricoltore_id)) #+ (2*getnumProdotti(agricoltore_id,prodotto))
                        )
