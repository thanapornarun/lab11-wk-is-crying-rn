package ku.cs.lab11_dictionary.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {
    private Map<String, Vocabulary> dictionary;

    public Dictionary() {
        dictionary = new HashMap<>();
    }

    public void addVocabulary(Vocabulary v) {
        dictionary.put(v.getWord(), v);
    }

    public Vocabulary find(String vocab){
        Vocabulary found = dictionary.get(vocab);
        if (found == null){
            throw new RuntimeException(vocab + " not found in dictionary");
        }
        return found;
    }

    public void defineWordMeaning(String vocab, String meaning){
        Vocabulary found = find(vocab);
        found.defineMeaning(meaning);
    }

    public void addWordExample(String vocab, String wordSentences){
        Vocabulary found = find(vocab);
        found.addSentence(wordSentences);
    }

    public Set<String> getAllVocabulary() {
        return dictionary.keySet();
    }
}
