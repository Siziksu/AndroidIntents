package com.siziksu.intents.data.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.siziksu.intents.commons.Constants;

public class ManifestReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction() != null) {
      if (intent.getAction().equals(Constants.ACTION_MANIFEST)) {
        if (intent.getExtras() != null) {
          String name = intent.getStringExtra(Constants.EXTRAS_NAME);
          String age = intent.getStringExtra(Constants.EXTRAS_AGE);
          String weight = intent.getStringExtra(Constants.EXTRAS_WEIGHT);
          // Toast.makeText(context, "Name: " + name + " Age: " + age + " Weight: " + weight, Toast.LENGTH_SHORT).show();
          Intent i = new Intent();
          i.setClassName(Constants.PACKAGE_NAME, Constants.CLASS_NAME_ACTIVITY_RESULT);
          // i.setComponent(new ComponentName(Constants.PACKAGE_NAME, Constants.CLASS_NAME_ACTIVITY_RESULT));
          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          i.putExtra(Constants.EXTRAS_NAME, name);
          i.putExtra(Constants.EXTRAS_AGE, age);
          i.putExtra(Constants.EXTRAS_WEIGHT, weight);
          context.startActivity(i);
        }
      }
    }
  }
}