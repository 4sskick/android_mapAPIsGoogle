package com.khilman.www.learngoogleapi.base;

import android.content.Context;

/**
 * Created by Septian Adi Wijaya on 30/11/2020.
 * please be sure to add credential if you use people's code
 */
public interface IBasePresenter<viewT> {
    void onViewActive(viewT mView, Context mContext);

    void onViewInActive();
}
