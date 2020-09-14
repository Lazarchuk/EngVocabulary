package voc.service;

import voc.model.*;
import java.util.List;

public interface VocabularyService {
    List<Word> getAll();
    List<Synonym> getAllSynonyms();
    List<Antonym> getAllAntonyms();
    Word getWord(String requestWord);
    List<Category> getAllCategories();
    void addCategory(Category category);
    List<String> getErrors();
    boolean editWord(Word word, String requestWord);
    boolean insertWord(Word word);
    void deleteWord(int id);
    boolean addSynonym(String wordEng, String synonymName);
    void deleteSynonym(String wordEng, String synonymName);
    boolean addAntonym(String wordEng, String antonymName);
    void deleteAntonym(String wordEng, String antonymName);
}
