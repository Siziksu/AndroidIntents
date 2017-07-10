package com.siziksu.intents.data.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.siziksu.intents.commons.Constants;

public class PersonReceiver extends BroadcastReceiver {

    private NewPersonListener listener;

    public PersonReceiver(NewPersonListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            if (intent.getAction().equals(Constants.ACTION_PERSON)) {
                if (intent.getExtras() != null
                    && intent.getExtras().containsKey(Constants.EXTRAS_NAME)
                    && intent.getExtras().containsKey(Constants.EXTRAS_AGE)
                    && intent.getExtras().containsKey(Constants.EXTRAS_WEIGHT)) {
                    String name = intent.getStringExtra(Constants.EXTRAS_NAME);
                    String age = intent.getStringExtra(Constants.EXTRAS_AGE);
                    String weight = intent.getStringExtra(Constants.EXTRAS_WEIGHT);
                    listener.onPersonFound(name, age, weight);
                }
            }
        }
    }

    public interface NewPersonListener {

        void onPersonFound(String... params);
    }
}