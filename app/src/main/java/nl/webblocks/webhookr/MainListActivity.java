package nl.webblocks.webhookr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
//        WebhookContract.WebhookDbHelper mDbHelper = new WebhookContract.WebhookDbHelper(getApplicationContext());



        // Webhooks
        final ArrayList<Webhook> items = new ArrayList<Webhook>();
        items.add(new Webhook(1, "Name 1", "https://example.com/1"));
        items.add(new Webhook(2, "Name 2", "https://example.com/2"));
        items.add(new Webhook(3, "Name 3", "https://example.com/3"));
        items.add(new Webhook(3, "Name 4", "https://example.com/4"));
        items.add(new Webhook(4, "Name 5", "https://example.com/5"));

        // Display via adapter
        WebhooksAdapter adapter = new WebhooksAdapter(this, items);
        listView.setAdapter(adapter);



        // Attach click listener
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Webhook item = items.get(position);

                Intent intent = new Intent(v.getContext(), EditWebhookActivity.class);
//                intent.putExtra("tmp", item.name);
                intent.putExtra("webhook", (Serializable) item);
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
