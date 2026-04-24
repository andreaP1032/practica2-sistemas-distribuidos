package com.sistemasdistr.basico.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistemasdistr.basico.service.FlaskApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Maincontroller {
    @GetMapping("/")
    public String vistaHome( ModelMap interfazConPantalla){
        return "index";
    }
    @Autowired
    private FlaskApiService flaskApiService;
    @GetMapping("/test/{type}")
    @ResponseBody
    public String testApi(@PathVariable String type) {
        return flaskApiService.callApi(type);
    }
}
