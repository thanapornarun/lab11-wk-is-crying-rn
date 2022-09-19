module ku.cs.lab11_dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs.lab11_dictionary to javafx.fxml;
    exports ku.cs.lab11_dictionary;

    exports ku.cs.lab11_dictionary.controller;
    opens ku.cs.lab11_dictionary.controller to javafx.fxml;
}