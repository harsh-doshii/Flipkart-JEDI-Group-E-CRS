package com.flipkart.bean;

public abstract class User {
    private String name;
    private String gender;
    private String address;
    private String username;
    private String password;
    private int id;
    private String role;

    private  int roleId;
    private String dob;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public User() {
    }

    public User(String name, String gender, String address, String username, String password, int id, int roleId, String dob) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
        this.id = id;
        this.roleId = roleId;
        this.dob = dob;
    }

    public User(String name,String gender, String address,String username,String  password,String  dob){
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
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

    public Integer getId() {
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
