package voc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import voc.dao.DataAbstractFactory;
import voc.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private DataAbstractFactory factory;

    @RequestMapping(value = "result", method = RequestMethod.POST, params = "search")
    @ResponseBody
    public List<Word> getSearchWords(@RequestParam("search") String search){
        List<Word> allWords = factory.getDao().getAll();
        List<Word> searchWords = new ArrayList<>();
        String regex = ".*" + search + ".*";
        for (Word word : allWords) {
            if (Pattern.matches(regex, word.getWordEng())) {
                if (word.getWordUkr() == null){
                    word.setWordUkr("");
                }
                searchWords.add(word);
            }
        }
        return searchWords;
    }
}
