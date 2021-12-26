package cyk.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForgetController {

    @RequestMapping("/forget")
    public String forget(){
        return "forget";
    }
}
