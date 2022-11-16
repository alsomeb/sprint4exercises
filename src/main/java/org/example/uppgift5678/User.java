package org.example.uppgift5678;

import java.io.Serializable;
import java.time.LocalDate;

//MÅSTE impl serializable om man skickar dessa POJO:s
public class User implements Serializable {
    private String fullName;
    private String address;
    private String mobile;
    private LocalDate dateOfBirth;


    public User(String fullName, String address, String mobile, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.address = address;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserData() {
        return fullName + " " + dateOfBirth + " " + address + " " + mobile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
