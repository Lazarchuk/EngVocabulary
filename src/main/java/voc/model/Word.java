package voc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vocabulary")
@NamedQuery(name = "Word.getAll", query = "SELECT c from Word c")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word_eng", length = 60)
    private String wordEng;

    @Column(name = "word_ukr", length = 60)
    private String wordUkr;

    @Column(name = "speech_part", length = 12)
    private String speechPart;

    @Column(name = "example", length = 200)
    private String example;

    @Column(name = "meaning", length = 200)
    private String meaning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "word_synonyms",
                joinColumns = @JoinColumn(name = "word_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "synonym_id", referencedColumnName = "id"))
    private Set<Synonym> synonyms;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "word_antonyms",
            joinColumns = @JoinColumn(name = "word_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "antonym_id", referencedColumnName = "id"))
    private Set<Antonym> antonyms;

    @Transient
    private String categoryId; // It uses to get category id from spring form edit word

    public Word() {
    }

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
        this.wordEng = wordEng.toLowerCase();
    }

    public String getWordUkr() {
        return wordUkr;
    }

    public void setWordUkr(String wordUkr) {
        this.wordUkr = wordUkr.toLowerCase();
    }

    public String getSpeechPart() {
        return speechPart;
    }

    public void setSpeechPart(String speechPart) {
        this.speechPart = speechPart;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public Set<Antonym> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(Set<Antonym> antonyms) {
        this.antonyms = antonyms;
    }

    public void addSynonym(Synonym synonym){
        this.synonyms.add(synonym);
    }

    public void addAntonym(Antonym antonym) {this.antonyms.add(antonym);}
}
