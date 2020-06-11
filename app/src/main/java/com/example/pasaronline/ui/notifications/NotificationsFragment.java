package com.example.pasaronline.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pasaronline.R;
import com.example.pasaronline.ui.account.CreateAccountFragment;
import com.example.pasaronline.ui.login.LoginFragment;

public class NotificationsFragment extends Fragment {

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private EditText inputEmail, inputPassword;
    private Button btnLogin, btndaftar;
    private TextView loginAdmin;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = root.findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.notification, loginFragment).commit();
            }
        });

        inputEmail = root.findViewById(R.id.email);
        inputPassword = root.findViewById(R.id.kata_sandi);

        btndaftar = root.findViewById(R.id.daftar);
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccountFragment createAccountFragment = new CreateAccountFragment();
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.notification, createAccountFragment).commit();
            }
        });
        loginAdmin = root.findViewById(R.id.loginAdmin);

        return root;
    }
}
