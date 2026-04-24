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
}