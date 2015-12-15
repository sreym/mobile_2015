package com.anna.listwork;

/**
 * Created by anna on 08.12.15.
 */
public class ListItem {
    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    private String text;
    private int id;

    public ListItem(String text, int id) {
        this.id = id;
        this.text = text;
    }
}
