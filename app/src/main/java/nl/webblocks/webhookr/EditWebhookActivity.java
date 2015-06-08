package nl.webblocks.webhookr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EditWebhookActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_webhook);

        Intent intent = getIntent();
        Webhook webhook = (Webhook) intent.getSerializableExtra("webhook");

        // Base
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(webhook.name);
        TextView url = (TextView) findViewById(R.id.url);
        url.setText(webhook.url);

        // Params
        TextView paramUrl = (TextView) findViewById(R.id.paramUrl);
        paramUrl.setText(webhook.paramUrl);
        TextView paramTitle = (TextView) findViewById(R.id.paramTitle);
        paramTitle.setText(webhook.paramTitle);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
