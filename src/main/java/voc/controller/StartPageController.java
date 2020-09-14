package voc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartPageController {

    @GetMapping("/eng")
    public ModelAndView initPage(){
        return new ModelAndView("start");
    }
}

