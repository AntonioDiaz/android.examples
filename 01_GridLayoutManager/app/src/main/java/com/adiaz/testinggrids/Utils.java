package com.adiaz.testinggrids;

import java.lang.reflect.Field;

/**
 * Created by adiaz on 31/12/17.
 */

public class Utils {

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
