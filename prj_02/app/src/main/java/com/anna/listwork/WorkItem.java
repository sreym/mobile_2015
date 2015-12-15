package com.anna.listwork;

/**
 * Created by anna on 08.12.15.
 */
public class WorkItem {
    private String name;

    public int getId() {
        return id;
    }

    private int id;
    private int done;

    public WorkItem(String name, int id, int done) {
        this.name = name;
        this.id = id;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return done == 1 ? true : false;
    }
}
