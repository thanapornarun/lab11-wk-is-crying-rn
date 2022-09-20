package ku.cs.lab11_dictionary.services;

import ku.cs.lab11_dictionary.models.Dictionary;
import ku.cs.lab11_dictionary.models.Vocabulary;

import java.io.*;
import java.util.Map;

public class DictionaryFileDataSource implements DataSource<Dictionary> {

    private String directoryName;
    private String fileName;

    public DictionaryFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileExisted();
    }

    private void checkFileExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Dictionary readData() {
        Dictionary dic = new Dictionary();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(","); // แยกด้วยคอมม่า
                Vocabulary v = new Vocabulary();
                v.defineWord(data[0].trim());
                v.definePartOfSpeech(data[1].trim());
                v.defineMeaning(data[2].trim());
                v.addSentence(data[3].trim());
                int i = 4;
                while (data[i] != null) {
                    v.addSentence(data[i].trim());
                    i++;
                }
                dic.addVocabulary(v);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return dic;
    }

    @Override
    public void writeData(Dictionary dic){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (String word : dic.getAllVocabulary()){
                Vocabulary v = dic.find(word);
                StringBuilder line = new StringBuilder(v.getWord().trim() + "," + v.getPartOfSpeech().trim() + "," + v.getMeaningWord().trim());
                for (String sentence : v.getWordSentences()){
                    line.append(",").append(sentence.trim());
                }
                buffer.append(line.toString());
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

