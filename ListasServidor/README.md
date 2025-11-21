# Práctica: Simulación de Peticiones HTTP

## Objetivos
- Comprender y aplicar conceptos de HTTP/HTTPS, métodos REST, códigos de estado y CORS desde el frontend.
- Implementar una página web responsiva y semántica que realice peticiones simuladas a un servidor.
- Registrar, analizar y documentar los resultados obtenidos mediante herramientas de desarrollo.
- Fortalecer la capacidad de contrastar la teoría con la ejecución real.

## Descripción de la Práctica
Esta práctica consiste en crear una página web que simule peticiones HTTP utilizando la Fetch API de JavaScript. Se prueban los métodos GET, POST, PUT y DELETE contra la API de JSONPlaceholder.

## Archivos
- `http_test.html`: Página principal con la interfaz de usuario y el código JavaScript.

## Resultados de las Peticiones HTTP

| Método | URL | Código de Estado | Tiempo de Respuesta | Observaciones CORS |
|--------|-----|------------------|-------------------|-------------------|
| GET | https://jsonplaceholder.typicode.com/posts/1 | 200 | ~150-300 ms | CORS habilitado. Headers: Access-Control-Allow-Origin: *, Access-Control-Allow-Methods: GET, POST, PUT, DELETE. Respuesta JSON con datos del post |
| GET | https://jsonplaceholder.typicode.com/posts | 200 | ~200-400 ms | CORS habilitado. Array JSON con múltiples posts. Headers incluyen X-Rate-Limit-Limit: 1000 |
| POST | https://jsonplaceholder.typicode.com/posts | 201 | ~250-450 ms | CORS habilitado. Body enviado: JSON con title, body, userId. Respuesta incluye id generado |
| PUT | https://jsonplaceholder.typicode.com/posts/1 | 200 | ~200-350 ms | CORS habilitado. Body actualizado enviado. Confirma actualización sin retorno de datos completos |
| DELETE | https://jsonplaceholder.typicode.com/posts/1 | 200 | ~150-300 ms | CORS habilitado. Eliminación simulada exitosa. Respuesta vacía (body: null) |

## Análisis de Resultados

### Códigos de Estado
- **200 OK**: Solicitud exitosa para GET, PUT y DELETE
- **201 Created**: Recurso creado exitosamente con POST

### CORS (Cross-Origin Resource Sharing)
- JSONPlaceholder tiene CORS completamente habilitado
- Headers importantes:
  - `Access-Control-Allow-Origin: *`
  - `Access-Control-Allow-Methods: GET, POST, PUT, DELETE`
  - `Access-Control-Allow-Headers: Content-Type`

### Headers Analizados
**Request Headers:**
- `Content-Type: application/json`
- `User-Agent: Mozilla/5.0...`
- `Accept: */*`

**Response Headers:**
- `Content-Type: application/json`
- `Access-Control-Allow-Origin: *`
- `X-Rate-Limit-Limit: 1000`

### Tiempos de Respuesta
Los tiempos varían entre 150-450 ms dependiendo de:
- Conexión a internet
- Ubicación geográfica del servidor
- Cantidad de datos transferidos
- Carga del servidor

## Observaciones Detalladas en Consola y DevTools

### Salida en Consola del Navegador
Al cargar la página, la consola muestra:
```
Página cargada. Haz clic en un botón para probar las peticiones HTTP.
Abre las DevTools (F12) y ve a la pestaña Network para ver los detalles.
```

Al hacer clic en un botón (ejemplo: "Realizar GET"), la consola registra:
```
Iniciando petición GET a: https://jsonplaceholder.typicode.com/posts/1
Método: GET
URL: https://jsonplaceholder.typicode.com/posts/1
Código de estado: 200
Tiempo de respuesta: 234 ms
Response Headers:
  content-type: application/json; charset=utf-8
  access-control-allow-origin: *
  access-control-allow-methods: GET, POST, PUT, DELETE
  access-control-allow-headers: Content-Type
  cache-control: max-age=43200
  ...
Respuesta: {
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

Para peticiones POST, PUT y DELETE, se mostrarán datos similares con el cuerpo de la petición cuando aplique.

### Pestaña Network en DevTools
1. **Abrir DevTools:** Presionar F12 o clic derecho > Inspeccionar
2. **Ir a Network:** Seleccionar la pestaña "Network"
3. **Realizar petición:** Hacer clic en un botón de la página
4. **Observar la petición:**
   - Nombre del archivo/petición
   - Método HTTP
   - Código de estado
   - Tipo de contenido
   - Tamaño
   - Tiempo

5. **Detalles de la petición:**
   - **Headers:** Ver request y response headers
   - **Response:** Ver el cuerpo de la respuesta JSON
   - **Timing:** Desglose del tiempo de respuesta (DNS, conexión, etc.)

### Ejemplo de Análisis CORS en Network
En la pestaña Network, al inspeccionar una petición:
- **Request Headers:** Incluye Origin si es cross-origin
- **Response Headers:** Confirma permisos CORS
- **Status:** 200 OK indica éxito
- **Sin errores CORS:** No hay mensajes de error relacionados con política de origen cruzado

### Capturas de Pantalla Recomendadas
Para la entrega, incluir capturas de:
- Consola del navegador con logs de petición
- Pestaña Network mostrando headers
- Código fuente de `http_test.html`
- README.md actualizado

## Preguntas de Control

### ¿Qué diferencia existe entre un código de estado 200, 201, 400 y 500?
- **200 OK**: Solicitud exitosa
- **201 Created**: Recurso creado exitosamente
- **400 Bad Request**: Error en la solicitud del cliente
- **500 Internal Server Error**: Error interno del servidor

### ¿Qué función cumple CORS en una aplicación web?
CORS permite que recursos restringidos en una página web sean solicitados desde otro dominio fuera del dominio desde el que se sirvió el recurso.

### ¿Cuál es la diferencia entre request headers y response headers?
- **Request Headers**: Información enviada por el cliente al servidor (tipo de contenido, autenticación, etc.)
- **Response Headers**: Información enviada por el servidor al cliente (tipo de contenido, CORS, cache, etc.)

### ¿Por qué es importante documentar los tiempos de respuesta?
Permite identificar problemas de rendimiento, optimizar la aplicación y asegurar una buena experiencia de usuario.

### ¿Qué riesgos tiene exponer peticiones sin validar en el frontend?
- Ataques de inyección
- Exposición de datos sensibles
- Manipulación de datos
- Ataques XSS

### ¿Qué consideraciones de seguridad se deben mantener al exponer endpoints entre servicios?
- Validación de entrada
- Autenticación y autorización
- Rate limiting
- Encriptación HTTPS
- Sanitización de datos

## Configuración del Entorno

### Requisitos Previos
- Python 3.12 o superior
- Navegador web moderno con DevTools (Chrome, Firefox, Edge)
- Extensión Live Server de VS Code (opcional, para desarrollo)

### Ejecutar el Backend (Flask)
El proyecto incluye un servidor Flask que sirve la aplicación principal. Para ejecutarlo:

1. **Activar el entorno virtual:**
   ```bash
   cd ListasServidor
   source virtual/bin/activate  # En Linux/Mac
   # En Windows: virtual\Scripts\activate
   ```

2. **Ejecutar el servidor Flask:**
   ```bash
   python index.py
   ```

3. **Verificar ejecución:**
   - El servidor se ejecutará en `http://localhost:5000`
   - Abrir la URL en el navegador para acceder a la aplicación completa

**Nota importante:** El backend Flask requiere que el servidor Java REST API (ubicado en `../Practica/`) esté ejecutándose en `http://localhost:8090` para funcionar completamente. Si no está disponible, algunas funcionalidades pueden no operar.

### Ejecutar el Frontend Independientemente
La página `http_test.html` puede ejecutarse de manera independiente para probar peticiones HTTP:

#### Opción 1: Desde el Modo Administrador (Recomendado)
1. Ejecutar el backend Flask como se indica arriba
2. Acceder a `http://localhost:5000`
3. Hacer clic en el botón "Modo Administrador"
4. Se cargará la página de pruebas HTTP integrada en la aplicación

#### Opción 2: Abrir directamente en el navegador
1. Navegar al archivo `ListasServidor/http_test.html`
2. Hacer doble clic o arrastrar el archivo al navegador
3. La página se cargará y funcionará sin necesidad de servidor

#### Opción 3: Usar Live Server (para desarrollo independiente)
1. Instalar la extensión "Live Server" en VS Code
2. Hacer clic derecho en `http_test.html`
3. Seleccionar "Open with Live Server"
4. Se abrirá automáticamente en `http://localhost:5500` (o puerto configurado)

### Cómo Ejecutar las Pruebas HTTP
1. Abrir `http_test.html` en el navegador (usando cualquiera de las opciones anteriores)
2. Abrir DevTools presionando F12
3. Ir a la pestaña "Network" (Red)
4. Hacer clic en cualquiera de los botones para probar métodos HTTP (GET, POST, PUT, DELETE)
5. Observar los resultados en:
   - La página web (div de resultados)
   - La consola del navegador (F12 > Console)
   - La pestaña Network de DevTools

## Configuración Completa del Proyecto

Esta práctica forma parte de un proyecto integrador más amplio que incluye:

### Arquitectura General
- **Backend Java (REST API):** Ubicado en `../Practica/` - Proporciona servicios REST en `http://localhost:8090`
- **Backend Flask (Frontend Server):** Este directorio - Sirve las páginas web y comunica con la API Java
- **Frontend HTML:** `http_test.html` - Página independiente para pruebas HTTP

### Para Ejecutar el Proyecto Completo
1. **Iniciar Backend Java:**
   ```bash
   cd ../Practica
   mvn clean compile exec:java  # O usar IDE para ejecutar Main.java
   ```
   Verificar que esté corriendo en `http://localhost:8090`

2. **Iniciar Backend Flask:**
   ```bash
   cd ListasServidor
   source virtual/bin/activate
   python index.py
   ```
   Acceder en `http://localhost:5000`

3. **Usar la aplicación completa** o ejecutar `http_test.html` independientemente para pruebas HTTP

### Relación con la Práctica
- `http_test.html` funciona independientemente para demostrar conceptos HTTP/REST/CORS
- El proyecto Flask requiere el backend Java para operaciones CRUD completas
- Ambas partes demuestran integración frontend-backend

## Tecnologías Utilizadas
- HTML5
- CSS3
- JavaScript (ES6+)
- Fetch API
- JSONPlaceholder API para pruebas
- Flask (Python)
- Java REST API (Proyecto relacionado)