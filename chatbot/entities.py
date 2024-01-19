from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class Agricoltore(db.Model):
    __tablename__ = 'agricoltore'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nome = db.Column(db.String(40), nullable=False)
    email = db.Column(db.String(50), nullable=False)
    pwd = db.Column(db.String(16), nullable=False)
    nome_bottega = db.Column(db.String(255), nullable=False)
    indirizzo_bottega = db.Column(db.String(255), nullable=False)
    id_portafoglio = db.Column(db.Integer, db.ForeignKey('portafoglio.id', onupdate='CASCADE', ondelete='CASCADE'))

class Cliente(db.Model):
    __tablename__ = 'cliente'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nome = db.Column(db.String(30), nullable=False)
    cognome = db.Column(db.String(30), nullable=False)
    pwd = db.Column(db.String(16), nullable=False)
    data_nascita = db.Column(db.Date, nullable=False)
    email = db.Column(db.String(30), nullable=False)

class Certificato(db.Model):
    __tablename__ = 'certificato'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nome = db.Column(db.String(100), nullable=False)
    data_scadenza = db.Column(db.Date, nullable=False)
    scansione = db.Column(db.LargeBinary, nullable=False)
    id_agricoltore = db.Column(db.Integer, db.ForeignKey('agricoltore.id', onupdate='CASCADE', ondelete='CASCADE'))

    agricoltore = db.relationship('Agricoltore', backref=db.backref('certificati', lazy=True))

class RecensioneAgricoltore(db.Model):
    __tablename__ = 'recensione_agricoltore'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_cliente = db.Column(db.Integer, db.ForeignKey('cliente.id', onupdate='CASCADE', ondelete='CASCADE'))
    id_agricoltore = db.Column(db.Integer, db.ForeignKey('agricoltore.id', onupdate='CASCADE', ondelete='CASCADE'))
    descrizione = db.Column(db.String(200), nullable=False)
    voto = db.Column(db.Integer, nullable=False)

    cliente = db.relationship('Cliente', backref=db.backref('recensioni_agricoltori', lazy=True))
    agricoltore = db.relationship('Agricoltore', backref=db.backref('recensioni', lazy=True))

class Ordine(db.Model):
    __tablename__ = 'ordine'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    importo = db.Column(db.Float, nullable=False)
    data_ordine = db.Column(db.Date, nullable=False)
    pagamento = db.Column(db.String(20))
    id_cliente = db.Column(db.Integer, db.ForeignKey('cliente.id', onupdate='CASCADE', ondelete='SET NULL'))
    id_agricoltore = db.Column(db.Integer, db.ForeignKey('agricoltore.id', onupdate='CASCADE', ondelete='SET NULL'))
    id_indirizzo = db.Column(db.Integer, nullable=False)

    cliente = db.relationship('Cliente', backref=db.backref('ordini', lazy=True))
    agricoltore = db.relationship('Agricoltore', backref=db.backref('ordini', lazy=True))

class IndirizzoSpedizione(db.Model):
    __tablename__ = 'indirizzo_spedizione'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_cliente = db.Column(db.Integer, db.ForeignKey('cliente.id', onupdate='CASCADE', ondelete='CASCADE'))
    civico = db.Column(db.Integer, nullable=False)
    via = db.Column(db.String(50), nullable=False)
    cap = db.Column(db.Integer, nullable=False)
    citta = db.Column(db.String(20), nullable=False)
    provincia = db.Column(db.String(20), nullable=False)

    cliente = db.relationship('Cliente', backref=db.backref('indirizzi_spedizione', lazy=True))
