package voc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voc.dao.Dao;
import voc.model.Antonym;
import voc.model.Category;
import voc.model.Synonym;
import voc.model.Word;
import voc.service.VocabularyService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VocabularyServiceImpl implements VocabularyService{
    @Autowired
    private Dao wordDao;
    private List<String> errors;

    public VocabularyServiceImpl() {
    }

    @Override
    public List<Word> getAll(){
        return wordDao.getAll();
    }

    @Override
    public List<Synonym> getAllSynonyms() {
        return wordDao.getAllSynonyms();
    }

    @Override
    public List<Antonym> getAllAntonyms() {
        return wordDao.getAllAntonyms();
    }

    @Override
    public Word getWord(String requestWord) {
        return wordDao.getWord(requestWord);
    }

    @Override
    public List<Category> getAllCategories(){
        return wordDao.getAllCategories();
    }

    @Override
    public void addCategory(Category category){
        if (category.getName().length() > 40){
            errors = new ArrayList<>();
            errors.add("Category should not be longer than 40 characters");
        }
        else {
            wordDao.addCategory(category);
        }
    }

    @Override
    public List<String> getErrors(){
        return errors;
    }

    @Override
    public boolean editWord(Word word, String requestWord) {
        boolean result = isValidEditWord(word, requestWord);
        if (result){
            wordDao.editWord(word);
        }
        return result;
    }

    @Override
    public boolean insertWord(Word word) {
        boolean result = isValidNewWord(word);
        if (result) {
            wordDao.insertWord(word);
        }
        return result;
    }

    @Override
    public void deleteWord(int id){
        wordDao.deleteWord(id);
    }

    @Override
    public boolean addSynonym(String wordEng, String synonymName){
        boolean result = isValidNewSynonym(synonymName);
        Word word = wordDao.getWord(wordEng);
        Synonym synonym = wordDao.getSynonym(synonymName);
        if (synonym != null){
            word.addSynonym(synonym);
            wordDao.mergeWord(word);
        }
        else if (result){
            Synonym newSynonym = new Synonym();
            newSynonym.setName(synonymName);
            word.addSynonym(newSynonym);
            wordDao.mergeWord(word);
        }
        return result;
    }

    @Override
    public void deleteSynonym(String wordEng, String synonymName) {
        Word word = wordDao.getWord(wordEng);
        Synonym synonym = wordDao.getSynonym(synonymName);
        word.getSynonyms().remove(synonym);
        wordDao.mergeWord(word);
    }

    @Override
    public boolean addAntonym(String wordEng, String antonymName) {
        boolean result = isValidNewAntonym(antonymName);
        Word word = wordDao.getWord(wordEng);
        Antonym antonym = wordDao.getAntonym(antonymName);
        if (antonym != null){
            word.addAntonym(antonym);
            wordDao.mergeWord(word);
        }
        else if (result){
            Antonym newAntonym = new Antonym();
            newAntonym.setName(antonymName);
            word.addAntonym(newAntonym);
            wordDao.mergeWord(word);
        }
        return result;
    }

    @Override
    public void deleteAntonym(String wordEng, String antonymName) {
        Word word = wordDao.getWord(wordEng);
        Antonym antonym = wordDao.getAntonym(antonymName);
        word.getAntonyms().remove(antonym);
        wordDao.mergeWord(word);
    }

    private boolean isValidNewWord(Word word){
        boolean valid = true;
        errors = new ArrayList<>();
        if (wordDao.getWordId(word.getWordEng()) != 0){
            errors.add("This word has already exist");
            valid = false;
        }
        if (word.getWordEng().length() > 60){
            errors.add("The word should not be longer than 60 characters");
            valid = false;
        }
        if (word.getWordEng().length() == 0){
            errors.add("Enter new word");
            valid = false;
        }
        if (word.getWordUkr().length() > 60){
            errors.add("The translation should not be longer than 60 characters");
            valid = false;
        }
        if (word.getMeaning().length() > 200){
            errors.add("The meaning should not be longer than 200 characters");
            valid = false;
        }
        if (word.getExample().length() > 200){
            errors.add("The example should not be longer than 200 characters");
            valid = false;
        }
        return valid;
    }

    private boolean isValidEditWord(Word word,String requestWord){
        boolean valid = true;
        errors = new ArrayList<>();
        if (!word.getWordEng().toLowerCase().equals(requestWord) && wordDao.getWordId(word.getWordEng()) != 0){
            errors.add("This word has already exist");
            valid = false;
        }

        if (word.getWordEng().length() > 60){
            errors.add("The word should not be longer than 60 characters");
            valid = false;
        }
        if (word.getWordEng().length() == 0){
            errors.add("Enter new word");
            valid = false;
        }
        if (word.getWordUkr().length() > 60){
            errors.add("The translation should not be longer than 60 characters");
            valid = false;
        }
        if (word.getMeaning().length() > 200){
            errors.add("The meaning should not be longer than 200 characters");
            valid = false;
        }
        if (word.getExample().length() > 200){
            errors.add("The example should not be longer than 200 characters");
            valid = false;
        }
        return valid;
    }

    private boolean isValidNewSynonym(String synonymName){
        boolean valid = true;
        errors = new ArrayList<>();
        if (synonymName.length() > 60){
            errors.add("The synonym should not be longer than 60 characters");
            valid = false;
        }
        return valid;
    }

    private boolean isValidNewAntonym(String antonymName){
        boolean valid = true;
        errors = new ArrayList<>();
        if (antonymName.length() > 60){
            errors.add("The antonym should not be longer than 60 characters");
            valid = false;
        }
        return valid;
    }
}
