/**
 * Controlador principal de la aplicación
 * Este controlador expone endpoints REST que permiten interactuar con la aplicación
 * Se encarga de invocar el servicio FlaskApiService para consumir la API externa simulada (Flask)
 * y devolver las respuestas al cliente
 * Forma parte de la capa de presentación dentro de la arquitectura de la aplicación
 */

package com.sistemasdistr.basico.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistemasdistr.basico.service.FlaskApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Maincontroller {
	// Endpoint raíz que devuelve la vista principal (index.html)
    @GetMapping("/")
    public String vistaHome( ModelMap interfazConPantalla){
        return "index";
    }
    @Autowired
    private FlaskApiService flaskApiService;
    
    // Endpoint que recibe el tipo de prueba y llama a la API Flask mediante el servicio
    @GetMapping("/test/{type}")
    @ResponseBody
    public String testApi(@PathVariable String type) {
        return flaskApiService.callApi(type);
    }
}
