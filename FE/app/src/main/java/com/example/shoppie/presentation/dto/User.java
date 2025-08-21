package com.example.shoppie.presentation.dto;

import com.example.shoppie.staticclass.StaticClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String birthday;

    public User(String fullName, String phoneNumber, String birthday) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public User(String id, String fullName, String phoneNumber, String birthday) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public User()
    {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public LocalDate get_LocalDate_Birthday()
    {
        return LocalDate.parse(birthday, DateTimeFormatter.ofPattern(StaticClass.DATE_FORMAT));
    }
}
