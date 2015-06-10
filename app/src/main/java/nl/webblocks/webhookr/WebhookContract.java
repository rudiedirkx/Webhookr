package nl.webblocks.webhookr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class WebhookContract {
//    WebhookDbHelper mDbHelper = new WebhookDbHelper(getContext());

    public static abstract class Webhook implements BaseColumns {
        public static final String TABLE_NAME = "webhooks";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_PARAM_TITLE = "paramTitle";
        public static final String COLUMN_PARAM_URL = "paramUrl";
    }

    private static final String SQL_CREATE_TABLE =
        "CREATE TABLE " + Webhook.TABLE_NAME + " (" +
            Webhook._ID + " INTEGER PRIMARY KEY," +
            Webhook.COLUMN_NAME + " TEXT," +
            Webhook.COLUMN_URL + " TEXT," +
            Webhook.COLUMN_PARAM_TITLE + " TEXT," +
            Webhook.COLUMN_PARAM_URL + " TEXT" +
        ")";

    private static final String SQL_DROP_TABLE =
        "DROP TABLE IF EXISTS " + Webhook.TABLE_NAME;

    public class WebhookDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Webhookr.db";

        public WebhookDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DROP_TABLE);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
