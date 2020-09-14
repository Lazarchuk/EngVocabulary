package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import voc.service.VocabularyService;

@Controller
public class DeleteWordController {

    @Autowired
    private VocabularyService service;

    //AJAX
    @RequestMapping(value = "/delete/word", method = RequestMethod.POST, params = "wordId")
    @ResponseBody
    public int deleteWord(@RequestParam("wordId") int id){
        service.deleteWord(id);
        return id;
    }

}
