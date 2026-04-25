# practica2-sistemas-distribuidos

Repositorio: https://github.com/andreaP1032/practica2-sistemas-distribuidos

#Descripción
Aplicación distribuida compuesta por:

Spring Boot (backend)
Flask (API externa simulada)
Docker (contenedores)
MySQL / PostgreSQL / RabbitMQ

Spring Boot consume la API Flask para simular respuestas correctas y distintos errores.

Ejecución
docker compose up --build
Acceso:
http://localhost:7001

Seguridad
Se utiliza Spring Security con formulario de login

Endpoints
/test/ok → respuesta correcta
/test/file-error → error de fichero
/test/db-error → error de base de datos
/test/pokemon-error → error API externa

Capturas
Docker:
Spring Boot:
Login:
OK:
File Error:
DB Error:
API Error:
Flask:

Autor
Andrea P 1032