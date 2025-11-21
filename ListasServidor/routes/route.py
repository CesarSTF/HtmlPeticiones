from flask import Blueprint, request, render_template, redirect, url_for
import requests
from formularios.forms_generador import GeneradorForm
from formularios.forms_familia import FamiliaForm

router = Blueprint('router', __name__)
##############################################################################################################
BACKEND_URL_GENERADOR = "http://localhost:8090/api/generador"
BACKEND_URL_FAMILIA = "http://localhost:8090/api/familia"
BACKEND_URL_HISTORIAL = "http://localhost:8090/api/historial/all"
##############################################################################################################
@router.route('/')
def home():
    r_familias = requests.get(f"{BACKEND_URL_FAMILIA}/all")
    familias = r_familias.json().get('data', [])
    r = requests.get(f"{BACKEND_URL_GENERADOR}/all")
    generadores = r.json().get('data', [])
    return render_template('index.html', generadores=generadores, familias=familias)

@router.route('/admin')
def admin():
    return render_template('http_test.html')
##############################################################################################################
@router.route('/generador/<int:id>')
def generador(id):
    r = requests.get(f"{BACKEND_URL_GENERADOR}/get/{id}")
    return render_template('generador.html', generador=r.json().get('data'))  


@router.route('/generador/lista')
def list_generador():
    r = requests.get(f"{BACKEND_URL_GENERADOR}/all")
    data = r.json()
    return render_template('fragmento/lista.html', generadores=data['data'])  


@router.route('/generador/nuevo')
def save_generador():
    return render_template('fragmento/fromGenerador.html')


@router.route('/generador/send', methods=['POST'])
def send_generador():
    form = GeneradorForm(request.form)
    if form.es_valido():
        data = {
            "modelo": form.modelo,
            "costo": form.costo,
            "consumoComustible": form.consumo_comustible,
            "enegeriaGenerada": form.energia_generada,
            "uso": form.uso
        }
        response = requests.post(f"{BACKEND_URL_GENERADOR}/createGenerador", json=data)
        if response.status_code == 200:
            return redirect(url_for('router.home', mensaje="Generador creado exitosamente"))
    return '<h1>¡Algo ha salido mal!</h1>'
    

@router.route('/generador/eliminar/<int:id>', methods=['POST'])
def delete_generador(id):
    response = requests.delete(f"{BACKEND_URL_GENERADOR}/delete/{id}")
    if response.status_code == 200:
        return redirect(url_for('router.home'))
    else:
        print(response.status_code)
        return '<h1>Error: no se pudo eliminar el generador</h1>'


@router.route('/generador/editar/<int:id>')
def edit_generador(id):
    r = requests.get(f"{BACKEND_URL_GENERADOR}/get/{id}")
    generador = r.json().get('data')  
    form = GeneradorForm(data=generador)  
    return render_template('fragmento/fromGenerador.html', form=form, generador=generador)


@router.route('/generador/update/<int:id>', methods=['POST'])
def update_generador(id):
    form = GeneradorForm(request.form)
    if form.es_valido():
        data = {
            "modelo": form.modelo,
            "costo": form.costo,
            "consumoComustible": form.consumo_comustible,
            "enegeriaGenerada": form.energia_generada,
            "uso": form.uso
        }
        response = requests.put(f"{BACKEND_URL_GENERADOR}/update/{id}", json=data)
        print(data)
        print(response.json())
        if response.status_code == 200:
            return redirect(url_for('router.home', mensaje="Generador actualizado exitosamente"))
    return render_template('fragmento/fromGenerador.html', form=form, error="¡Algo ha salido mal!")
##############################################################################################################
@router.route('/familia/<int:id>')
def familia(id):
    r_familias = requests.get(f"{BACKEND_URL_FAMILIA}/get/{id}")
    return render_template('familia.html', familia=r_familias.json().get('data'))  


@router.route('/familia/listaFamilias')
def list_familias():    
    r = requests.get(f"{BACKEND_URL_GENERADOR}/all")
    data = r.json()
    return render_template('fragmento/listaFamilias.html', familias=data['data'])  


@router.route('/familia/nuevo')
def save_familia():
    r = requests.get(f"{BACKEND_URL_GENERADOR}/all")
    generadores = r.json().get('data', [])  
    return render_template('fragmento/fromFamilia.html', generadores=generadores)


@router.route('/familia/send', methods=['POST'])
def send_familia():
    form = FamiliaForm(request.form)
    if form.es_valido():
        data = {
            "nroIntegrantes": form.nroIntegrantes,
            "nombreFamilia": form.nombreFamilia,
            "generador": {"idGenerador": int(form.generadorId)}
        }
        response = requests.post(f"{BACKEND_URL_FAMILIA}/createFamilia", json=data)
        if response.status_code == 200:
            return redirect(url_for('router.home', mensaje="Familia creada exitosamente"))
    return '<h1>¡Algo ha salido mal!</h1>'


@router.route('/familia/eliminar/<int:id>', methods=['POST'])
def delete_familia(id):
    response = requests.delete(f"{BACKEND_URL_FAMILIA}/delete/{id}")
    if response.status_code == 200:
        return redirect(url_for('router.home'))
    else:
        print(response.status_code)
        return '<h1>Error: no se pudo eliminar la familia</h1>'


@router.route('/familia/editar/<int:id>')
def edit_familia(id):
    r_familia = requests.get(f"{BACKEND_URL_FAMILIA}/get/{id}")
    
    if r_familia.status_code == 200:
        try:
            familia = r_familia.json().get('data')  
        except ValueError as e:
            print("Error al decodificar JSON:", e)
            return '<h1>Error: Respuesta del servidor no es JSON válido</h1>'
    else:
        print("Error al obtener familia:", r_familia.status_code)
        return '<h1>Error: No se pudo obtener la familia</h1>'
    

    r_generadores = requests.get(f"{BACKEND_URL_GENERADOR}/all")
    if r_generadores.status_code == 200:
        generadores = r_generadores.json().get('data', [])  
    else:
        print("Error al obtener generadores:", r_generadores.status_code)
        return '<h1>Error: No se pudo obtener la lista de generadores</h1>'
    
    form = FamiliaForm(data=familia)
    return render_template('fragmento/fromFamilia.html', form=form, familia=familia, generadores=generadores)


@router.route('/familia/update/<int:id>', methods=['POST'])
def update_familia(id):
    form = FamiliaForm(request.form)
    if form.es_valido():
        data = {
            "nroIntegrantes": form.nroIntegrantes,
            "nombreFamilia": form.nombreFamilia,
            "generador": {"idGenerador": int(form.generadorId)}
        }
        response = requests.put(f"{BACKEND_URL_FAMILIA}/update/{id}", json=data)
        if response.status_code == 200:
            return redirect(url_for('router.home', mensaje="Familia actualizada exitosamente"))
    return render_template('fragmento/fromFamilia.html', form=form, error="¡Algo ha salido mal!")
