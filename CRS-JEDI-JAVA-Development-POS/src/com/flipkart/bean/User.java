package com.flipkart.bean;

public abstract class User {
    private String name;
    private String gender;
    private String address;
    private String username;
    private String password;
    private int id;
    private String role;
    private String dob;

    public User() {
    }

    public User(String name, String gender, String address, String username, String password, int id, String role, String dob) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
        this.id = id;
        this.role = role;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



}
