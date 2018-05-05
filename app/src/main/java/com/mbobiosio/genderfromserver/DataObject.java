package com.mbobiosio.genderfromserver;


public class DataObject {
    String id, name;

    public DataObject(String i, String n) {
        id = i;
        name = n;
    }
    public String getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
