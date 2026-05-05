# practica2-sistemas-distribuidos

Repositorio: https://github.com/andreaP1032/practica2-sistemas-distribuidos

#Descripción
Aplicación distribuida compuesta por:

Spring Boot (backend)
Flask (API externa simulada)
Docker (contenedores)
MySQL / PostgreSQL / RabbitMQ

Spring Boot consume la API Flask para simular respuestas correctas y distintos errores.
-------------------------------------------
Ejecución
docker compose up --build
-Pantalla principal:
http://localhost:7001

-Simulador:
http://localhost:7001/simulador
-------------------------------------------
Acceso:
http://localhost:7001
-------------------------------------------
Seguridad
Se utiliza Spring Security con formulario de login
-------------------------------------------
Endpoints
/test/ok → respuesta correcta
/test/file-error → error de fichero
/test/db-error → error de base de datos
/test/pokemon-error → error API externa
Estos errores son generados por la API Flask y consumidos por Spring Boot.
-------------------------------------------
Simulador 
Se ha añadido una pantalla visual accesible desde:
http://localhost:7001/simulador
Permite todos los endpoints arriba mencionados
-------------------------------------------
Simulación
Se ha implementado una API en Flask que actúa como sistemo externo, generando respuestas controladas(éxito y errores) para validar el manejo de exceptiones en la aplicación Spring Boot.
-------------------------------------------
Capturas
Docker: contenedores en ejecución
Spring Boot:arranque correcto
Login: formulario de autenticación "admin"/1234
OK:respuesta correcta
File Error:error de fichero
DB Error:error de base de datos
API Error: error externo
Flask: ejecución del servidor
Simulador
-------------------------------------------
Autor
Andrea P 1032