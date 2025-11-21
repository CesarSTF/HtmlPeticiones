# formularios/forms_generador.py

class GeneradorForm:
    def __init__(self, data):
        self.modelo = data.get('modelo')
        self.costo = data.get('costo')
        self.consumo_comustible = data.get('consumoComustible')
        self.energia_generada = data.get('enegeriaGenerada')
        self.uso = data.get('uso')

    def es_valido(self):
        def es_decimal(valor):
            try:
                float(valor)
                return True
            except ValueError:
                return False

        return all([
            self.modelo is not None,
            self.costo is not None and es_decimal(self.costo),
            self.consumo_comustible is not None and es_decimal(self.consumo_comustible),
            self.energia_generada is not None and es_decimal(self.energia_generada),
            self.uso in ["DOMESTICO", "COMERCIAL"]
        ])