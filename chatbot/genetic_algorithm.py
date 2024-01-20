import numpy as np
import pygad
import sqlite3
import mysql.connector

conn = mysql.connector.connect(
    host='localhost',
    user='root',
    password='password',
    database='greenbridgedb'
)

cursor = conn.cursor()

def ga(id_cliente):
    indirizzoCliente = getIndirizzoCliente(id_cliente)
    return indirizzoCliente

def getIndirizzoCliente(id_cliente):
    query = "SELECT via, citta, provincia, civico FROM indirizzo_spedizione WHERE id_cliente = %s"
    cursor.execute(query, (id_cliente,))
    risultato = cursor.fetchone()
    if risultato:
        via, citta, provincia, civico = risultato
        indirizzoCliente = f"{via}, {citta}, {provincia}, {civico}, Italia"
        return indirizzoCliente
    else:
        return None

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

def calcolamediaRecensioni(agricoltore_id):

    query = "SELECT AVG(voto) FROM recensione_agricoltore WHERE id_agricoltore = %s"
    cursor.execute(query, (agricoltore_id,))
    media = cursor.fetchone()[0]
    return media

def getIndirizzoAgricoltore(agricoltore_id):
    query = "SELECT indirizzo_bottega FROM agricoltore WHERE id = %s"
    cursor.execute(query, (agricoltore_id,))
    indirizzo = cursor.fetchone()[0]
    return indirizzo

def getNumAgricoltori():
    query = "SELECT COUNT(*) FROM agricoltore"
    cursor.execute(query)
    num_agricoltori = cursor.fetchone()
    return num_agricoltori


class Agricoltore:
    def __init__(self, agricoltore_id):
        self.id = agricoltore_id
        self.indirizzo = getIndirizzoAgricoltore(agricoltore_id)
        self.numOrdini = calcolanumOrdini(agricoltore_id)
        self.numCertificati = calcolanumCertificati(agricoltore_id)
        self.mediaRecensioni = calcolamediaRecensioni(agricoltore_id)

def main():
    numAgricoltori = getNumAgricoltori()
    agricoltori = []
    for agricoltore_id in range(1, numAgricoltori[0] + 1):
        nuovoagricoltore = Agricoltore(agricoltore_id)
        agricoltori.append(nuovoagricoltore)
    for agricoltore in agricoltori:
        print(f"id: {agricoltore.id}, indirizzo: {agricoltore.indirizzo}, numOrdini: {agricoltore.numOrdini}, numCertificati: {agricoltore.numCertificati}, mediaRecensioni: {agricoltore.mediaRecensioni}")

    print("\nIndirizzo cliente:", ga(15))


if __name__ == "__main__":
    main()

'''  
certificazioni_ecosostenibili = np.array([0.8, 0.9, 0.7, 0.6, 0.5])
media_recensioni = np.array([4.5, 4.2, 4.8, 4.0, 3.9])
preferenze_cliente = np.array([0.6, 0.7, 0.5, 0.8, 0.9])


# Define the target function (fitness function)

    def fitness_function(solution, solution_index):
        # Extract weights from the solution
        w1, w2, w3, w4 = solution

        # Calculate the fitness using the given formula
        fitness = np.sum(w1 * ordini_agricoltore +
                     w2 * certificazioni_ecosostenibili +
                     w3 * media_recensioni +
                     w4 * distanza_cliente)

        return fitness


# Create an instance of the pygad.GA class
ga_instance = pygad.GA(num_generations=50,
                       num_parents_mating=4,
                       fitness_func=fitness_function,
                       sol_per_pop=10,
                       num_genes=4,  # Four genes for weights w1, w2, w3, and w4
                       gene_type=float,
                       gene_space=[(-1, 1)] * 4,  # Assuming weights can be in the range [-1, 1]

                       parent_selection_type="tournament",
                       crossover_type="single_point",
                       mutation_type="random",
                       mutation_percent_genes=10)

# Run the genetic algorithm
ga_instance.run()

# Get the best solution found by the genetic algorithm
solution, fitness = ga_instance.output_dict['last_generation']['best_solution'], ga_instance.output_dict['last_generation']['best_solution_fitness']

# Print the result
print("Best solution (weights):", solution)
print("Fitness value:", fitness)
'''