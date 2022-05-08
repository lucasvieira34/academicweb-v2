package com.lucasvieira.academicweb.application.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller()
@RequestMapping("/secretaria")
public class SecretariaController {

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("secretaria/secretaria-dashboard");
    }
}
