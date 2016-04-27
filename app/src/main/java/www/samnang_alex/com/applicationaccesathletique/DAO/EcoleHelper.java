package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import www.samnang_alex.com.applicationaccesathletique.models.Ecole;

public class EcoleHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Ecoles.db";
    public static final String TABLE_NAME = "Ecole";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";

    public EcoleHelper(Context context) {
        this(context, null, null, DATABASE_VERSION);
    }
    public EcoleHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String requeteCreation = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOM + " TEXT);";
        db.execSQL(requeteCreation);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUneEcole(Ecole ecole) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, ecole.getNom().toLowerCase());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean supprimerUneEcole(Ecole ecole) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NOM, new String[]{ecole.getNom().toLowerCase()});
        db.close();
        return  true;
    }
    public boolean modifierUneEcole(Ecole ecole) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, ecole.getNom().toLowerCase());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, "NOM = ?", new String[]{ecole.getNom()});
        db.close();
        return true;
    }
    public boolean trouverUneEcole(Ecole ecole) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +  TABLE_NAME + " WHERE id = " + ecole.getId(), null);
        if(cursor != null)
            return true;
        else
            return false;
    }
    public Cursor findAll() {
        String requete = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(requete, null);
        db.close();
        return  cursor;
    }
    public Cursor find(Ecole ecole) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE nom = '" + ecole.getNom().toLowerCase() + "'", null);
        db.close();
        return cursor;
    }
    public Cursor findById(Ecole ecole) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + ecole.getId(), null);
        db.close();
        return cursor;
    }
}