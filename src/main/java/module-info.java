module controller {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;

    opens controller to javafx.fxml;
    exports controller;
}
