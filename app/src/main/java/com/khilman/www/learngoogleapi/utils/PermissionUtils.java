package com.khilman.www.learngoogleapi.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Septian Adi Wijaya on 27/11/2020.
 * please be sure to add credential if you use people's code
 * refers to EasyPermissions.java
 *
 * @see <a href="https://github.com/googlesamples/easypermissions/blob/1.0.0/easypermissions/src/main/java/pub/devrel/easypermissions/EasyPermissions.java">here<a/>
 */
public class PermissionUtils {

    private static final String TAG = PermissionUtils.class.getSimpleName();

    public static boolean checkPermissions(Context context, String[] permissions) {

        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            LogHelper.e(TAG, "API version < M, returning true by default");

            return true;
        }

        //construct data permissions
        //<permission name, PERMISSION_DENIED or PERMISSION_GRANTED>
        HashMap<String, Integer> hPermissions = new HashMap<>();
        for (String perm : permissions) {
            hPermissions.put(perm, PackageManager.PERMISSION_DENIED);
        }


        if (context == null)
            throw new RuntimeException("Can't check permission on null context");

        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(context, perm, hPermissions);
        }

        return true;
    }

    private static synchronized void requestPermissions(Context context, String permission, HashMap<String, Integer> hPermissions) {
//        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
//            Toast.makeText(context, "Membutuhkan Izin " + permission, Toast.LENGTH_SHORT).show();
//        } else {

            // No explanation needed; request the permission
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{permission},
                    1);
//        }
    }


}
