package com.example.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class MainFragment extends Fragment {

    private String city;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.app_bar_main, container, false);


        city = (fragmentView.findViewById(R.id.editText)).toString();

        Button button = fragmentView.findViewById(R.id.button_city);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnFragmentInteractionListener mListener = (OnFragmentInteractionListener) getActivity();
                assert mListener != null;
                mListener.onFragmentInteraction(city);
            }
        });

        setRecycleView(fragmentView);

        return fragmentView;
    }


    private void setRecycleView(View fragmentView) {
        CityData builder = new CityData(getResources());
        final List<String> dataSource = builder.build();

        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final CityAdapter adapter = new CityAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                OnFragmentInteractionListener mListener = (OnFragmentInteractionListener) getActivity();
                assert mListener != null;
                mListener.onFragmentInteraction(data);
            }
        });
    }
}
