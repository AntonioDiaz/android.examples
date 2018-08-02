package com.adiaz.expandable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.adiaz.expandable.expand.ExpandActivity;
import com.adiaz.expandable.multicheck.MultiCheckActivity;
import com.adiaz.expandable.multitype.MultiTypeActivity;
import com.adiaz.expandable.multitypeandcheck.MultiTypeCheckGenreActivity;
import com.adiaz.expandable.singlecheck.SingleCheckActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button expand = findViewById(R.id.expand_button);
        expand.setOnClickListener(navigateTo(ExpandActivity.class));

        Button multiSelect = findViewById(R.id.multi_check_button);
        multiSelect.setOnClickListener(navigateTo(MultiCheckActivity.class));

        Button singleSelect = findViewById(R.id.single_check_button);
        singleSelect.setOnClickListener(navigateTo(SingleCheckActivity.class));

        Button mixedSelect = findViewById(R.id.mixedtype_button);
        mixedSelect.setOnClickListener(navigateTo(MultiTypeActivity.class));

        Button mixedTypeAndCheck = findViewById(R.id.mixedtype_check_button);
        mixedTypeAndCheck.setOnClickListener(navigateTo(MultiTypeCheckGenreActivity.class));
    }

    public OnClickListener navigateTo(final Class<?> clazz) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, clazz);
                startActivity(intent);
            }
        };
    }
}