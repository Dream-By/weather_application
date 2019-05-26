package com.example.weather;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CityData {

    private final List<String> dataSource;
    private final Resources resources;

    CityData(Resources resources) {
        dataSource = new ArrayList<>(6);
        this.resources = resources;
    }

    // строим данные
    List<String> build() {
        String[] descriptions = resources.getStringArray(R.array.cities);
        Collections.addAll(dataSource, descriptions);
        return dataSource;
    }
}
