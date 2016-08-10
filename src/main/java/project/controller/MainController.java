package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HOUSE on 01.08.2016.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/index")
    public String index(){
        return "../../index";
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }
}
