package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.RaffinementMembre;

public class RaffinementMembreHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MesRaffinementsMembres.db";
    public static final String TABLE_NAME = "RaffinementMembre";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MUSCLES = "muscles";
    public static final String COLUMN_LIGAMENTS = "ligaments";
    public static final String COLUMN_OS = "os";
    public static final String COLUMN_NERFS = "nerfs";
    public static final String COLUMN_AUTRE = "autre";
    public static final String COLUMN_RIEN = "rien";
    public static final String COLUMN_OEILDROIT = "oeilDroit";
    public static final String COLUMN_OEILGAUCHE = "oeilGauche";
    public static final String COLUMN_NEZ = "nez";
    public static final String COLUMN_BOUCHE = "bouche";
    public static final String COLUMN_DENTS = "dents";
    public static final String COLUMN_STERNUM = "sternum";
    public static final String COLUMN_COTES = "cotes";
    public static final String COLUMN_SACRUM = "sacrum";
    public static final String COLUMN_OSILIAQUE = "osIliaque";
    public static final String COLUMN_COCCYX = "coccyx";
    public static final String COLUMN_FOIE = "foie";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_PANCREAS = "pancreas";
    public static final String COLUMN_REIN = "rein";
    public static final String COLUMN_COEUR = "coeur";
    public static final String COLUMN_POUMON = "poumon";
    public static final String COLUMN_GENITAUX = "genitaux";

    public RaffinementMembreHelper(Context context) { this(context, null, null, 1); }
    public RaffinementMembreHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_MUSCLES + " INTEGER," +
            COLUMN_LIGAMENTS + " INTEGER," +
            COLUMN_OS + " INTEGER," +
            COLUMN_NERFS + " INTEGER," +
            COLUMN_AUTRE + " INTEGER," +
            COLUMN_OEILDROIT + " INTEGER," +
            COLUMN_OEILGAUCHE + " INTEGER," +
            COLUMN_NEZ + " INTEGER," +
            COLUMN_BOUCHE + " INTEGER," +
            COLUMN_DENTS + " INTEGER," +
            COLUMN_STERNUM + " INTEGER," +
            COLUMN_COTES + " INTEGER," +
            COLUMN_SACRUM + " INTEGER," +
            COLUMN_OSILIAQUE + " INTEGER," +
            COLUMN_COCCYX + " INTEGER," +
            COLUMN_FOIE + " INTEGER," +
            COLUMN_RATE + " INTEGER," +
            COLUMN_PANCREAS + " INTEGER," +
            COLUMN_REIN + " INTEGER," +
            COLUMN_COEUR + " INTEGER," +
            COLUMN_POUMON + " INTEGER," +
            COLUMN_GENITAUX + " INTEGER);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUnRaffinementMembre(RaffinementMembre raffinementMembre) {
        return true;
    }
    public boolean modifierUnRaffinementMembre(RaffinementMembre raffinementMembre) {
        return true;
    }
    public boolean supprimerUnRaffinementMembre(RaffinementMembre raffinementMembre) {
        return true;
    }
    public boolean trouverUnRaffinementMembre(RaffinementMembre raffinementMembre) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + raffinementMembre.getId(), null);
        if(cursor != null)
            return true;
        else
            return false;
    }
    public Cursor findAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}