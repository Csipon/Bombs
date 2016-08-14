package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register/register";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String login(){ return "login/login"; }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String info(){
        return "user/info";
    }

    @RequestMapping(value = "/to.mail", method = RequestMethod.GET)
    public String toMail(){
        return "mail/sendmail";
    }

    @RequestMapping(value = "/do.buy", method = RequestMethod.GET)
    public String buy(){
        return "product/bought";
    }
}
