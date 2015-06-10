package nl.webblocks.webhookr;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class WebhooksCursorAdapter extends CursorAdapter {

    public WebhooksCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_NAME));
        String url = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_NAME));
        Webhook webhook = new Webhook(
                id,
                name,
                url
        );

        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        text1.setText(webhook.name);

        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
        text2.setText(webhook.getUrlDomain());
    }
}
