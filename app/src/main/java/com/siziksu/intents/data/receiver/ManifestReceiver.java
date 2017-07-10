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
                if (intent.getExtras() != null
                    && intent.getExtras().containsKey(Constants.EXTRAS_NAME)
                    && intent.getExtras().containsKey(Constants.EXTRAS_AGE)
                    && intent.getExtras().containsKey(Constants.EXTRAS_WEIGHT)) {
                    String name = intent.getStringExtra(Constants.EXTRAS_NAME);
                    String age = intent.getStringExtra(Constants.EXTRAS_AGE);
                    String weight = intent.getStringExtra(Constants.EXTRAS_WEIGHT);
                    // Toast.makeText(context, "Name: " + name + " Age: " + age + " Weight: " + weight, Toast.LENGTH_SHORT).show();
                    Intent newIntent = new Intent();
                    newIntent.setClassName(Constants.PACKAGE_NAME, Constants.CLASS_NAME_ACTIVITY_RESULT);
                    // newIntent.setComponent(new ComponentName(Constants.PACKAGE_NAME, Constants.CLASS_NAME_ACTIVITY_RESULT));
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    newIntent.putExtra(Constants.EXTRAS_NAME, name);
                    newIntent.putExtra(Constants.EXTRAS_AGE, age);
                    newIntent.putExtra(Constants.EXTRAS_WEIGHT, weight);
                    context.startActivity(newIntent);
                }
            }
        }
    }
}