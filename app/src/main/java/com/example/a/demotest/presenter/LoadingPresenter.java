package com.example.a.demotest.presenter;

import android.graphics.Picture;

import com.example.a.demotest.R;
import com.example.a.demotest.contract.Contract;

public class LoadingPresenter implements Contract.Presenter {
    private Contract.View view;
    public LoadingPresenter(Contract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

}
