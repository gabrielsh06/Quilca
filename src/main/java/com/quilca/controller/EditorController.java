package com.quilca.controller;

import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.stereotype.Controller;

@Controller
public class EditorController {

    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @FXML
    private StackPane editorContainer;

    @FXML
    public void initialize() {
        VirtualizedScrollPane<StyleClassedTextArea> scroll = new VirtualizedScrollPane<>(
                editorService.getEditorArea()
        );
        editorContainer.getChildren().add(scroll);
    }
}