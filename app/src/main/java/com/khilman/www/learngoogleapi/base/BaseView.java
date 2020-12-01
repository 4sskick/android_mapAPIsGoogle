package com.khilman.www.learngoogleapi.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.khilman.www.learngoogleapi.R;

/**
 * Created by monta on 01/12/20
 * please make sure to use credit when using people code
 **/
public abstract class BaseView extends AppCompatActivity implements IBaseView {

    private static final String TAG = BaseView.class.getSimpleName();
    public static final int EMPTY_LAYOUT = 0;


    private RelativeLayout layoutContent;


    private Activity mActivity;
    private Toast mToast;

    protected abstract int parentLayout();

    protected abstract int contentLayout();

    protected abstract void initComponents(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (parentLayout() != EMPTY_LAYOUT)
            setContentView(parentLayout());
        else
            setContentView(R.layout.base_activity);

        if (contentLayout() != EMPTY_LAYOUT)
            try {
                layoutContent = findViewById(R.id.layout_content);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                inflater.inflate(contentLayout(), layoutContent);
            } catch (Exception e) {
                throw new IllegalStateException("Inflating contentLayout() failed on " + this.getClass().getSimpleName());
            }
        else
            throw new IllegalStateException("contentLayout() can't be EMPTY " + this.getClass().getSimpleName());

        mActivity = this;
        initComponents(savedInstanceState);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isShownLoading() {
        return false;
    }

    @Override
    public void showMessage(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }

        if (message != null && !message.isEmpty()) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }
}
