module com.example.chessgameteam10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
                requires com.almasb.fxgl.all;

    opens com.example.chessboard to javafx.fxml, javafx.graphics;
    exports com.example.chessboard;
}