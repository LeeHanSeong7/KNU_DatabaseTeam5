package knu.movie.app.pd.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonFormat;

import knu.movie.app.pd.interfaces.DTO;
import knu.movie.app.pd.model.AccountDAO.ACCOUNT;

public class AccountDTO implements DTO {
    private String email_id;
    private String password;
    private String phone_number;
    private String name;
    private String address;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birth_date;
    private String job;
    private String membership;
    private Boolean isAdmin;

    public AccountDTO() {
        super();
    }

    public AccountDTO(AccountDTO a) {
        this.email_id = a.email_id;
        this.password = a.password;
        this.phone_number = a.phone_number;
        this.name = a.name;
        this.address = a.address;
        this.gender = a.gender;
        this.birth_date = a.birth_date==null ? null : new Date(a.birth_date.getTime());
        this.job = a.job;
        this.membership = a.membership;
        this.isAdmin = a.isAdmin;
    }

    public AccountDTO(String email_id, String password, String phone_number, String name, String address, String gender,
            Date birth_date, String job, String membership, Boolean isAdmin) {
        this.email_id = email_id;
        this.password = password;
        this.phone_number = phone_number;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birth_date = birth_date;
        this.job = job;
        this.membership = membership;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        AccountDTO a = (AccountDTO) o;
        return this.email_id.equals(a.email_id) &&
                this.password.equals(a.password) &&
                this.phone_number.equals(a.phone_number) &&
                this.name.equals(a.name) &&
                this.address.equals(a.address) &&
                this.gender.equals(a.gender) &&
                this.birth_date.equals(a.birth_date) &&
                this.job.equals(a.job) &&
                this.membership.equals(a.membership) &&
                this.isAdmin.equals(a.isAdmin);
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

    public String getGender() {
        return gender;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public String getJob() {
        return job;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setJob(String job) {
        this.job = job;
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

    public static AccountDTO fromResultSet(ResultSet rs) {
        try {
            return new AccountDTO(rs.getString(ACCOUNT.EMAIL_ID), rs.getString(ACCOUNT.PASSWORD),
                    rs.getString(ACCOUNT.PHONE_NUMBER), rs.getString(ACCOUNT.NAME), rs.getString(ACCOUNT.ADDRESS),
                    rs.getString(ACCOUNT.GENDER), rs.getDate(ACCOUNT.BIRTH_DATE), rs.getString(ACCOUNT.JOB),
                    rs.getString(ACCOUNT.MEMBERSHIP), rs.getBoolean(ACCOUNT.IS_ADMIN));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nAccountDTO.fromResultSet rs error please check null ");
        }
        return null;
	}
}