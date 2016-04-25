package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntrepriseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Entreprises.db";
    private static final String TABLE_NAME = "entreprise";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_NOMRESPONSABLE = "nomResponsable";
    private static final String COLUMN_RUE = "rue";
    private static final String COLUMN_VILLE = "ville";
    private static final String COLUMN_PROVINCE = "province";
    private static final String COLUMN_CODEPOSTAL = "codePostal";
    private static final String COLUMN_NUMEROTELEPHONE = "numeroTelephone";
    private static final String COLUMN_SITEWEB = "siteWeb";
    private static final String COLUMN_COURRIEL = "courriel";

    public EntrepriseHelper(Context context) {
        this(context, null, null, 1);
    }
    public EntrepriseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOM + " TEXT," +
            COLUMN_NOMRESPONSABLE + " TEXT," +
            COLUMN_RUE + " TEXT," +
            COLUMN_VILLE + " TEXT," +
            COLUMN_PROVINCE + " TEXT," +
            COLUMN_CODEPOSTAL + " TEXT," +
            COLUMN_NUMEROTELEPHONE + " TEXT," +
            COLUMN_SITEWEB + " TEXT," +
            COLUMN_COURRIEL + " TEXT);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}