package com.example.pasaronline.ui.home.pasar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pasaronline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasarFragment extends Fragment {


    public PasarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasar, container, false);
    }

}
