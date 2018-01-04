package com.adiaz.testingfirebasecloudmessaging;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by adiaz on 4/1/18.
 */

public class MySharedPrefManager {

    private static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    private static Context mContext;
    private static MySharedPrefManager mInstance;

    public MySharedPrefManager(Context context) {
        mContext = context;
    }

    public static synchronized MySharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySharedPrefManager(context);
        }
        return mInstance;
    }

    public void storeToken (String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }
}
