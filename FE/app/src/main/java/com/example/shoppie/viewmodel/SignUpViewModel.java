package com.example.shoppie.viewmodel;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.BR;
import com.example.shoppie.once_event.OnceEvent;
import com.example.shoppie.presentation.view.fragment.AuthenticInformationFragment;
import com.example.shoppie.presentation.view.fragment.PersonalInformationFragment;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.jvm.functions.Function1;

public class SignUpViewModel extends ViewModel
{
    // <editor-fold desc="Region: Data Fields">
    MutableLiveData<String> fullName;
    MutableLiveData<String> phoneNumber;
    MutableLiveData<String> birthday;
    MutableLiveData<String> email;
    MutableLiveData<String> password;
    LiveData<String> strPosition;
    ArrayList<Fragment> fragments;
    MutableLiveData<Integer> position;

    MutableLiveData<Boolean> isValidFullName;
    MutableLiveData<Boolean> isValidPhoneNumber;
    MutableLiveData<Boolean> isValidBirthday;
    MutableLiveData<Boolean> isValidEmail;
    MutableLiveData<Boolean> isValidPassword;
    // </editor-fold>

    // <editor-fold desc="Region: Event Fields">
    MutableLiveData<Boolean> isShowProcessBar;
    MutableLiveData<OnceEvent<Boolean>> asBackSystemEvent;
    MutableLiveData<OnceEvent<Boolean>> changeNextFragmentEvent;
    // </editor-fold>

    // <editor-fold desc="Region: Constructor">

    public SignUpViewModel()
    {
        fullName = new MutableLiveData<>("");
        phoneNumber = new MutableLiveData<>("");
        birthday = new MutableLiveData<>("");
        email = new MutableLiveData<>("");
        password = new MutableLiveData<>("");
        position = new MutableLiveData<>(0);

        strPosition = (LiveData<String>)Transformations.map(position, new Function1<Integer, String>() {
            @Override
            public String invoke(Integer integer) {
                return String.format("%d of %d", integer + 1, fragments.size());
            }
        });

        isShowProcessBar = new MutableLiveData<>();

        fragments = new ArrayList<>();
        fragments.add(new PersonalInformationFragment());
        fragments.add(new AuthenticInformationFragment());
        asBackSystemEvent = new MutableLiveData<>();
        changeNextFragmentEvent = new MutableLiveData<>(new OnceEvent<>(true));

        isValidFullName = (MutableLiveData<Boolean>) Transformations.map(fullName, new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return true;
            }
        });
        isValidBirthday = (MutableLiveData<Boolean>) Transformations.map(birthday, new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return true;
            }
        });

        isValidPhoneNumber = (MutableLiveData<Boolean>) Transformations.map(phoneNumber, new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return true;
            }
        });

        isValidEmail = (MutableLiveData<Boolean>) Transformations.map(email, new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return true;
            }
        });

        isValidPassword = (MutableLiveData<Boolean>) Transformations.map(password, new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return true;
            }
        });
    }
    // </editor-fold>

    // <editor-fold desc="Region: Getter & Setter">
    public LiveData<String> getStrPosition() {
        return strPosition;
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

    public LiveData<Boolean> getIsShowProcessBar() {
        return isShowProcessBar;
    }
    public Fragment getCurrentFragment()
    {
        return fragments.get(position.getValue());
    }

    public void setShowProcessBar(boolean isShow)
    {
        isShowProcessBar.setValue(isShow);
    }

    public LiveData<OnceEvent<Boolean>> getChangeNextFragmentEvent() {
        return changeNextFragmentEvent;
    }
    public LiveData<OnceEvent<Boolean>> getAsBackSystemEvent() {
        return asBackSystemEvent;
    }

    public int getPosition() {
        return position.getValue();
    }

    public LiveData<Boolean> getIsValidFullName() {
        return isValidFullName;
    }

    public LiveData<Boolean> getIsValidPhoneNumber() {
        return isValidPhoneNumber;
    }

    public LiveData<Boolean> getIsValidBirthday() {
        return isValidBirthday;
    }

    public LiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public LiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }
    // </editor-fold>

    // <editor-fold desc="Region: Methods">
    public void onClick_BackFragment()
    {
        asBackSystemEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_Next_FPersonalInfo()
    {

        boolean isValid = true;
        if(fullName.getValue() == null || fullName.getValue().isEmpty())
        {
            isValid = false;
            isValidFullName.setValue(false);
        }
        if(phoneNumber.getValue() == null || phoneNumber.getValue().isEmpty())
        {
            isValid = false;
            isValidPhoneNumber.setValue(false);
        }
        if(birthday.getValue() == null || birthday.getValue().isEmpty())
        {
            isValid = false;
            isValidBirthday.setValue(false);
        }
        if(!isValid)
        {
            return;
        }

        moveToNextPosition();
        changeNextFragmentEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_btnSignUp()
    {
        Log.d("THUAN", "Sign Up button has been pressed");
    }

    public void moveToNextPosition()
    {
        int pos = position.getValue() + 1;
        if(pos >= fragments.size())
        {
            return;
        }

        position.setValue(pos);
    }

    public void backToPreviousPosition()
    {
        int pos = position.getValue() - 1;
        if(pos >= 0)
        {
            position.setValue(pos);
        }
    }
    // </editor-fold>
}
