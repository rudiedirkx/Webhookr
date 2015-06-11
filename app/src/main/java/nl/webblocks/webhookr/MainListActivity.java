package nl.webblocks.webhookr;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
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

    }

    public void loadItems() {
        // Webhooks from db
        WebhookDbHelper mDbHelper = new WebhookDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
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



        // Display via adapter
        WebhooksArrayAdapter adapter = new WebhooksArrayAdapter(this, items);
        listView.setAdapter(adapter);



        // Attach click listener
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Webhook item = items.get(position);

                Intent intent = new Intent(v.getContext(), EditWebhookActivity.class);
                intent.putExtra("webhook", item.id);
                startActivity(intent);
//                startActivityForResult(intent, 0);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.loadItems();
    }



    public void openAddScreen() {
        Intent intent = new Intent(getApplicationContext(), EditWebhookActivity.class);
        intent.putExtra("webhook", 0);
        startActivity(intent);
//        startActivityForResult(intent, 0);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                openAddScreen();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
