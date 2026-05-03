package com.quilca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import org.springframework.stereotype.Controller;

@Controller
public class StatusBarController {

    @FXML
    private ComboBox<Integer> zoomCombo;
    private BorderPane rootLayout;

    @FXML
    public void initialize() {
        zoomCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object + "%";
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string.replace("%", ""));
            }
        });
        zoomCombo.setValue(100);
    }

    public void onZoomChange() {
        Integer value = zoomCombo.getValue();
        double newZoom = 14 * ((double) value / 100);
        rootLayout.setStyle("-fx-font-size: " + newZoom + "px;");
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }
}