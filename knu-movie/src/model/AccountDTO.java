package model;

import java.sql.Date;

public class AccountDTO {
    private String email_id;
    private String password;
    private String phone_number;
    private String name;
    private String address;
    private char gender;
    private Date birth_date;
    private String Job;
    private String membership;
    private Boolean isAdmin;

    public AccountDTO(String email_id, String password, String phone_number, String name, String address, char gender,
            Date birth_date, String job, String membership, Boolean isAdmin) {
        this.email_id = email_id;
        this.password = password;
        this.phone_number = phone_number;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birth_date = birth_date;
        Job = job;
        this.membership = membership;
        this.isAdmin = isAdmin;
    }

    public String getAddress() {
        return address;
    }
    public Date getBirth_date() {
        return birth_date;
    }
    public String getEmail_id() {
        return email_id;
    }
    public char getGender() {
        return gender;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public String getJob() {
        return Job;
    }
    public String getMembership() {
        return membership;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setJob(String job) {
        Job = job;
    }
    public void setMembership(String membership) {
        this.membership = membership;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}