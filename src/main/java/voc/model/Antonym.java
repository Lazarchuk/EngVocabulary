package voc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "antonyms")
@NamedQuery(name = "Antonym.getAll", query = "SELECT c From Antonym c")
public class Antonym {

    public Antonym() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "antonyms")
    @JsonIgnore
    private Set<Word> words;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
