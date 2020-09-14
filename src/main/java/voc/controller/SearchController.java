package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import voc.model.*;
import voc.service.VocabularyService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private VocabularyService service;

    //AJAX
    // Category Fetchtype LAZY
    @RequestMapping(value = "result", method = RequestMethod.POST, params = "search")
    @ResponseBody
    public List<Word> getSearchWords(@RequestParam("search") String search){
        List<Word> allWords = service.getAll();
        List<Word> searchWords = new ArrayList<>();
        String regex = ".*" + search + ".*";
        for (Word word : allWords) {
            if (Pattern.matches(regex, word.getWordEng())) {
                searchWords.add(word);
            }
        }
        return searchWords;
    }

    //AJAX
    //Search synonyms
    @RequestMapping(value = "synonyms", method = RequestMethod.POST, params = "search")
    @ResponseBody
    public List<Synonym> getSearchSynonyms(@RequestParam("search") String search){
        List<Synonym> allSynonyms = service.getAllSynonyms();
        List<Synonym> searchSynonym = new ArrayList<>();
        String regex = ".*" + search + ".*";
        for (Synonym synonym : allSynonyms) {
            if (Pattern.matches(regex, synonym.getName())) {
                searchSynonym.add(synonym);
            }
        }
        return searchSynonym;
    }

    //AJAX
    //Search antonyms
    @RequestMapping(value = "antonyms", method = RequestMethod.POST, params = "search")
    @ResponseBody
    public List<Antonym> getSearchAntonyms(@RequestParam("search") String search){
        List<Antonym> allAntonyms = service.getAllAntonyms();
        List<Antonym> searchAntonym = new ArrayList<>();
        String regex = ".*" + search + ".*";
        for (Antonym antonym : allAntonyms) {
            if (Pattern.matches(regex, antonym.getName())) {
                searchAntonym.add(antonym);
            }
        }
        return searchAntonym;
    }
}
