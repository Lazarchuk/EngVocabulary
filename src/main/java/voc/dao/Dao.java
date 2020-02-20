package voc.dao;

import voc.model.Word;

import java.util.List;

public interface Dao<T> {
    int getWordsQuantity();
    int findWordInDB(String requestWord);   // If such word is exist in DB, return it id, else - return 0
    List<T> getAll();
    List<String> getAllCategories();
    List<String> getErrors();
    Word getWord(String requestWord);
    int insertWord(Word word);
    int editWord(Word word);
    boolean addSynonym(String targetWord, String synonym);
    boolean addAntonym(String targetWord, String antonym);
    boolean deleteSynonym(String targetWord, String synonym);
    boolean deleteAntonym(String targetWord, String antonym);
    boolean addCategory(String targetWord, String category);
}
