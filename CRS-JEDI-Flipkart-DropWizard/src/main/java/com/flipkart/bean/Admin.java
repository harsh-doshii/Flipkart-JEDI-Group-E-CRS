package com.flipkart.bean;

public class Admin extends User {
    private String adminId;

    public Admin() {
    }

    public Admin(String name, String gender, String address, String username, String password, int id, int roleId, String dob) {
        super(name, gender, address, username, password, id, roleId, dob);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


}
