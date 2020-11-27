package com.khilman.www.learngoogleapi.utils;

import android.app.Activity;
import android.content.Intent;

import com.khilman.www.learngoogleapi.DirectionActivity;
import com.khilman.www.learngoogleapi.OjekActivity;
import com.khilman.www.learngoogleapi.PlaceAutoCompleteActivity;

/**
 * Created by Septian Adi Wijaya on 27/11/2020.
 * please be sure to add credential if you use people's code
 */
public class NavigationUtils {

    public static void directToPlacePicker(Activity act) {
        act.startActivity(new Intent(act, PlaceAutoCompleteActivity.class));
    }

    public static void directToDirection(Activity act) {
        act.startActivity(new Intent(act, DirectionActivity.class));
    }

    public static void directToOjek(Activity act) {
        act.startActivity(new Intent(act, OjekActivity.class));
    }
}
