package nl.webblocks.webhookr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WebhooksArrayAdapter extends ArrayAdapter<Webhook> {

    // View holder pattern
    static class ViewHolder {
        TextView text1;
        TextView text2;
    }

    public WebhooksArrayAdapter(Context context, ArrayList<Webhook> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View holder pattern
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);

            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Webhook webhook = getItem(position);

        holder.text1.setText(webhook.name);
        holder.text2.setText(webhook.getUrlDomain());

        return convertView;
    }
}
