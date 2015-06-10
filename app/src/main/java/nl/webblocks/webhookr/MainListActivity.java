package nl.webblocks.webhookr;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainListActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        listView = (ListView) findViewById(R.id.mainlist);



        // Webhooks from db
        WebhookDbHelper mDbHelper = new WebhookDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // DEBUG //
//        ContentValues values = new ContentValues();
//        values.put(WebhookDbHelper.COLUMN_NAME, "Name 3");
//        values.put(WebhookDbHelper.COLUMN_URL, "http://url.com/path");
//        values.put(WebhookDbHelper.COLUMN_PARAM_TITLE, "ptitle");
//        values.put(WebhookDbHelper.COLUMN_PARAM_URL, "purl");
//        long insertId;
//        insertId = db.insert(WebhookDbHelper.TABLE_NAME, null, values);
        // DEBUG //

        Cursor cursor = db.query(
                WebhookDbHelper.TABLE_NAME,
                WebhookDbHelper.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                null
        );

        final ArrayList<Webhook> items = Webhook.allFromCursor(cursor);



        // Webhooks
//        final ArrayList<Webhook> items = new ArrayList<Webhook>();
//        items.add(new Webhook(1, "Name 1", "https://example.com/1"));
//        items.add(new Webhook(2, "Name 2", "https://example.com/2"));
//        items.add(new Webhook(3, "Name 3", "https://example.com/3"));
//        items.add(new Webhook(3, "Name 4", "https://example.com/4"));
//        items.add(new Webhook(4, "Name 5", "https://example.com/5"));



        // Display via adapter
//        WebhooksCursorAdapter adapter = new WebhooksCursorAdapter(this, c);
        WebhooksArrayAdapter adapter = new WebhooksArrayAdapter(this, items);
        listView.setAdapter(adapter);



        // Attach click listener
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Webhook item = items.get(position);

                Intent intent = new Intent(v.getContext(), EditWebhookActivity.class);
                intent.putExtra("webhook", item.id);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
