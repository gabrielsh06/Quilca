package com.quilca.model;

import java.util.Set;
import java.util.HashSet;

public record TextStyle(Set<String> classes) {

    public static TextStyle forDefault() {
        return new TextStyle(new HashSet<>());
    }

    public TextStyle withBold(boolean bold) {
        Set<String> newClasses = new HashSet<>(classes);
        if (bold) newClasses.add("bold");
        else newClasses.remove("bold");
        return new TextStyle(newClasses);
    }

    public TextStyle withItalic(boolean italic) {
        Set<String> newClasses = new HashSet<>(classes);
        if (italic) newClasses.add("italic");
        else newClasses.remove("italic");
        return new TextStyle(newClasses);
    }

    public TextStyle withSize(int size) {
        Set<String> newClasses = new HashSet<>(classes);
        newClasses.removeIf(c -> c.startsWith("size-"));
        newClasses.add("size-" + size);
        return new TextStyle(newClasses);
    }

    public TextStyle withColor(String color) {
        Set<String> newClasses = new HashSet<>(classes);
        newClasses.removeIf(c -> c.startsWith("color-"));
        newClasses.add(color);
        return new TextStyle(newClasses);
    }

    public boolean isBold() {
        return classes.contains("bold");
    }

    public boolean isItalic() {
        return classes.contains("italic");
    }
}