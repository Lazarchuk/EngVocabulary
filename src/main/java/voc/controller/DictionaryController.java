package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import voc.model.Word;
import voc.service.VocabularyService;
import voc.serviceimpl.VocabularyServiceImpl;

import java.util.List;

@Controller
public class DictionaryController {

    @Autowired
    private VocabularyService service;

    @GetMapping("/dictionary")
    public ModelAndView getDictionary(ModelMap model){
        List<Word> words = service.getAll();
        model.addAttribute("words", words);
        return new ModelAndView("dictionary", model);
    }

}
