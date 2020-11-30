package com.khilman.www.learngoogleapi.base;

import android.content.Context;

/**
 * Created by Septian Adi Wijaya on 30/11/2020.
 * please be sure to add credential if you use people's code
 */
public class BasePresenter<viewT> implements IBasePresenter<viewT> {

    private viewT mView;
    private Context mContext;

    @Override
    public void onViewActive(viewT mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void onViewInActive() {
        this.mView = null;
        this.mContext = null;
    }
}
