package com.quilca.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class MainLayoutController {

    @FXML
    private BorderPane mainLayout;

    @FXML
    private VBox sidebar;

    @FXML
    public void initialize() {
        Platform.runLater(() -> sidebar.prefWidthProperty().bind(
                mainLayout.widthProperty().multiply(0.25)
        ));
    }
}