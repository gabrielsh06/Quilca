package com.quilca.model;

public record TextStyle(
        boolean bold,
        boolean italic,
        int size,
        String color)
{
    public static TextStyle forDefault() {
        return new TextStyle(false, false, 14, "black");
    }

    public TextStyle withBold(boolean bold) {return new TextStyle(bold, this.italic, this.size, this.color);}

    public TextStyle withItalic(boolean italic) {return new TextStyle(this.bold, italic, this.size, this.color);}

    public TextStyle withSize(int size) {return new TextStyle(this.bold, this.italic, size, this.color);}

    public TextStyle withColor(String color) {return new TextStyle(this.bold, this.italic, this.size, color);}
}