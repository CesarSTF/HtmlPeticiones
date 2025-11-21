# formularios/forms_familia.py

class FamiliaForm:
    def __init__(self, data):
        self.nroIntegrantes = data.get('nroIntegrantes')
        self.nombreFamilia = data.get('nombreFamilia')
        self.generadorId = data.get('generadorId')

    def es_valido(self):
        return all([
            self.nroIntegrantes is not None and self.nroIntegrantes.isdigit(),
            self.nombreFamilia is not None and not self.nombreFamilia.isdigit(),
            self.generadorId is not None and self.generadorId.isdigit()
        ])
