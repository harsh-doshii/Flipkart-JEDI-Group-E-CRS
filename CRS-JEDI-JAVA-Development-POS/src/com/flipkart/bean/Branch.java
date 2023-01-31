package com.flipkart.bean;

public class Branch {
    private String id;
    private String name;
    private Professor hod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getHod() {
        return hod;
    }

    public void setHod(Professor hod) {
        this.hod = hod;
    }
}
