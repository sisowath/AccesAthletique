package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.Evenement;

public class EvenementHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MesEvenements.db";
    public static final String TABLE_NAME = "Evenement";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_JOUR = "jour";
    public static final String COLUMN_MOIS = "mois";
    public static final String COLUMN_ANNEE = "annee";

    public EvenementHelper(Context context) { this(context, null, null, 1); }
    public EvenementHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_TYPE + " TEXT, CHECK (" + COLUMN_TYPE + " IN (\"Pratique\",\"Match\",\"Tournoi\"))," +
        COLUMN_JOUR + " INTEGER," +
        COLUMN_MOIS + " INTEGER," +
        COLUMN_ANNEE + " INTEGER);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUnEvenement(Evenement evenement) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, evenement.getType());
        contentValues.put(COLUMN_JOUR, evenement.getJour());
        contentValues.put(COLUMN_MOIS, evenement.getMois());
        contentValues.put(COLUMN_ANNEE, evenement.getAnnee());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean modifierUnEvenement(Evenement evenement) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, evenement.getType());
        contentValues.put(COLUMN_JOUR, evenement.getJour());
        contentValues.put(COLUMN_MOIS, evenement.getMois());
        contentValues.put(COLUMN_ANNEE, evenement.getAnnee());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, "WHERE id = ?", new String[] {String.valueOf(evenement.getId())});
        db.close();
        return true;
    }
    public boolean supprimerUnEvenement(Evenement evenement) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "WHERE id = ?", new String[] {String.valueOf(evenement.getId())});
        db.close();
        return true;
    }
    public boolean trouverUnEvenement(Evenement evenement) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + evenement.getId(), null);
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
    public Cursor find(Evenement evenement) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE type = '" + evenement.getType() + "' AND jour = " + evenement.getJour() + " AND mois = " + evenement.getMois() + " AND annee = " + evenement.getAnnee(), null);
        return cursor;
    }
}