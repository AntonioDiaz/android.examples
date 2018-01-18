package com.adiaz.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_user)
    TextView title;

    private static final int RC_SIGN_IN = 123;
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //FirebaseApp.initializeApp(this);

        // Choose authentication providers
        /*

        */
        FirebaseAuth instance = FirebaseAuth.getInstance();
        instance.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(TAG, "onAuthStateChanged: " + firebaseAuth.getCurrentUser().getDisplayName());
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            title.setText("DisplayName: " + user.getDisplayName());
            title.append("\nEMail: " + user.getEmail());
            title.append("\nProviderId: " + user.getProviderId());
        } else {
            title.setText("User outside");
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.logo_dark)
                            .build(),
                    RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                title.setText("User inside");
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
                title.setText("Login error");
            }
        }
    }

}
