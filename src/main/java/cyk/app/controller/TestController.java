package cyk.app.controller;

import cyk.app.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping(
            value = "/testpojo",
            method = RequestMethod.POST
    )
    public String testparam(User user){
        System.out.println(user);
        return "test";
    }

    @RequestMapping("/param")
    public ModelAndView testParam(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testScope", "你好 model and view");
        modelAndView.setViewName("param");
        return modelAndView;
    }
}
