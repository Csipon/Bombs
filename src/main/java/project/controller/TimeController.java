package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.GregorianCalendar;

/**
 * Created by HOUSE on 23.07.2016.
 */
@Controller
public class TimeController {
    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public String getTime(@RequestParam("time") String calendar, Model model){
        GregorianCalendar gc = new GregorianCalendar();
        float delta = ((float)(gc.getTimeInMillis() - (Long.parseLong(calendar)))/ 1000);
        model.addAttribute("result", delta);
        return "time/time";
    }
}
