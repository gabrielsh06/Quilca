package com.quilca.service;

import com.quilca.model.TextStyle;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.SimpleEditableStyledDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EditorService {

    private StyledTextArea<TextStyle, TextStyle> editorArea;
    public EditorService () {
        editorArea = new StyledTextArea<>(
                TextStyle.forDefault(),
                (text, style) -> {
                },
                TextStyle.forDefault(),
                (text, style) -> {
                    StringBuilder sb = new StringBuilder();
                    if (style.bold()) sb.append("-fx-font-weight: bold;");
                    if (style.italic()) sb.append("-fx-font-style: italic;");
                    if (style.size() > 0) sb.append("-fx-font-size: ").append(style.size()).append("px;");
                    if (style.color() != null) sb.append("-fx-fill: ").append(style.color()).append(";");
                    text.setStyle(sb.toString());
                },
                new SimpleEditableStyledDocument<>(TextStyle.forDefault(), TextStyle.forDefault()), true
        );
    }

    public StyledTextArea<TextStyle, TextStyle> getEditorArea() {
        return editorArea;
    }

    public void saveText(String filePath, String content) {
        try {
            Files.writeString(Paths.get(filePath), content);
        } catch (IOException e) {
            System.out.println("Failed to save file");
        }
    }

    public String readText(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Failed to read file");
            return "";
        }
    }
}