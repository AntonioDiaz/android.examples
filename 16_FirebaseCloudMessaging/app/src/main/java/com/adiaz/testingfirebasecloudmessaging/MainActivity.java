package com.adiaz.testingfirebasecloudmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tv_token)
    TextView tvToken;

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvToken.setText("my token \n");
        updateToken();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateToken();
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
        FirebaseMessaging.getInstance().subscribeToTopic("LEGANES");

    }

    public void updateToken(){
        String token = MySharedPrefManager.getInstance(this).getToken();
        if (!TextUtils.isEmpty(token)) {
            tvToken.append(token);
        }
        Log.d(TAG, "updateToken: " + token);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.e(TAG, "onStop: unregistering" + e.getMessage(), e);
        }
    }
}
