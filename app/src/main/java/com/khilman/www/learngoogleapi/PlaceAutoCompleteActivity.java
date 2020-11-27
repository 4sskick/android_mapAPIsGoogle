package com.khilman.www.learngoogleapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class PlaceAutoCompleteActivity extends AppCompatActivity {


    // Deklarasi variable
    private TextView tvPickUpFrom, tvDestLocation;
    private TextView tvPickUpAddr, tvPickUpLatLng, tvPickUpName;
    private TextView tvDestLocAddr, tvDestLocLatLng, tvDestLocName;

    public static final int PICK_UP = 0;
    public static final int DEST_LOC = 1;
    private static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_auto_complete);

        getSupportActionBar().setTitle("Place Auto Complete");
        // Inisialisasi Widget
        wigetInit();

        // inisialisasi Place
        placeInit();

        // Event OnClick
        tvPickUpFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jalankan Method untuk menampilkan Place Auto Complete
                // Bawa constant PICK_UP
                showPlaceAutoComplete(PICK_UP);
            }
        });
        // Event OnClick
        tvDestLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jalankan Method untuk menampilkan Place Auto Complete
                // Bawa constant DEST_LOC
                showPlaceAutoComplete(DEST_LOC);
            }
        });
    }

    // method untuk inisialisasi Place agar lebih rapih
    private void placeInit(){
        if (!Places.isInitialized()) {
            Places.initialize(this, getString(R.string.google_maps_key));
        }
    }

    // Method untuk Inisilisasi Widget agar lebih rapih
    private void wigetInit() {
        tvPickUpFrom = findViewById(R.id.tvPickUpFrom);
        tvDestLocation = findViewById(R.id.tvDestLocation);
        tvPickUpAddr = findViewById(R.id.tvPickUpAddr);
        tvPickUpLatLng = findViewById(R.id.tvPickUpLatLng);
        tvPickUpName = findViewById(R.id.tvPickUpName);
        tvDestLocAddr = findViewById(R.id.tvDestLocAddr);
        tvDestLocLatLng = findViewById(R.id.tvDestLocLatLng);
        tvDestLocName = findViewById(R.id.tvDestLocName);
    }

    // Method menampilkan input Place Auto Complete
    private void showPlaceAutoComplete(int typeLocation) {
        // isi RESUT_CODE tergantung tipe lokasi yg dipilih.
        // titik jmput atau tujuan
        REQUEST_CODE = typeLocation;

        // Filter hanya tmpat yg ada di Indonesia
//        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().setCountry("ID").build();
//        try {
//            // Intent untuk mengirim Implisit Intent
//            Intent mIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
//                    .setFilter(typeFilter)
//                    .build(this);
//            // jalankan intent impilist
//            startActivityForResult(mIntent, REQUEST_CODE);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace(); // cetak error
//            Log.e("tagReq", "showPlaceAutoComplete: here");
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace(); // cetak error
//            // Display Toast
//            Log.e("tagReq", "showPlaceAutoComplete: Layanan Play Services Tidak Tersedia");
//            Toast.makeText(this, "Layanan Play Services Tidak Tersedia", Toast.LENGTH_SHORT).show();
//        }


        List<Place.Field> fields = Arrays.asList(
                Place.Field.ID
                , Place.Field.NAME
                , Place.Field.ADDRESS
                , Place.Field.LAT_LNG);

        Intent mIntent = new Autocomplete
                .IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .setCountry("ID")
                .setInitialQuery("monas")
                .setLocationBias(RectangularBounds
                        .newInstance(
                                new LatLng(-6.260457557522209, 106.78625711420865)
                                , new LatLng(-6.170694470907933, 106.8267691958792)
                        )
                )
                .build(this);

        startActivityForResult(mIntent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "Sini Gaes", Toast.LENGTH_SHORT).show();
        // Pastikan Resultnya OK
        if (resultCode == RESULT_OK) {
            //Toast.makeText(this, "Sini Gaes2", Toast.LENGTH_SHORT).show();

            // Tampung Data tempat ke variable
//            Place placeData = PlaceAutocomplete.getPlace(this, data);
//
//            if (placeData.isDataValid()) {

            Place placeData = Autocomplete.getPlaceFromIntent(data);

            // Show in Log Cat
            Log.e("autoCompletePlace Data", placeData.toString());

            // Dapatkan Detail
            String placeAddress = placeData.getAddress().toString();
            LatLng placeLatLng = placeData.getLatLng();
            String placeName = placeData.getName().toString();

            // Cek user milih titik jemput atau titik tujuan
            switch (REQUEST_CODE) {
                case PICK_UP:
                    // Set ke widget lokasi asal
                    tvPickUpFrom.setText(placeAddress);
                    tvPickUpAddr.setText("Location Address : " + placeAddress);
                    tvPickUpLatLng.setText("LatLang : " + placeLatLng.toString());
                    tvPickUpName.setText("Place Name : " + placeName);

                    break;
                case DEST_LOC:
                    // Set ke widget lokasi tujuan
                    tvDestLocation.setText(placeAddress);
                    tvDestLocAddr.setText("Destination Address : " + placeAddress);
                    tvDestLocLatLng.setText("LatLang : " + placeLatLng.toString());
                    tvDestLocName.setText("Place Name : " + placeName);

                    break;
            }

//            } else {
//                // Data tempat tidak valid
//                Toast.makeText(this, "Invalid Place !", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}