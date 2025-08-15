package com.example.shoppie.model.dto;

import com.example.shoppie.presentation.dto.User;
import com.example.shoppie.staticclass.StaticClass;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MUser {
    String fullName;
    String phoneNumber;
    String birthday;
    @Exclude
    String id;

    public MUser(String fullName, String phoneNumber, String birthday) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public MUser(String fullName, String phoneNumber, String birthday, String id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.id = id;
    }

    public MUser()
    {

    }

    public MUser(User user) {
        this.fullName = user.getFullName();
        this.birthday = user.getBirthday();
        this.phoneNumber = user.getPhoneNumber();
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

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "MUser{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday='" + birthday + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
