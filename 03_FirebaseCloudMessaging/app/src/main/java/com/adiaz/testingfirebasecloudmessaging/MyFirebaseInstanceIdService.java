package com.adiaz.testingfirebasecloudmessaging;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by adiaz on 4/1/18.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();
    public static final String TOKEN_BROADCAST = "TOKEN_BROADCAST";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        storeToken(refreshedToken);
        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
    }

    private void storeToken(String refreshedToken) {
        MySharedPrefManager.getInstance(getApplicationContext()).storeToken(refreshedToken);

    }
}
