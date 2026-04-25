package com.quilca.controller;

import com.quilca.model.TextStyle;
import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyledTextArea;
import org.springframework.stereotype.Component;

@Component
public class EditorController {

    private final EditorService editorService;

    @FXML
    private StackPane editorContainer;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @FXML
    public void initialize(){
        VirtualizedScrollPane<StyledTextArea<TextStyle, TextStyle>> scroll = new VirtualizedScrollPane<>(
                editorService.getEditorArea()
        );
        editorContainer.getChildren().add(scroll);
    }
}