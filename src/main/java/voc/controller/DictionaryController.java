package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import voc.dao.Dao;
import voc.dao.DataAbstractFactory;
import voc.model.Word;

import java.util.List;

@Controller
public class DictionaryController {
    @Autowired
    private DataAbstractFactory factory;

    @GetMapping("/dictionary")
    public ModelAndView getDictionary(ModelMap model){
        Dao<Word> dao = factory.getDao();
        List<Word> words = dao.getAll();
        model.addAttribute("words", words);
        return new ModelAndView("dictionary", model);
    }

}
