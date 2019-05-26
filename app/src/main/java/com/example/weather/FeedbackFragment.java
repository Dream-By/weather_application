package com.example.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FeedbackFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.nav_feedback, container, false);

        return getView(fragmentView);
    }

    private View getView(View fragmentView) {
        final EditText feedback = fragmentView.findViewById(R.id.feedback);
        Button button = fragmentView.findViewById(R.id.button_feedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback.setText("");
            }
        });
        return fragmentView;
    }
}
