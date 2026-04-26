package com.quilca.controller;

import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class FormatBarController {

    private final EditorService editorService;

    public FormatBarController(EditorService editorService) {
        this.editorService = editorService;
    }

    @FXML
    private ComboBox<Integer> fontSize;

    @FXML
    private Button colorButton;
    private Popup colorPopup;

    @FXML
    private void onBold() {
        editorService.toggleBold();
    }

    @FXML
    private void onItalic() {
        editorService.toggleItalic();
    }

    @FXML
    private void onSizeChange() {
        Integer size = fontSize.getValue();
        if (size != null) editorService.setSize(size);
    }

    private static final Map<String, String> COLORS = new LinkedHashMap<>() {{
        put("color-black", "#000000");
        put("color-white", "#FFFFFF");
        put("color-red", "#FF0000");
        put("color-dark-red", "#C00000");
        put("color-orange", "#FF6600");
        put("color-yellow", "#FFD700");
        put("color-green", "#00B050");
        put("color-dark-green", "#375623");
        put("color-blue", "#0070C0");
        put("color-dark-blue", "#002060");
        put("color-purple", "#7030A0");
        put("color-gray", "#808080");
    }};

    @FXML
    public void initialize() {
        colorPopup = new Popup();
        FlowPane grid = new FlowPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(5));
        grid.setPrefWrapLength(168);
        grid.setStyle("-fx-background-color: white; -fx-border-color: #cccccc;");

        COLORS.forEach((cssClass, hex) -> {
            Rectangle rect = new Rectangle(30, 30);
            rect.setFill(Color.web(hex));
            rect.setStroke(Color.LIGHTGRAY);
            rect.setStrokeWidth(1.5);
            rect.setOnMouseEntered(_ -> rect.setStroke(Color.BLACK));
            rect.setOnMouseExited(_ -> rect.setStroke(Color.LIGHTGRAY));
            rect.setOnMouseClicked(_ -> {
                editorService.setColor(cssClass);
                colorPopup.hide();
            });
            grid.getChildren().add(rect);
        });

        colorPopup.getContent().add(grid);
        colorPopup.setAutoHide(true);
    }

    @FXML
    private void onColorPicker() {
        if (colorPopup.isShowing()) {
            colorPopup.hide();
        } else {
            Bounds bounds = colorButton.localToScreen(colorButton.getBoundsInLocal());
            colorPopup.show(colorButton, bounds.getMinX(), bounds.getMaxY());
        }
    }
}