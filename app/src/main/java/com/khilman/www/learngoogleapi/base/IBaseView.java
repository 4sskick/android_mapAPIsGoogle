package com.khilman.www.learngoogleapi.base;

/**
 * Created by monta on 01/12/20
 * please make sure to use credit when using people code
 **/
public interface IBaseView {

    void showLoading();

    void hideLoading();

    boolean isShownLoading();

    void showMessage(String message);
}
