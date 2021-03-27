package com.dorasima.shareforall.ui.register;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorasima.shareforall.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterForm2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterForm2 extends Fragment {

    private RegisterFormViewModel mViewModel;

    public static RegisterForm2 newInstance() {
        return new RegisterForm2();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_form_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterFormViewModel.class);
        // TODO: Use the ViewModel
    }
}