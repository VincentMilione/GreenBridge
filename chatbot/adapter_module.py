import requests

class SpringBootAdapter:
    def __init__(self, base_url):
        self.base_url = base_url

    def get_data_from_db(self):
        # Implementa la logica per ottenere i dati dal database tramite il server Spring Boot
        url = f"{self.base_url}/get_data_from_db"
        response = requests.get(url)

        if response.status_code == 200:
            return response.json()
        else:
            raise Exception(f"Errore durante la comunicazione con il server Spring Boot: {response.status_code}")

    def send_command_to_flask(self, command):
        # Invia un comando al modulo Flask
        url = f"{self.base_url}/receive_command_from_spring_boot"
        response = requests.post(url, json={"command": command})

        if response.status_code != 200:
            raise Exception(f"Errore durante l'invio del comando al modulo Flask: {response.status_code}")
