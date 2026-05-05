package com.sistemasdistr.basico.service;

//Importamos la anotación Service para que Spring gestione esta clase como componente de servicio
import org.springframework.stereotype.Service;
//RestTemplate permite realizar peticiones HTTP desde Spring Boot a otros servicios
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskApiService {

   // private final String BASE_URL = "http://localhost:5000/api/test/";
	//URL base de la API Flask
	//Se usa host.docker.internal porque Spring se ejecuta dentro de Docker
	//y necesita acceder al Flask que está ejecutándose en el sistema anfitrión
    private final String BASE_URL = "http://host.docker.internal:5000/api/test/";

    //Método generico para llamar a un endpoint concreto de Flask
    //Recibe el tipo de prueba: ok, file-error, db-error o pokemon-error
    public String callApi(String endpoint) {
    	//creamos un cliente HTTP para hacer la llamada desde Spring a Flask
        RestTemplate restTemplate = new RestTemplate();
        //realizamos la petición GET y devolvemos la respuesta como testo JSON
        return restTemplate.getForObject(BASE_URL + endpoint, String.class);
    }
}