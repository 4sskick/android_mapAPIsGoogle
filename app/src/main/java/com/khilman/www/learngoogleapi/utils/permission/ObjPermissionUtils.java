package com.khilman.www.learngoogleapi.utils.permission;

import java.util.List;
import java.util.Map;

/**
 * Created by Septian Adi Wijaya on 30/11/2020.
 * please be sure to add credential if you use people's code
 */
public class ObjPermissionUtils {

    public static boolean isEmpty(Object s) {
        if (s == null) {
            return true;
        }
        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return true;
        }
        if (s instanceof Map) {
            return ((Map<?, ?>) s).isEmpty();
        }
        if (s instanceof List) {
            return ((List<?>) s).isEmpty();
        }
        if (s instanceof Object[]) {
            return ((Object[]) s).length == 0;
        }
        return false;
    }
}
