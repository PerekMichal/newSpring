package pl.perekmichal.newSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.perekmichal.newSpring.PersonRepository;
import pl.perekmichal.newSpring.models.Person;
import pl.perekmichal.newSpring.models.forms.PersonForm;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class MainController {

    @Autowired
    PersonRepository personRepository;


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
    public String data(@RequestParam(value = "name") String name,
                       @RequestParam(value = "lastName") String lastName) {

        return name + " " + lastName;
    }

    @RequestMapping(value = "/newform", method = RequestMethod.GET)
    public String newform(Model model){
        model.addAttribute("personObject", new PersonForm());
        return "form";
    }

    @RequestMapping(value = "/newform", method = RequestMethod.POST)
    public String newformPost(@ModelAttribute("personObject") @Valid PersonForm personForm, BindingResult result){
        if (result.hasErrors()){
            return "form";
        }
        Person personObject = new Person(personForm);
        personRepository.save(personObject);

        return "result";
    }


    private  void  testBuilder () {
        PersonForm personForm = new PersonForm.Builder("michał" )
                .age(27)
                .email("test@test.com")
                .lastName("perek")
                .number("8888")
                .build();

        personForm.getAge();
    }
}
