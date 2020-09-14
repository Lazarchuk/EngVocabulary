package voc.dao;

import voc.model.*;

import java.util.List;

public interface Dao {
    int getWordsQuantity();
    List<Word> getAll();
    List<Synonym> getAllSynonyms();
    List<Antonym> getAllAntonyms();
    List<Category> getAllCategories();
    List<String> getErrors();
    Word getWord(String requestWord);
    Synonym getSynonym(String requestSynonym);
    Antonym getAntonym(String requestAntonym);
    Category getCategory(int id);
    int getWordId(String requestWord);       // If such word is exist in DB, return it id, else - return 0
    int getSynonymId(String requestSynonym);       // If such word is exist in DB, return it id, else - return 0
    int getAntonymId(String requestAntonym);       // If such word is exist in DB, return it id, else - return 0
    void insertWord(Word word);
    void editWord(Word word);
    void deleteWord(int id);
    void mergeWord(Word word);
    boolean addSynonym(String targetWord, String synonym);
    boolean addAntonym(String targetWord, String antonym);
    boolean deleteSynonym(String targetWord, String synonym);
    boolean deleteAntonym(String targetWord, String antonym);
    void addCategory(Category category);
}
