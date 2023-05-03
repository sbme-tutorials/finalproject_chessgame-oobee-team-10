module com.example.chessgameteam10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens com.example.chessgameteam10 to javafx.fxml,javafx.graphics;
    exports com.example.chessgameteam10;
    exports com.example.chessboard;
}