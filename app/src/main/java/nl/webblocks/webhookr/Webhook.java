package nl.webblocks.webhookr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.URLUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webhook implements Serializable {

    public static boolean validUrl(String url) {
        return URLUtil.isHttpsUrl(url) || URLUtil.isHttpUrl(url);
    }

    public static Webhook oneFromId(Context context, int id) {
        WebhookDbHelper mDbHelper = new WebhookDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                WebhookDbHelper.TABLE_NAME,
                WebhookDbHelper.ALL_COLUMNS,
                "id = ?",
                new String[] {Integer.toString(id)},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        return oneFromCursor(cursor);
    }

    public static Webhook oneFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_NAME));
        String url = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_URL));
        String paramTitle = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_PARAM_TITLE));
        String paramUrl = cursor.getString(cursor.getColumnIndexOrThrow(WebhookDbHelper.COLUMN_PARAM_URL));
        return new Webhook(id, name, url, paramTitle, paramUrl);
    }

    public static ArrayList<Webhook> allFromCursor(Cursor cursor) {
        ArrayList<Webhook> list = new ArrayList<Webhook>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Webhook webhook = oneFromCursor(cursor);
            list.add(webhook);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public int id;
    public String name;
    public String url;

    public String paramTitle = "title";
    public String paramUrl = "url";

    public Webhook(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Webhook(int id, String name, String url, String paramTitle, String paramUrl) {
        this(id, name, url);
        this.paramTitle = paramTitle;
        this.paramUrl = paramUrl;
    }

    public String getUrlDomain() {
        Pattern p = Pattern.compile("://([^/]+)");
        Matcher m = p.matcher(this.url);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    public String toString() {
        return name;
    }
}
