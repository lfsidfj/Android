package com.spiderman.example.ui.fragment.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spiderman.example.R;
import com.spiderman.example.ui.fragment.HttpBaseFragment;


/**
 * Created by HP on 2018/4/24.
 */

public class CartFragment extends HttpBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_cart,null);
        }
        return rootView;
    }

    @Override
    public void complete(String dataType) {

    }
}
