package ku.cs.lab11_dictionary.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.lab11_dictionary.models.Dictionary;
import ku.cs.lab11_dictionary.models.Vocabulary;
import ku.cs.lab11_dictionary.services.DataSource;
import ku.cs.lab11_dictionary.services.DictionaryFileDataSource;

public class DictionaryHashMapController {
    @FXML private ListView<String> dictionaryView;

    @FXML private Label wordLabel;
    @FXML private Label partOfSpeechLabel;
    @FXML private Label meaningWordLabel;
    @FXML private TextArea sentencesTextArea;

    private DataSource<Dictionary> dataSource;
    private Dictionary dictionary;

    @FXML
    public void initialize() {
        dataSource = new DictionaryFileDataSource("data", "dictionary.csv");
        showListView();
        clearSelectedDictionary();
        handleSelectedListView();
    }

    private void showListView() {
        dictionary = dataSource.readData();
        dictionaryView.getItems().addAll(dictionary.getAllVocabulary());
        dictionaryView.refresh();
    }

    private void handleSelectedListView(){
        dictionaryView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue);
                showSelectedDictionary(newValue);
            }
        });
    }

    private void showSelectedDictionary(String v) {
        Vocabulary vocab = dictionary.find(v);
        wordLabel.setText(vocab.getWord());
        partOfSpeechLabel.setText(vocab.getPartOfSpeech());
        meaningWordLabel.setText(vocab.getMeaningWord());

        String line = "";
        for (String s : vocab.getWordSentences()) {
            line = line + s + "\n";
        }
        sentencesTextArea.setText(line);
    }
    private void clearSelectedDictionary() {
        wordLabel.setText("");
        partOfSpeechLabel.setText("");
        meaningWordLabel.setText("");
        sentencesTextArea.setText("");
    }
}
