from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/api/test/ok")
def ok():
    return jsonify({
        "success": True,
        "message": "Todo correcto"
    })

@app.route("/api/test/file-error")
def file_error():
    try:
        open("archivo_inexistente.txt")
    except Exception as e:
        return jsonify({
            "success": False,
            "errorType": "FILE_ERROR",
            "message": str(e)
        })

@app.route("/api/test/db-error")
def db_error():
    return jsonify({
        "success": False,
        "errorType": "DB_ERROR",
        "message": "Error simulado de base de datos"
    })

@app.route("/api/test/pokemon-error")
def pokemon_error():
    return jsonify({
        "success": False,
        "errorType": "THIRD_PARTY_API_ERROR",
        "message": "Error simulado API externa"
    })

if __name__ == "__main__":
    app.run(port=5000)