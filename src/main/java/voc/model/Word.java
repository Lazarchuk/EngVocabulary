package voc.model;

import java.util.List;
import java.util.Map;

public class Word {
    private int id;
    private String wordEng;         // 40 charachters
    private String wordUkr;         // 60 charachters
    private String speechPart;      // 12 charachters
    private String category;        // 40 charachters
    private String example;         // 200 charachters
    private String meaning;         // 200 charachters
    private List<String> synonyms;  // JSON 200 charachters
    private List<String> antonyms;  // JSON 200 charachters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordEng() {
        return wordEng;
    }

    public void setWordEng(String wordEng) {
        this.wordEng = wordEng;
    }

    public String getWordUkr() {
        return wordUkr;
    }

    public void setWordUkr(String wordUkr) {
        this.wordUkr = wordUkr;
    }

    public String getSpeechPart() {
        return speechPart;
    }

    public void setSpeechPart(String speechPart) {
        this.speechPart = speechPart;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }
}
