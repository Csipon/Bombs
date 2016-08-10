package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by HOUSE on 26.07.2016.
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String errorNotFound() {
        return "404";
    }

    @RequestMapping(value = "/400", method = RequestMethod.GET)
    public String errorRegister() {
        return "400";
    }
}
