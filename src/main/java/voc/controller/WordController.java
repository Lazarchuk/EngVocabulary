package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import voc.dao.Dao;
import voc.dao.DataAbstractFactory;
import voc.model.SpeechPart;
import voc.model.Word;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WordController {
    @Autowired
    private DataAbstractFactory factory;
    private List<String> errors;

    // Load word info
    @RequestMapping(value = "/word/{requestWord}", method = RequestMethod.GET)
    public ModelAndView wordDetail(@PathVariable("requestWord") String requestWord, ModelMap model){
        Dao<Word> dao = factory.getDao();
        Word word = dao.getWord(requestWord);
        List<String> parts = SpeechPart.getSpeechParts();
        List<String> categories = dao.getAllCategories();
        model.addAttribute("editWord", word);
        model.addAttribute("partsMap", parts);
        model.addAttribute("categoryMap", categories);
        return new ModelAndView("worditem");
    }

    // Edit word
    @RequestMapping(value = "/word/{requestWord}", method = RequestMethod.POST)
    public ModelAndView editWord(@PathVariable("requestWord") String requestWord, ModelMap model, Word word){
        Dao<Word> dao = factory.getDao();
        int result = dao.editWord(word);
        if (result == 1){
            return new ModelAndView("redirect:/word/"+requestWord);
        }
        else {
            List<String> parts = SpeechPart.getSpeechParts();
            List<String> categories = dao.getAllCategories();
            List<String> errors = dao.getErrors();
            model.addAttribute("editWord", word);
            model.addAttribute("partsMap", parts);
            model.addAttribute("categoryMap", categories);
            model.addAttribute("errors", errors);
            return new ModelAndView("worditem");
        }
    }

    // Load form to insert new word
    @RequestMapping(value = "/word/insert", method = RequestMethod.GET)
    public ModelAndView newWord(ModelMap model){
        Dao<Word> dao = factory.getDao();
        Word insertWord = new Word();
        List<String> parts = SpeechPart.getSpeechParts();
        List<String> categories = dao.getAllCategories();
        model.addAttribute("insertWord", insertWord);
        model.addAttribute("partsMap", parts);
        model.addAttribute("categoryMap", categories);
        return new ModelAndView("insertword");
    }

    // Insert new word controller
    @RequestMapping(value = "/word/insert", method = RequestMethod.POST)
    public ModelAndView insertWord(ModelMap model, Word insertWord){
        Dao<Word> dao = factory.getDao();
        int result = dao.insertWord(insertWord);
        if (result == 1){
            return new ModelAndView("redirect:/word/"+insertWord.getWordEng().toLowerCase());
        }
        else {
            List<String> parts = SpeechPart.getSpeechParts();
            List<String> categories = dao.getAllCategories();
            List<String> errors = dao.getErrors();
            model.addAttribute("insertWord", insertWord);
            model.addAttribute("partsMap", parts);
            model.addAttribute("categoryMap", categories);
            model.addAttribute("errors", errors);
            return new ModelAndView("insertword");
        }
    }

    // Insert new synonym
    @RequestMapping(value = "/word/new/synonym", method = RequestMethod.POST, params = {"wordEng", "synonym"})
    public List<String> newSynonym(@RequestParam("wordEng") String wordEng, @RequestParam("synonym") String synonym){
        List<String> errors = new ArrayList<>();
        Dao<Word> dao = factory.getDao();
        boolean result = dao.addSynonym(wordEng, synonym);
        if (!result){
            errors = dao.getErrors();
        }
        return errors;
    }

    // Insert new antonym
    @RequestMapping(value = "/word/new/antonym", method = RequestMethod.POST, params = {"wordEng", "antonym"})
    public List<String> newAntonym(@RequestParam("wordEng") String wordEng, @RequestParam("antonym") String antonym){
        List<String> errors = new ArrayList<>();
        Dao<Word> dao = factory.getDao();
        boolean result = dao.addAntonym(wordEng, antonym);
        if (!result){
            errors = dao.getErrors();
        }
        return errors;
    }

    // Delete synonym
    @RequestMapping(value = "/word/delete/synonym", method = RequestMethod.POST, params = {"wordEng", "synonym"})
    public boolean delSynonym(@RequestParam("wordEng") String wordEng, @RequestParam("synonym") String synonym){
        Dao<Word> dao = factory.getDao();
        boolean result = dao.deleteSynonym(wordEng, synonym);
        return result;
    }

    // Delete synonym
    @RequestMapping(value = "/word/delete/antonym", method = RequestMethod.POST, params = {"wordEng", "antonym"})
    public boolean delAntonym(@RequestParam("wordEng") String wordEng, @RequestParam("antonym") String antonym){
        Dao<Word> dao = factory.getDao();
        boolean result = dao.deleteAntonym(wordEng, antonym);
        return result;
    }

    // Add new category. Edit word only
    @RequestMapping(value = "word/new/category", method = RequestMethod.POST, params = {"wordEng", "category"})
    public List<String> newCategory(@RequestParam("wordEng") String wordEng, @RequestParam("category") String category){
        List<String> errors = new ArrayList<>();
        Dao<Word> dao = factory.getDao();
        boolean result = dao.addCategory(wordEng, category);
        if (!result){
            errors = dao.getErrors();
        }
        return errors;
    }

}
