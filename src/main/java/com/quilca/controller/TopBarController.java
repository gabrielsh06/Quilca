package com.quilca.controller;

import com.quilca.service.EditorService;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class TopBarController {

    private final HostServices hostServices;
    private final EditorService editorService;
    public HBox topbar;

    public TopBarController(HostServices hostServices, EditorService editorService) {
        this.hostServices = hostServices;
        this.editorService = editorService;
    }

    @FXML
    private void about() {
        hostServices.showDocument("https://github.com/gabrielsh06/Quilca");
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