package com.example.shoppie.presentation.view.viewmodel_data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import kotlin.jvm.functions.Function1;

public class SignUpViewModel extends ViewModel
{
    MutableLiveData<String> fullName = new MutableLiveData<>("");
    MutableLiveData<String> phoneNumber = new MutableLiveData<>("");
    MutableLiveData<String> birthday = new MutableLiveData<>("");
    MutableLiveData<String> email = new MutableLiveData<>("");
    MutableLiveData<String> password = new MutableLiveData<>("");
    MutableLiveData<Integer> position = new MutableLiveData<>(1);
    MutableLiveData<String> strPos = (MutableLiveData<String>)
            Transformations.map(position, integer -> String.format("%d of 2", position.getValue()));

    public LiveData<String> getStrPos() {
        return strPos;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getBirthday() {
        return birthday;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public MutableLiveData<String> getFullName() {
        return fullName;
    }

    public void setPosition(int position)
    {
        this.position.setValue(position);
    }

}
