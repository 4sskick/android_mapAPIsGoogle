package com.khilman.www.learngoogleapi;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.khilman.www.learngoogleapi.base.BaseView;
import com.khilman.www.learngoogleapi.utils.LogHelper;
import com.khilman.www.learngoogleapi.utils.NavigationUtils;
import com.khilman.www.learngoogleapi.utils.permission.PermissionUtils;

public class MainActivity extends BaseView implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btnPlacePicker;
    private Button btnDirection;
    private Button btnOjek;

    @Override
    protected int parentLayout() {
        return 0;
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents(@Nullable Bundle savedInstanceState) {

        btnPlacePicker = (Button) findViewById(R.id.btn_placePicker);
        btnDirection = (Button) findViewById(R.id.btn_direction);
        btnOjek = (Button) findViewById(R.id.btn_ojek);

        btnPlacePicker.setOnClickListener(this);
        btnDirection.setOnClickListener(this);
        btnOjek.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_placePicker:

                if (PermissionUtils.checkPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                }))
                    NavigationUtils.directToPlacePicker(this);
                break;
            case R.id.btn_direction:

                if (PermissionUtils.checkPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                }))
                    NavigationUtils.directToDirection(this);
                break;
            case R.id.btn_ojek:

                if (PermissionUtils.checkPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                }))
                    NavigationUtils.directToOjek(this);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        LogHelper.e(TAG, requestCode, permissions, grantResults);

        for (int granted : grantResults)
            if (granted != PackageManager.PERMISSION_GRANTED) {


                AlertDialog dialog = new AlertDialog.Builder(this).create();

                if (dialog.isShowing())
                    dialog.dismiss();
                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("INFO")
                            .setMessage("Some permission need to be granted, wanna check for setting permission?")
                            .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                                .setData(Uri.parse("package:" + this.getClass().getPackage().getName()));
                                        startActivityForResult(intent, 1);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                        LogHelper.e(TAG, e);

                                        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                                        startActivityForResult(intent, 1);
                                    }
                                }
                            })
                            .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                    dialog = builder.create();
                    dialog.show();
                }

                break;
            }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
