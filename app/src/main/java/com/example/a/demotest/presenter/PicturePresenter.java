package com.example.a.demotest.presenter;

import android.graphics.Picture;

import com.example.a.demotest.R;
import com.example.a.demotest.contract.Contract;

public class PicturePresenter implements Contract.Presenter {
    private Contract.View view;
    public PicturePresenter(Contract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

}
