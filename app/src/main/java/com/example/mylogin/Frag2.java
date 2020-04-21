package com.example.mylogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Frag2 extends Fragment {

    private View view;

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    public String[] data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);


        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);




        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    spinner2.setAdapter(null);
                }else{
                    switch (position){
                        case 1:
                            data = getResources().getStringArray(R.array.서울시);
                            break;
                        case 2:
                            break;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item,data);
                    spinner2.setAdapter(adapter);
                }
            } //spinner1 selected end

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return view;
    }
}
