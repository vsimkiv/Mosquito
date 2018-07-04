package com.softserve.mosquito.configs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class IndexController {
   @GetMapping
    public ModelAndView swagger(){
        return new ModelAndView("redirect:/docApi/swagger-ui.html");
    }
}