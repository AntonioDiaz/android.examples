package com.adiaz.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
                if (firebaseAuth.getCurrentUser()!=null) {
                    firebaseAuth.getCurrentUser().reload();
                    Log.d(TAG, "onAuthStateChanged: " + firebaseAuth.getCurrentUser().getDisplayName());
                    updateUserDescription(firebaseAuth.getCurrentUser());
                }
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            updateUserDescription(user);
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

    private void updateUserDescription(FirebaseUser user) {
        title.setText("DisplayName: " + user.getDisplayName());
        title.append("\n\nEMail: " + user.getEmail());
        title.append("\n\nisVerified: " + user.isEmailVerified());
        title.append("\n\nProviderId: " + user.getProviderId());
        title.append("\n\nPhotoURL: " + user.getPhotoUrl());
        title.append("\n\nPhone: " + user.getPhoneNumber());
        title.append("\n\nUid: " + user.getUid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //menu.findItem(R.id.action_logout).setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                doLogout();
                break;
            default:
                Log.d(TAG, "onOptionsItemSelected: erroraco");
        }
        return super.onOptionsItemSelected(item);
    }

    private void doLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (!user.isEmailVerified()) {
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                    }
                                }
                            });
                }
                title.setText("User inside");
                startActivity(new Intent(this, MainActivity.class));
                finish();
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
                title.setText("Login error");
            }
        }
    }

}
