package com.example.pasaronline.ui.home.supermarket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pasaronline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupermarketFragment extends Fragment {


    public SupermarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supermarket, container, false);
    }

}
