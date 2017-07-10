package com.siziksu.intents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.siziksu.intents.R;
import com.siziksu.intents.commons.Constants;

public class AskedForResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_for_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.buttonAccept);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.buttonCancel);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonAccept:
                intent = new Intent();
                intent.putExtra(Constants.EXTRAS_RESULT, "Value returned from the asked intent");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.buttonCancel:
                intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }
}
