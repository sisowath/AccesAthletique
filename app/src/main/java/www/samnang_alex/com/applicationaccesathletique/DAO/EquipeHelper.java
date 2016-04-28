package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.Equipe;

public class EquipeHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Equipes.db";
    public static final String TABLE_EQUIPE = "Equipe";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";

    public EquipeHelper(Context context) {
        this(context, null, null, 1);
    }
    public EquipeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_EQUIPE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOM + " TEXT);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPE);
        onCreate(db);
    }
    public boolean ajouterUneEquipe(Equipe equipe) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, equipe.getNom().toLowerCase());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EQUIPE, null, contentValues);
        db.close();
        return true;
    }
    public boolean modifierUneEquipe(Equipe equipe) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, equipe.getNom().toLowerCase());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_EQUIPE, contentValues, "id = ?", new String[]{String.valueOf(equipe.getId())});
        db.close();
        return true;
    }
    public boolean supprimerUneEquipe(Equipe equipe) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EQUIPE, "id = ?", new String[] {String.valueOf(equipe.getId())});
        db.close();
        return true;
    }
    public boolean trouverUneEquipe(Equipe equipe) {
        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE + " WHERE nom = '" + equipe.getNom().toLowerCase() + "'", null);
        if (cursor != null)
            return true;
        else
            return false;
    }
    public Cursor findAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE, null);
        db.close();
        return cursor;
    }
    public Cursor find(Equipe equipe) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE + " WHERE nom = '" + equipe.getNom().toLowerCase() + "'", null);
        db.close();
        return cursor;
    }
    public Cursor findById(Equipe equipe) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE + " WHERE id = " + equipe.getId(), null);
        db.close();
        return cursor;
    }
    public String findNameById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nom FROM " + TABLE_EQUIPE + " WHERE id = " + id, null);
        db.close();
        return cursor.getColumnName(cursor.getColumnIndex("nom"));
    }
}