package com.flipkart.bean;

public class Branch {
    private String id;
    private String name;
    private Professor hod;

    public Branch(String id, String name, Professor hod) {
        this.id = id;
        this.name = name;
        this.hod = hod;
    }

    public Branch() {
    }

    public Branch(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
