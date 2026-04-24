package com.quilca.controller;

import com.quilca.model.TextStyle;
import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyledTextArea;
import org.springframework.stereotype.Component;

import java.io.File;

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

    @FXML
    public void handleSave() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = chooser.showSaveDialog(editorService.getEditorArea().getScene().getWindow());
        if (file != null) {
            String content = editorService.getEditorArea().getText();
            editorService.saveText(file.getAbsolutePath(), content);
        }
    }

    @FXML
    private void handleRead() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(editorService.getEditorArea().getScene().getWindow());

        if (file != null) {
            String content = editorService.readText(file.getAbsolutePath());
            editorService.getEditorArea().replaceText(content);
        }
    }
}