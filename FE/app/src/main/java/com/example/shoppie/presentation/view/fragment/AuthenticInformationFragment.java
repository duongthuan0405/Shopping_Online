package com.example.shoppie.presentation.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppie.R;
import com.example.shoppie.databinding.FragmentAuthenticInformationBinding;
import com.example.shoppie.databinding.FragmentPersonalInformationBinding;
import com.example.shoppie.presentation.contract_vp.AuthenticInformation_F_Contract;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.AuthenticInformation_F_Presenter;
import com.example.shoppie.presentation.view.viewmodel_data.SignUpViewModel;

public class AuthenticInformationFragment extends Fragment implements AuthenticInformation_F_Contract.IView{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private AuthenticInformation_F_Contract.IPresenter presenter = new AuthenticInformation_F_Presenter(this);
    private FragmentAuthenticInformationBinding binding;
    private SignUp_A_Contract.IView parentActivity;

    public AuthenticInformationFragment() {
        // Required empty public constructor
    }

    public static AuthenticInformationFragment newInstance(String param1, String param2) {
        AuthenticInformationFragment fragment = new AuthenticInformationFragment();
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

        parentActivity = (SignUp_A_Contract.IView) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_authentic_information, container, false);
        binding = FragmentAuthenticInformationBinding.inflate(inflater, container, false);

        binding.txVwBack.setOnClickListener(v -> onClick_txVwBack());

        return binding.getRoot();
    }

    private void onClick_txVwBack() {
        presenter.onClick_txVwBack();
    }

    @Override
    public void handleAsSystemBackPress() {
        parentActivity.handleAsBackPressSystem();
    }
}