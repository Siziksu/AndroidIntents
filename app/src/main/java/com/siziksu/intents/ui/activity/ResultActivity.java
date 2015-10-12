package com.siziksu.intents.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.siziksu.intents.R;
import com.siziksu.intents.commons.Constants;

public class ResultActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    ((TextView) findViewById(R.id.txtResultName)).setText(getIntent().getExtras().getString(Constants.EXTRAS_NAME));
    ((TextView) findViewById(R.id.txtResultAge)).setText(getIntent().getExtras().getString(Constants.EXTRAS_AGE));
    ((TextView) findViewById(R.id.txtResultWeight)).setText(getIntent().getExtras().getString(Constants.EXTRAS_WEIGHT));
  }
}
