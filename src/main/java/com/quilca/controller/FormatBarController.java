package com.quilca.controller;

import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

@Component
public class FormatBarController {

    private final EditorService editorService;

    @FXML
    private ComboBox<Integer> fontSize;

    @FXML
    private ColorPicker colorPicker;

    public FormatBarController(EditorService editorService) {
        this.editorService = editorService;
    }

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

    @FXML
    private void onColorChange() {
        Color color = colorPicker.getValue();
        String hex = String.format("#%02X%02X%02X",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
        editorService.setColor(hex);
    }
}