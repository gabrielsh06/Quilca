package com.quilca.controller;

import com.quilca.service.EditorService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.InlineCssTextArea;
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
    private final InlineCssTextArea editorArea = new InlineCssTextArea();
    @FXML
    public void initialize(){
        editorArea.setWrapText(true);
        VirtualizedScrollPane<InlineCssTextArea> scroll = new VirtualizedScrollPane<>(editorArea);
        editorContainer.getChildren().add(scroll);
    }

    @FXML
    public void handleSave() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = chooser.showSaveDialog(editorArea.getScene().getWindow());
        if (file != null) {
            String content = editorArea.getText();
            editorService.saveText(file.getAbsolutePath(), content);
        }
    }

    @FXML
    private void handleRead() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(editorArea.getScene().getWindow());

        if (file != null) {
            String content = editorService.readText(file.getAbsolutePath());
            editorArea.replaceText(content);
        }
    }
}