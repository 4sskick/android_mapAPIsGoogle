package com.khilman.www.learngoogleapi;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.khilman.www.learngoogleapi.utils.NavigationUtils;
import com.khilman.www.learngoogleapi.utils.permission.PermissionUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlacePicker;
    private Button btnDirection;
    private Button btnOjek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlacePicker = (Button) findViewById(R.id.btn_placePicker);
        btnDirection = (Button) findViewById(R.id.btn_direction);
        btnOjek = (Button) findViewById(R.id.btn_ojek);

        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
////            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
////                    Manifest.permission.ACCESS_FINE_LOCATION)) {
////                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
////            } else {
////
////                // No explanation needed; request the permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    1);
////            }
//        } else {
//            // Permission has already been granted
//            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
//        }

        btnPlacePicker.setOnClickListener(this);
        btnDirection.setOnClickListener(this);
        btnOjek.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_placePicker:

                if (PermissionUtils.checkPermissions(this, new String[]{
                        /*Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                        , */Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.CAMERA
                }))
                    Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
//                    NavigationUtils.directToPlacePicker(this);
                break;
            case R.id.btn_direction:
                NavigationUtils.directToDirection(this);
                break;
            case R.id.btn_ojek:
                NavigationUtils.directToOjek(this);
                break;
        }
    }
}
