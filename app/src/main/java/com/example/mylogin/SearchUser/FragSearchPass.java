package com.example.mylogin.SearchUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylogin.R;

public class FragSearchPass extends Fragment {
    private View view;

    public static FragSearchPass newInstance()
    {
        FragSearchPass fragSearchPass = new FragSearchPass();
        return fragSearchPass;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_searchpass, container, false);

        return view;
    }
}
