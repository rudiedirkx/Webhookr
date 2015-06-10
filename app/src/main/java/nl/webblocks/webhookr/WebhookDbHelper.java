package nl.webblocks.webhookr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WebhookDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "webhooks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_PARAM_TITLE = "paramTitle";
    public static final String COLUMN_PARAM_URL = "paramUrl";

    public static final String[] ALL_COLUMNS = new String[] {
        WebhookDbHelper.COLUMN_ID,
        WebhookDbHelper.COLUMN_NAME,
        WebhookDbHelper.COLUMN_URL,
        WebhookDbHelper.COLUMN_PARAM_TITLE,
        WebhookDbHelper.COLUMN_PARAM_URL,
    };

    private static final String SQL_CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_URL + " TEXT," +
            COLUMN_PARAM_TITLE + " TEXT," +
            COLUMN_PARAM_URL + " TEXT" +
        ")";

    private static final String SQL_DROP_TABLE =
        "DROP TABLE IF EXISTS " + TABLE_NAME;

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
