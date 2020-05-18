package com.example.mylogin.Shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mylogin.Frag3;
import com.example.mylogin.R;

public class ShopFrag extends Fragment {

    private View view;
    private TextView tv_sample2;
    private Button btn_shop2;
    private String result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopfrag, container, false);

        tv_sample2 = view.findViewById(R.id.tv_sample2);
        btn_shop2 = view.findViewById(R.id.btn_shop2);

        if (getArguments() != null) {
            result = getArguments().getString("fromFrag3");
            tv_sample2.setText(result);
        }

        btn_shop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fromShopFrag","거래글 목록 표시");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Frag3 frag3 = new Frag3();
                frag3.setArguments(bundle);
                transaction.replace(R.id.main_frame, frag3);
                transaction.commit();//저장
            }
        });

        return view;
    }
}
