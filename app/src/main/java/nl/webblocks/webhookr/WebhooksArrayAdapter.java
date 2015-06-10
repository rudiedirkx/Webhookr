package nl.webblocks.webhookr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WebhooksArrayAdapter extends ArrayAdapter<Webhook> {

    public WebhooksArrayAdapter(Context context, ArrayList<Webhook> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Webhook webhook = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);

        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        text1.setText(webhook.name);

        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
        text2.setText(webhook.getUrlDomain());

        return view;
    }
}
