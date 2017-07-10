package com.siziksu.intents.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.siziksu.intents.R;
import com.siziksu.intents.commons.Constants;
import com.siziksu.intents.data.receiver.PersonReceiver;
import com.siziksu.intents.ui.object.Index;
import com.siziksu.intents.ui.object.IndexAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonReceiver.NewPersonListener {

    private static final int PICK_CONTACT_REQUEST = 1;
    private static final int REQUEST_CODE = 2;

    private PersonReceiver receiver;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        List<Index> index = new ArrayList<>();
        index.add(new Index("Go Activity First", "new Intent(\"com.siziksu.intents.action.FIRST\")"));
        index.add(new Index("Go Activity Second", "new Intent(this, ActivityTwo.class)"));
        index.add(new Index("Go Activity Second or Third", "new Intent(\"com.siziksu.intents.action.BOTH\")"));
        index.add(new Index("Go to the Web Browser", "new Intent(Intent.ACTION_VIEW, Uri.parse(\"http://www.amazon.com\")"));
        index.add(new Index("Make Calls", "new Intent(Intent.ACTION_DIAL, Uri.parse(\"tel:+651234567\"))"));
        index.add(new Index("Show Map", "new Intent(Intent.ACTION_VIEW, Uri.parse(\"geo:41.3773,2.15819?z=15\"))"));
        index.add(new Index("Choose Contacts", "new Intent(Intent.ACTION_PICK)"));
        index.add(new Index("Launch My Browser", "new Intent(\"com.siziksu.intents.action.BROWSER\")"));
        index.add(new Index("Send Broadcast", "new Intent(com.siziksu.intents.action.PERSON)\nIf your receiver is registered in code, it's tied to the life of the activity/service in which you registered."));
        index.add(new Index("Send Broadcast (Manifest)", "new Intent(com.siziksu.intents.action.MANIFEST)\nIf your receiver is registered in the manifest and your app is not running, a new process will be created to handle the broadcast."));
        index.add(new Index("Ask For A Result", "startActivityForResult(new Intent(this, ActivityAskedForResult.class), REQUEST_CODE)"));
        IndexAdapter adapter = new IndexAdapter(this);
        adapter.init(new IndexAdapter.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                onItemClick(position);
            }
        });
        list.setHasFixedSize(true);
        list.setAdapter(adapter.getAdapter());
        list.setItemAnimator(new DefaultItemAnimator());
        list.setLayoutManager(adapter.getLayoutManager());
        adapter.showItems(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new PersonReceiver(this);
        IntentFilter intent = new IntentFilter(Constants.ACTION_PERSON);
        registerReceiver(receiver, intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onItemClick(int position) {
        Intent intent;
        switch (position) {
            case 0:
                startActivity(new Intent(Constants.ACTION_FIRST));
                break;
            case 1:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case 2:
                startActivity(new Intent(Constants.ACTION_BOTH));
                break;
            case 3:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;
            case 4:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+651234567")));
                break;
            case 5:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.3773,2.15819?z=15")));
                break;
            case 6:
                pickContact();
                break;
            case 7:
                intent = new Intent(Constants.ACTION_BROWSER);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;
            case 8:
                /*
                  If your receiver is registered in code, it's tied to the life of the activity/service in
                  which you registered.
                 */
                intent = new Intent(Constants.ACTION_PERSON);
                setExtrasForTheIntent(intent);
                sendBroadcast(intent);
                break;
            case 9:
                /*
                  If your receiver is registered in the manifest and your app is not running, a new process
                  will be created to handle the broadcast.
                 */
                intent = new Intent(Constants.ACTION_MANIFEST);
                setExtrasForTheIntent(intent);
                sendBroadcast(intent);
                break;
            case 10:
                intent = new Intent(this, AskedForResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    private void setExtrasForTheIntent(Intent intent) {
        intent.putExtra(Constants.EXTRAS_NAME, "John Doe");
        intent.putExtra(Constants.EXTRAS_AGE, "18");
        intent.putExtra(Constants.EXTRAS_WEIGHT, "75 Kg");
    }

    @Override
    public void onPersonFound(String... params) {
        String string = "Name: " + params[0]
                        + " Age: " + params[1]
                        + " Weight: " + params[2];
        Snackbar.make(toolbar, string, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_CONTACT_REQUEST:
                if (resultCode == RESULT_OK) {
                    // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getData().toString()));
                    // startActivity(i);

                    // Get the URI that points to the selected contact
                    Uri contactUri = data.getData();
                    // We only need the NUMBER column, because there will be only one row in the result
                    String[] projection = {Phone.NUMBER};

                    // Perform the query on the contact to get the NUMBER column
                    // We don't need a selection or sort order (there's only one result for the given URI)
                    // CAUTION: The query() method should be called from a separate thread to avoid blocking
                    // your app's UI thread (for simplicity of the sample, this code doesn't do that).
                    // Consider using CursorLoader to perform the query.
                    Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        // Retrieve the phone number from the NUMBER column
                        int column = cursor.getColumnIndex(Phone.NUMBER);
                        String number = cursor.getString(column);
                        cursor.close();
                        Snackbar.make(toolbar, "Phone number " + number, Snackbar.LENGTH_LONG).show();
                    }
                }
                break;
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Snackbar.make(toolbar, data.getStringExtra(Constants.EXTRAS_RESULT), Snackbar.LENGTH_LONG).show();
                }
                if (resultCode == RESULT_CANCELED) {
                    Snackbar.make(toolbar, "Result canceled", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        intent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}
