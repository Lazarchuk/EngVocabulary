package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import voc.model.Category;
import voc.model.SpeechPart;
import voc.model.Synonym;
import voc.model.Word;
import voc.service.VocabularyService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class WordController {

    @Autowired
    private VocabularyService service;

    // Load word info
    @RequestMapping(value = "/word/{requestWord}", method = RequestMethod.GET)
    public ModelAndView wordDetail(@PathVariable("requestWord") String requestWord, ModelMap model){
        Word word = service.getWord(requestWord);
        List<String> parts = SpeechPart.getSpeechParts();
        List<Category> categories = service.getAllCategories();
        model.addAttribute("editWord", word);
        model.addAttribute("partsMap", parts);
        model.addAttribute("categoryMap", categories);
        return new ModelAndView("worditem");
    }

    //Edit word
    @RequestMapping(value = "/word/{requestWord}", method = RequestMethod.POST)
    public ModelAndView editWord(@PathVariable("requestWord") String requestWord, ModelMap model, @ModelAttribute("editWord") Word word){
        boolean result = service.editWord(word, requestWord);
        if (result){
            return new ModelAndView("redirect:/word/"+word.getWordEng().toLowerCase());
        }
        else {
            List<String> parts = SpeechPart.getSpeechParts();
            List<Category> categories = service.getAllCategories();
            List<String> errors = service.getErrors();
            model.addAttribute("editWord", word);
            model.addAttribute("partsMap", parts);
            model.addAttribute("categoryMap", categories);
            model.addAttribute("errors", errors);
            return new ModelAndView("worditem");
        }
    }

    //Add new category. AJAX
    @RequestMapping(value = "word/new/category", method = RequestMethod.POST, params = "category")
    public List<String> newCategory(@RequestParam("category") String category){
        List<String> errors = new ArrayList<>();
        Category categoryEntity = new Category();
        categoryEntity.setName(category);
        service.addCategory(categoryEntity);
        errors = service.getErrors();
        return errors;
    }

    // Load form to insert new word
    @RequestMapping(value = "/word/insert", method = RequestMethod.GET)
    public ModelAndView newWord(ModelMap model){
        Word insertWord = new Word();
        List<String> parts = SpeechPart.getSpeechParts();
        List<Category> categories = service.getAllCategories();
        model.addAttribute("insertWord", insertWord);
        model.addAttribute("partsMap", parts);
        model.addAttribute("categoryMap", categories);
        return new ModelAndView("insertword");
    }

    // Insert new word
    @RequestMapping(value = "/word/insert", method = RequestMethod.POST)
    public ModelAndView insertWord(ModelMap model, Word insertWord){
        boolean result = service.insertWord(insertWord);
        if (result){
            return new ModelAndView("redirect:/word/"+insertWord.getWordEng().toLowerCase());
        }
        else {
            List<String> parts = SpeechPart.getSpeechParts();
            List<Category> categories = service.getAllCategories();
            List<String> errors = service.getErrors();
            model.addAttribute("insertWord", insertWord);
            model.addAttribute("partsMap", parts);
            model.addAttribute("categoryMap", categories);
            model.addAttribute("errors", errors);
            return new ModelAndView("insertword");
        }
    }

    //Add synonym. AJAX
    @RequestMapping(value = "/word/new/synonym", method = RequestMethod.POST, params = {"wordEng", "synonym"})
    public List<String> newSynonym(@RequestParam("wordEng") String wordEng, @RequestParam("synonym") String synonymName){
        List<String> errors = new ArrayList<>();
        boolean result = service.addSynonym(wordEng, synonymName);
        if (!result){
            errors = service.getErrors();
        }
        return errors;
    }

    // AJAX
    // Delete synonym
    @RequestMapping(value = "/word/delete/synonym", method = RequestMethod.POST, params = {"wordEng", "synonym"})
    public void delSynonym(@RequestParam("wordEng") String wordEng, @RequestParam("synonym") String synonymName){
        service.deleteSynonym(wordEng, synonymName);
    }

    // AJAX
    //Add antonym
    @RequestMapping(value = "/word/new/antonym", method = RequestMethod.POST, params = {"wordEng", "antonym"})
    public List<String> newAntonym(@RequestParam("wordEng") String wordEng, @RequestParam("antonym") String antonymName){
        List<String> errors = new ArrayList<>();
        boolean result = service.addAntonym(wordEng, antonymName);
        if (!result){
            errors = service.getErrors();
        }
        return errors;
    }

    // AJAX
    // Delete antonym
    @RequestMapping(value = "/word/delete/antonym", method = RequestMethod.POST, params = {"wordEng", "antonym"})
    public void delAntonym(@RequestParam("wordEng") String wordEng, @RequestParam("antonym") String antonymName){
        service.deleteAntonym(wordEng, antonymName);
    }


    /*@Autowired
    private DataAbstractFactory factory;
    private List<String> errors;

    // Insert new antonym
    @RequestMapping(value = "/word/new/antonym", method = RequestMethod.POST, params = {"wordEng", "antonym"})
    public List<String> newAntonym(@RequestParam("wordEng") String wordEng, @RequestParam("antonym") String antonym){
        List<String> errors = new ArrayList<>();
        Dao<Word> daoimpl = factory.getDao();
        boolean result = daoimpl.addAntonym(wordEng, antonym);
        if (!result){
            errors = daoimpl.getErrors();
        }
        return errors;
    }

    // Add new category. Edit word only
    @RequestMapping(value = "word/new/category", method = RequestMethod.POST, params = {"wordEng", "category"})
    public List<String> newCategory(@RequestParam("wordEng") String wordEng, @RequestParam("category") String category){
        List<String> errors = new ArrayList<>();
        Dao<Word> daoimpl = factory.getDao();
        boolean result = daoimpl.addCategory(wordEng, category);
        if (!result){
            errors = daoimpl.getErrors();
        }
        return errors;
    }*/

}
