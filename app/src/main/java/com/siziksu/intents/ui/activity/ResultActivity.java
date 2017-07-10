package com.siziksu.intents.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.siziksu.intents.R;
import com.siziksu.intents.commons.Constants;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.txt_result_name)).setText(getIntent().getExtras().getString(Constants.EXTRAS_NAME));
        ((TextView) findViewById(R.id.txt_result_age)).setText(getIntent().getExtras().getString(Constants.EXTRAS_AGE));
        ((TextView) findViewById(R.id.txt_result_weight)).setText(getIntent().getExtras().getString(Constants.EXTRAS_WEIGHT));
    }
}
