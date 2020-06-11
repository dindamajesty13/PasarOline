package com.example.pasaronline.ui.account;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasaronline.R;
import com.example.pasaronline.ui.login.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    private Button btnDaftar;
    private EditText inputName, inputNumber, inputAlamat, inputEmail, inputPassword;
    private ProgressDialog loadingBar;


    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        btnDaftar = view.findViewById(R.id.daftarNow);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }

        });

        inputName = view.findViewById(R.id.name);

        inputNumber = view.findViewById(R.id.number);

        inputAlamat = view.findViewById(R.id.alamat);

        inputEmail = view.findViewById(R.id.email);

        inputPassword = view.findViewById(R.id.password);

        loadingBar = new ProgressDialog(getActivity());

        return view;
    }

    private void CreateAccount() {
        String name = inputName.getText().toString();
        String number = inputNumber.getText().toString();
        String alamat = inputAlamat.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "Tolong isikan nama kamu...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(number)) {
            Toast.makeText(getActivity(), "Tolong isikan nomor whatsapp kamu...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(alamat)) {
            Toast.makeText(getActivity(), "Tolong isikan alamat kamu...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Tolong isikan email kamu...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Tolong isikan password kamu...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Mohon tunggu hingga proses selesai");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhoneNumber(name, number, alamat, email, password);
        }

    }


    private void ValidatePhoneNumber(final String name, final String number, final String alamat, final String email, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users")).child(number).exists()) {
                    HashMap<String, Object> userdataMap = new HashMap<>();

                    userdataMap.put("nama", name);
                    userdataMap.put("nomor", number);
                    userdataMap.put("alamat", alamat);
                    userdataMap.put("email", email);
                    userdataMap.put("password", password);

                    RootRef.child("Users").child(number).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getActivity(), "Selamat, akun anda telah berhasil dibuat", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        LoginFragment loginFragment = new LoginFragment();
                                        FragmentManager fm = getChildFragmentManager();
                                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                        fragmentTransaction.replace(R.id.loginFragment, loginFragment).commit();
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(getActivity(), "Koneksi Eror, Mohon Coba Lagi", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                } else {
                    Toast.makeText(getActivity(), "Nomor" + number +"sudah digunakan", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(getActivity(), "Tolong ulangi kembali menggunakan nomor lainnya", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

