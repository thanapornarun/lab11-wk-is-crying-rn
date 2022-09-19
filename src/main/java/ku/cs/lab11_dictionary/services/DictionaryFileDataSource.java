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
                Vocabulary v = new Vocabulary(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim()
                );
                v.addSentence(data[3].trim());
                v.addSentence(data[4].trim());
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
                Vocabulary v = dic.find(word);// find word
                String line = v.getWord().trim() + "," + v.getPartOfSpeech().trim() + "," + v.getMeaningWord().trim();
                for (String example : v.getWordSentences()){
                    line = line + "," + example.trim();
                }
                buffer.append(line);
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

