package ku.cs.lab11_dictionary.models;

import java.util.ArrayList;
import java.util.Set;

public class Vocabulary {
    String word;
    String partOfSpeech;
    String meaningWord;
    ArrayList<String> wordSentences;

    public Vocabulary(String word, String partOfSpeech, String meaningWord, ArrayList<String> wordSentences) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.meaningWord = meaningWord;
        this.wordSentences = wordSentences;
    }

    public Vocabulary(String word, String partOfSpeech, String meaningWord) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.meaningWord = meaningWord;
    }

    public Vocabulary() {
        this.word = "";
        this.partOfSpeech = "";
        this.meaningWord = "";
        this.wordSentences = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getMeaningWord() {
        return meaningWord;
    }

    public ArrayList<String> getWordSentences() {
        return wordSentences;
    }

    public void defineWord(String word) { this.word = word; }
    public void defineMeaning(String meaning){
        this.meaningWord = meaning;
    }

    public void definePartOfSpeech(String partOfSpeech){
        this.partOfSpeech = partOfSpeech;
    }

    public void addSentence(String sentence){
        this.wordSentences.add(sentence);
    }

    @Override
    public String toString() {
        return word + " [" + partOfSpeech + " " + meaningWord + " " + wordSentences + " ]";
    }
}
