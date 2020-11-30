package com.khilman.www.learngoogleapi.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.khilman.www.learngoogleapi.utils.LogHelper;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * make it 1 way to check for permission then give the result to user class directly
     * is it permitted or not.
     * <p>
     * when it isn't permitted yet, dialog asking permission shown (always). IF it denied all then returning false
     * when it already permitted (all), returning true to continue to next flow
     * when it only permitted partially, returning false & re-asking for permission which already denied, till it permitted
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissions(Context context, String[] permissions) {

        LogHelper.e(TAG, Arrays.deepToString(permissions));

        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            LogHelper.e(TAG, "API version < M, returning true by default");

            return true;
        }

        //construct data permissions
        List<String> needPermission = new ArrayList<>();

        if (context == null)
            throw new RuntimeException("Can't check permission on null context");

        for (String perm : permissions) {

            //check is permission which asked is permitted or not
            if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED)
                needPermission.add(perm);
        }

        //when needPermission empty, mean all permissions already GRANTED
        if (ObjPermissionUtils.isEmpty(needPermission))
            return true;
        else {
            return requestPermissions(context, needPermission);
        }
    }

    //requesting permissions
    private static synchronized boolean requestPermissions(Context context, List<String> lPermissions) {

        for (String perm : lPermissions) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, perm)) {
                LogHelper.e(TAG, perm);
            }
        }

        ActivityCompat.requestPermissions((Activity) context
                , lPermissions.toArray(new String[lPermissions.size() - 1])
                , 1);

        return false;
    }


}
