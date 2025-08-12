package com.example.shoppie.presentation.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppie.databinding.FragmentPersonalInformationBinding;
import com.example.shoppie.presentation.contract_vp.PersonalInformation_F_Contract;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.PersonalInformation_F_Presenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInformationFragment extends Fragment implements PersonalInformation_F_Contract.IView
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PersonalInformation_F_Contract.IPresenter presenter = new PersonalInformation_F_Presenter(this);
    FragmentPersonalInformationBinding binding;
    SignUp_A_Contract.IView parentActivity;

    public PersonalInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInformationFragment newInstance(String param1, String param2) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        parentActivity = (SignUp_A_Contract.IView) getActivity();

    }

    private void onClick_btnNext(View v) {
        presenter.onClick_btnNext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_personal_information, container, false);
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false);

        binding.btnNext.setOnClickListener(v -> onClick_btnNext(v));
        binding.txVwBack.setOnClickListener(v -> onClick_btnBack(v));

        return binding.getRoot();
    }

    private void onClick_btnBack(View v) {
        presenter.onClick_btnBack();
    }

    @Override
    public void changeTo_F_AuthenticInfo() {
        parentActivity.changeTo_F_AuthenticInfo();
    }

    @Override
    public void handleAsSystemBackPress() {
        parentActivity.handleAsBackPressSystem();
    }
}