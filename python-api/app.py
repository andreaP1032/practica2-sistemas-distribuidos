# --------------------------------------------------------
# API Flask simulada para integración con Spring Boot
# --------------------------------------------------------
# Este archivo implementa un microservicio en Python utilizando Flask.
# Su objetivo es simular una API externa (tercer sistema) que será
# consumida por la aplicación Spring Boot.
#
# Se han definido distintos endpoints que devuelven respuestas
# controladas (éxito y diferentes tipos de error) para poder
# probar el manejo de excepciones en un entorno distribuido.
#
# De esta forma, la aplicación principal puede validar cómo se
# comporta ante fallos externos sin depender de servicios reales.
# --------------------------------------------------------

# Importamos Flask para crear una API REST ligera
# y jsonify para devolver respuestas en formato JSON
from flask import Flask, jsonify

# Inicializamos la aplicación Flask
# Este servicio se ejecutará de forma independiente a Spring Boot
app = Flask(__name__)

# --------------------------------------------------------
# Endpoint de prueba correcto
# --------------------------------------------------------
# Simula una respuesta válida de un servicio externo.
# Se utiliza para comprobar la comunicación correcta entre
# Spring Boot y la API Flask.
@app.route("/api/test/ok")
def ok():
    return jsonify({
        "success": True,
        "message": "Todo correcto"
    })

# --------------------------------------------------------
# Simulación de error de acceso a fichero
# --------------------------------------------------------
# Representa un fallo típico en sistemas externos, donde
# un recurso no está disponible.
# Permite comprobar cómo Spring maneja este tipo de error.
@app.route("/api/test/file-error")
def file_error():
# Intentamos abrir un archivo inexistente para provocar
# una excepción real y simular un error de fichero
    try:
        open("archivo_inexistente.txt")
    except Exception as e:
        return jsonify({
            "success": False,
            "errorType": "FILE_ERROR",
            "message": str(e)
        })

# --------------------------------------------------------
# Simulación de error de base de datos
# --------------------------------------------------------
# Se utiliza para simular fallos internos en servicios externos,
# como errores en consultas o conexiones a base de datos.
@app.route("/api/test/db-error")
def db_error():
    return jsonify({
        "success": False,
        "errorType": "DB_ERROR",
        "message": "Error simulado de base de datos"
    })

# --------------------------------------------------------
# Simulación de error en API de terceros
# --------------------------------------------------------
# Representa un fallo en servicios externos (por ejemplo,
# una API pública como Pokémon API).
# Muy útil para probar resiliencia en sistemas distribuidos.
@app.route("/api/test/pokemon-error")
def pokemon_error():
    return jsonify({
        "success": False,
        "errorType": "THIRD_PARTY_API_ERROR",
        "message": "Error simulado API externa"
    })

# --------------------------------------------------------
# Arranque del servidor Flask
# --------------------------------------------------------
# Se ejecuta en local (puerto 5000) y es consumido
# por la aplicación Spring Boot mediante llamadas HTTP.
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)