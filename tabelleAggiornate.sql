create database greenbridgedb;
use greenbridgedb;
create table portafoglio(
id int primary key auto_increment,
credito float,
limite_massimo int default 5000
);

create table agricoltore(
id int primary key auto_increment,
nome varchar(40) not null,
email varchar(50) not null,
pwd varchar(16) not null,
nome_bottega varchar(255) not null,
indirizzo_bottega varchar(255) not null,  
id_portafoglio int,
foreign key(id_portafoglio) references portafoglio(id)
ON update cascade
on delete cascade
);

create table amministratore(
id int primary key auto_increment,
username varchar(10) not null,
pwd varchar(16) not null
);

create table certificato(
id int primary key auto_increment,
nome varchar(100) not null,
data_scadenza date not null,
scansione mediumblob not null,
id_agricoltore int,
foreign key(id_agricoltore) references agricoltore (id)
ON UPDATE CASCADE
ON DELETE CASCADE,
id_admin int NULL,
foreign key(id_admin) references amministratore (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

create table prodotto(
id int primary key AUTO_INCREMENT,
nome varchar(20) not null,
origine varchar(20) not null,
immagine mediumblob,
formato_vendita float not null,
prezzo_kg float not null,
prezzo_vendita float not null,
quantita_disp float not null,
lotto int,
descrizione varchar(100),
acquistabile bool default 1,
id_agricoltore int,
foreign key(id_agricoltore) references agricoltore(id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

create table cliente(
id int primary key auto_increment,
nome varchar(30) not null,
cognome varchar(30) not null,
pwd varchar(16) not null,
data_nascita date not null,
email varchar(30) not null
);

create table ordine(
id int primary key auto_increment,
importo float not null,
data_ordine date not null,
pagamento varchar(20),
id_cliente int NULL,
id_indirizzo int,
stato int,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete set null,
id_agricoltore int NULL,
foreign key(id_agricoltore) references agricoltore(id)
on update cascade
on delete set null
);

create table prodotti_ordine(
id int primary key auto_increment,
id_prodotto int,
id_ordine int,
kg_acquistati int,
prezzo_kg float,
foreign key(id_prodotto) references prodotto(id)
on update cascade
on delete no action,
foreign key(id_ordine) references ordine(id)
on update cascade
on delete cascade
);

create table fattura(
id int primary key auto_increment,
data_emissione date not null,
id_ordine int,
foreign key(id_ordine) references ordine(id)
on update cascade
on delete no action,
id_cliente int,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete no action
);

create table recensione_agricoltore(
id int primary key auto_increment,
id_cliente int,
id_agricoltore int,
descrizione varchar(200) not null,
voto int not null,
foreign key(id_agricoltore) references agricoltore(id)
on update cascade
on delete cascade,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete cascade
);

create table carrello_cliente(
id int primary key auto_increment,
id_cliente int,
id_prodotto int,
kg_richiesti int not null,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete cascade,
foreign key(id_prodotto) references prodotto(id)
on update cascade
on delete cascade
);

create table recensione_prodotti(
id int primary key auto_increment, 
id_cliente int,
id_prodotto int,
descrizione varchar(200) not null,
voto int not null,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete cascade,
foreign key(id_prodotto) references prodotto(id)
on update cascade
on delete cascade
);

create table indirizzo_spedizione(
id_cliente int,
id int primary key auto_increment,
civico int not null,
via varchar(50) not null,
cap int not null,
citta varchar(20) not null,
provincia varchar(20) not null,
foreign key(id_cliente) references cliente(id)
on update cascade
on delete cascade
);
