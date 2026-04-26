package com.quilca.service;

import com.quilca.model.TextStyle;
import javafx.scene.control.IndexRange;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

@Service
public class EditorService {

    private StyleClassedTextArea editorArea;

    public EditorService() {}

    public StyleClassedTextArea getEditorArea() {
        if (editorArea == null) {
            editorArea = new StyleClassedTextArea();
            editorArea.setWrapText(true);
            editorArea.setTextInsertionStyle(Set.of("size-12"));
        }
        return editorArea;
    }

    public void applyStyle(UnaryOperator<TextStyle> styleUpdater) {
        if (editorArea == null) return;
        IndexRange selection = editorArea.getSelection();
        if (selection.getLength() > 0) {
            editorArea.setStyleSpans(selection.getStart(),
                    editorArea.getStyleSpans(selection.getStart(), selection.getEnd())
                            .mapStyles(current -> {
                                TextStyle currentStyle = new TextStyle(new HashSet<>(current));
                                return styleUpdater.apply(currentStyle).classes();
                            })
            );
        } else {
            Collection<String> current = editorArea.getTextInsertionStyle();
            TextStyle currentStyle = current == null ? TextStyle.forDefault() : new TextStyle(new HashSet<>(current));
            TextStyle newStyle = styleUpdater.apply(currentStyle);
            editorArea.setTextInsertionStyle(newStyle.classes());
        }
    }

    // Selection styles
    public void toggleBold() {
        applyStyle(style -> style.withBold(!style.isBold()));
    }

    public void toggleItalic() {
        applyStyle(style -> style.withItalic(!style.isItalic()));
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