package com.quilca.service;

import com.quilca.model.TextStyle;
import javafx.scene.control.IndexRange;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.SimpleEditableStyledDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.UnaryOperator;

@Service
public class EditorService {

    private StyledTextArea<TextStyle, TextStyle> editorArea;

    public EditorService() {}

    public StyledTextArea<TextStyle, TextStyle> getEditorArea() {
        if (editorArea == null) {
            editorArea = new StyledTextArea<>(
                    TextStyle.forDefault(),
                    (text, style) -> {},
                    TextStyle.forDefault(),
                    (text, style) -> {
                        StringBuilder sb = new StringBuilder();
                        if (style.bold()) sb.append("-fx-font-weight: bold;");
                        if (style.italic()) sb.append("-fx-font-style: italic;");
                        if (style.size() > 0) sb.append("-fx-font-size: ").append(style.size()).append("px;");
                        if (style.color() != null) sb.append("-fx-fill: ").append(style.color()).append(";");
                        text.setStyle(sb.toString());
                    },
                    new SimpleEditableStyledDocument<>(TextStyle.forDefault(), TextStyle.forDefault()),
                    true
            );
        }
        return editorArea;
    }

    public void applyStyle(UnaryOperator<TextStyle> styleUpdater) {
        IndexRange selection = editorArea.getSelection();
        if (selection.getLength() > 0) {
            TextStyle current = editorArea.getStyleAtPosition(selection.getStart());
            editorArea.setStyle(selection.getStart(), selection.getEnd(), styleUpdater.apply(current));
        } else {
            TextStyle current = editorArea.getTextInsertionStyle();
            if (current == null) current = TextStyle.forDefault();
            editorArea.setTextInsertionStyle(styleUpdater.apply(current));
        }
    }

    // Selection styles
    public void toggleBold() {
        applyStyle(style -> style.withBold(!style.bold()));
    }

    public void toggleItalic() {
        applyStyle(style -> style.withItalic(!style.italic()));
    }

    public void setSize(int size) {
        applyStyle(style -> style.withSize(size));
    }

    public void setColor(String color) {
        applyStyle(style -> style.withColor(color));
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