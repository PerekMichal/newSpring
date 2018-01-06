package pl.perekmichal.newSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.perekmichal.newSpring.models.SimpleBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@Controller
public class MainController {

    @Autowired
    SimpleBean simpleBean;


    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String main(Model model) {

        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.of("Europe/Paris"));

        System.out.println("time now: " + now.toString());

        if (now.toLocalTime().getHour() >= 16){
            model.addAttribute("someString", "a");
        } else {
            model.addAttribute("someString","b");
        }
        return "index";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String data(@RequestParam(value = "name", required = false) String nameFromForm)  {
        return "twoje imię to: " + nameFromForm;
    }


}
