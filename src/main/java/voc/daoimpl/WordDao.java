package voc.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import voc.dao.Dao;
import voc.model.Antonym;
import voc.model.Category;
import voc.model.Synonym;
import voc.model.Word;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordDao implements Dao {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private Connection connection;
    private List<String> errors;

    @Override
    public int getWordsQuantity() {
        return 0;
    }

    @Override
    public int getWordId(String requestWord){
        String query = "SELECT id FROM dictionary.vocabulary WHERE word_eng=?";
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, requestWord.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int getSynonymId(String requestSynonym){
        String query = "SELECT id FROM dictionary.synonyms WHERE name=?";
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, requestSynonym.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int getAntonymId(String requestAntonym) {
        String query = "SELECT id FROM dictionary.antonyms WHERE name=?";
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, requestAntonym.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Category getCategory(int id){
        return em.find(Category.class, id);
    }

    @Override
    public List<Word> getAll() {
        List<Word> words = em.createNamedQuery("Word.getAll", Word.class).getResultList();
        return words;
    }

    @Override
    public List<Synonym> getAllSynonyms() {
        List<Synonym> synonyms = em.createNamedQuery("Synonym.getAll", Synonym.class).getResultList();
        return synonyms;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = em.createNamedQuery("Category.getAll", Category.class).getResultList();
        return categories;
    }

    @Override
    public Word getWord(String requestWord) {
        int id = getWordId(requestWord);
        return em.find(Word.class, id);
    }

    @Override
    public Synonym getSynonym(String requestSynonym) {
        int id = getSynonymId(requestSynonym);
        return em.find(Synonym.class, id);
    }

    @Override
    public Antonym getAntonym(String requestAntonym) {
        int id = getAntonymId(requestAntonym);
        return em.find(Antonym.class, id);
    }

    @Override
    public List<Antonym> getAllAntonyms() {
        List<Antonym> antonyms = em.createNamedQuery("Antonym.getAll", Antonym.class).getResultList();
        return antonyms;
    }

    @Override
    @Transactional
    public void insertWord(Word word) {
        Category category = null;
        if (!word.getCategoryId().equals("")) {
            int categoryId = Integer.parseInt(word.getCategoryId());
            category = getCategory(categoryId);
        }
        word.setCategory(category);
        em.persist(word);
    }

    @Override
    @Transactional
    public void editWord(Word word) {
        Category category = null;
        if (!word.getCategoryId().equals("")) {
            int categoryId = Integer.parseInt(word.getCategoryId());
            category = getCategory(categoryId);
        }
        word.setId(word.getId());
        word.setCategory(category);
        em.merge(word);
    }

    @Override
    @Transactional
    public void deleteWord(int id){
        em.remove(em.find(Word.class, id));
    }

    @Override
    @Transactional
    public void mergeWord(Word word) {
        em.merge(word);
    }

    @Override
    public boolean addSynonym(String targetWord, String synonym) {
        return false;
    }

    @Override
    public boolean addAntonym(String targetWord, String antonym) {
        return false;
    }

    @Override
    public boolean deleteSynonym(String wordEng, String synonym){
        /*boolean result = false;
        String jsonList = null;
        String query = "UPDATE vocabulary SET synonyms=? WHERE word_eng=?";
        Word targetWord = getWord(wordEng);
        List<String> list = targetWord.getSynonyms();
        list.remove(synonym);
        jsonList = writeJsonList(list);
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, jsonList);
            ps.setString(2, targetWord.getWordEng());
            int resultUpdate = ps.executeUpdate();
            if (resultUpdate == 1){
                result = true;
            }
            else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;*/
        return false;
    }

    @Override
    public boolean deleteAntonym(String targetWord, String antonym) {
        return false;
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        em.persist(category);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
