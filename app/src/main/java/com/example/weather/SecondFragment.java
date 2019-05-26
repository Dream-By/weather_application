package com.example.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.second_fragment, container, false);

        Button buttonBack = fragmentView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });
        return fragmentView;
    }

    public void setCity(String city) {
        TextView requiredCity = Objects.requireNonNull(getView()).findViewById(R.id.textView);
        requiredCity.setText(city);
    }
}
