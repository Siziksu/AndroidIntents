package com.siziksu.intents.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.siziksu.intents.R;

public class ThirdActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple);
    ((TextView) findViewById(R.id.activityTitle)).setText(getString(R.string.activity_third));
  }
}