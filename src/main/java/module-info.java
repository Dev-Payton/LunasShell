module com.example.lunasshell {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lunasshell to javafx.fxml;
    exports com.example.lunasshell;
}