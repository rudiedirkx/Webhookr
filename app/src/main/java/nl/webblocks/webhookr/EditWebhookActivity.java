package nl.webblocks.webhookr;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;

public class EditWebhookActivity extends ActionBarActivity {

    public TextView elId;
    public TextView elName;
    public TextView elUrl;
    public TextView elParamUrl;
    public TextView elParamTitle;

    public WebhookDbHelper mDbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_webhook);

        Intent intent = getIntent();
        int id = intent.getIntExtra("webhook", 0);

        elId = (TextView) findViewById(R.id.id);
        elName = (TextView) findViewById(R.id.name);
        elUrl = (TextView) findViewById(R.id.url);
        elParamUrl = (TextView) findViewById(R.id.paramUrl);
        elParamTitle = (TextView) findViewById(R.id.paramTitle);

        elId.setText(Integer.toString(id));

        if (id != 0) {
            Webhook webhook = Webhook.oneFromId(getApplicationContext(), id);

            // Base
            elName.setText(webhook.name);
            elUrl.setText(webhook.url);

            // Params
            elParamUrl.setText(webhook.paramUrl);
            elParamTitle.setText(webhook.paramTitle);

            // Button
            Button save = (Button) findViewById(R.id.save);
            save.setText(getString(R.string.update));

            Button delete = (Button) findViewById(R.id.delete);
            delete.setVisibility(View.VISIBLE);
        }
    }



    public void deleteWebhook(View view) {
        int id = Integer.parseInt(elId.getText().toString());
        if (id != 0) {
            mDbHelper = new WebhookDbHelper(getApplicationContext());
            db = mDbHelper.getWritableDatabase();

            db.delete(WebhookDbHelper.TABLE_NAME, "id = ?", new String[] {Integer.toString(id)});
        }

        finish();
    }



    public void saveWebhook(View view) {
        int id = Integer.parseInt(elId.getText().toString());

        String name = elName.getText().toString();
        String url = elUrl.getText().toString();

        if (name.trim().equals("")) {
            elName.setError("Can not be empty.");
            return;
        }

        if (url.trim().equals("")) {
            elUrl.setError("Can not be empty.");
            return;
        }

        if (!Webhook.validUrl(url)) {
            elUrl.setError("Invalid URL.");
            return;
        }

        ContentValues values = new ContentValues();
        values.put(WebhookDbHelper.COLUMN_NAME, name.trim());
        values.put(WebhookDbHelper.COLUMN_URL, url.trim());
        values.put(WebhookDbHelper.COLUMN_PARAM_TITLE, elParamTitle.getText().toString());
        values.put(WebhookDbHelper.COLUMN_PARAM_URL, elParamUrl.getText().toString());

        mDbHelper = new WebhookDbHelper(getApplicationContext());
        db = mDbHelper.getWritableDatabase();

        if (id == 0) {
            createWebhook(values);
        }
        else {
            updateWebhook(id, values);
        }

        // Go back to MainListActivity
        finish();
//        finishActivity(0);
    }

    public void updateWebhook(int id, ContentValues values) {
        int count;
        count = db.update(WebhookDbHelper.TABLE_NAME, values, "id = ?", new String[] {Integer.toString(id)});
    }

    public void createWebhook(ContentValues values) {
        long insertId;
        insertId = db.insert(WebhookDbHelper.TABLE_NAME, null, values);

        Log.v("id", Long.toString(insertId));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_webhook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
