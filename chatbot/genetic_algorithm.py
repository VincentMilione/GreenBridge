from data_manipulator import *
import pygad
import numpy

# Definizione della funzione di fitness
def fitness_func_wrapper(agricoltori):
    def fitness_func(ga_instance, solution, solution_idx):
        agr1 = agricoltori[int (solution[0])]
        agr2 = agricoltori[int (solution[1])]
        agr3 = agricoltori[int (solution[2])]
        agr4 = agricoltori[int (solution[3])]

        fitness = agr1.fitness + agr2.fitness + agr3.fitness + agr4.fitness
        return fitness

    return fitness_func

def main():
    #nomeProdotto = "Peperoni"
    #creazione array di agricoltori
    numAgricoltori = getNumAgricoltori()
    agricoltori = []
    for agricoltore_id in range (1, numAgricoltori + 1):
        nuovoagricoltore = Agricoltore(agricoltore_id)#, nomeProdotto)
        agricoltori.append(nuovoagricoltore)
    # Creazione della popolazione iniziale
    population_size = numAgricoltori
    ids = list(range(0, numAgricoltori))  # Genera una lista di numeri da 1 a 200
    ga_data = [ids[i:i+4] for i in range(0, len(ids), 4)] #Gli ID vengono inseriti 4 alla volta
    num_genes = 4
    # Creazione di un oggetto PyGAD
    ga_instance = pygad.GA(num_generations=15,
                           num_parents_mating=10,
                           fitness_func=fitness_func_wrapper(agricoltori=agricoltori),
                           num_genes=num_genes,
                           initial_population=ga_data,
                           parent_selection_type = "sss", #Steady State Selection
                           mutation_probability=0.4,
                           mutation_type = "random",
                           crossover_type="two_points",
                           keep_elitism = 1,
                           gene_type = int,
                           stop_criteria=["saturate_4"], #L'algoritmo si ferma se la fitness non cambia per 5 generazioni consecutive
                           allow_duplicate_genes=False, #Geni duplicati non ammessi
                           suppress_warnings = True,
                           )

    # Avvio dell'ottimizzazione
    ga_instance.run()
    # Ottieni il miglior individuo dopo l'ottimizzazione
    solution, solution_fitness, solution_idx = ga_instance.best_solution()
    print("Miglior soluzioni:", solution)
    print("Fitness della migliore soluzione:", solution_fitness)
    return solution



if __name__ == "__main__":
    main()