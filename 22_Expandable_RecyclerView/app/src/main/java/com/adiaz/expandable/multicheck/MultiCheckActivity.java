package com.adiaz.expandable.multicheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.adiaz.expandable.R;

import static com.adiaz.expandable.GenreDataFactory.makeMultiCheckGenres;

public class MultiCheckActivity extends AppCompatActivity {

  private MultiCheckGenreAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi_check);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    adapter = new MultiCheckGenreAdapter(makeMultiCheckGenres());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    Button clear = (Button) findViewById(R.id.clear_button);
    clear.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.clearChoices();
      }
    });

    Button check = (Button) findViewById(R.id.check_first_child);
    check.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.checkChild(true, 0, 3);
      }
    });
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    adapter.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    adapter.onRestoreInstanceState(savedInstanceState);
  }
}
