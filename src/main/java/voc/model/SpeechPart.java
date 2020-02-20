package voc.model;

import java.util.ArrayList;
import java.util.List;

public enum SpeechPart {
    NOUN("Noun"),
    VERB("Verb"),
    ADJECTIVE("Adjective"),
    ADVERB("Adverb"),
    PRONOUN("Pronoun"),
    PREPOSITION("Preposition"),
    CONJUNCTION("Conjunction"),
    NUMERAL("Numeral"),
    ARTICLE("Article"),
    INTERJECTION("Interjection");
    private String name;
    private SpeechPart(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> getSpeechParts(){
        List<String> parts = new ArrayList<>();
        parts.add(SpeechPart.NOUN.getName());
        parts.add(SpeechPart.VERB.getName());
        parts.add(SpeechPart.ADJECTIVE.getName());
        parts.add(SpeechPart.ADVERB.getName());
        parts.add(SpeechPart.PRONOUN.getName());
        parts.add(SpeechPart.PREPOSITION.getName());
        parts.add(SpeechPart.CONJUNCTION.getName());
        parts.add(SpeechPart.NUMERAL.getName());
        parts.add(SpeechPart.ARTICLE.getName());
        parts.add(SpeechPart.INTERJECTION.getName());
        return parts;
    }
}
