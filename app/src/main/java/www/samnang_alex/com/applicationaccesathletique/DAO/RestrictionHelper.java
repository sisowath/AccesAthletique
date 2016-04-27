package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.Restriction;

public class RestrictionHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MesRestrictions.db";
    private static final String TABLE_NAME = "Restriction";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRATIQUEAVECRESTRICTION = "pratiqueAvecRestriction";
    private static final String COLUMN_PARTIEAVECRESTRCTION = "partieAvecRestriction";
    private static final String COLUMN_AUCUNEPRATIQUE = "aucunePratique";
    private static final String COLUMN_AUCUNEPARTIE = "aucunePartie";
    private static final String COLUMN_AUCUNERESTRICTION = "aucuneRestriction";

    public RestrictionHelper(Context context){ this(context, null, null, 1); }
    public RestrictionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRATIQUEAVECRESTRICTION + " INTEGER, CHECK ( " + COLUMN_PRATIQUEAVECRESTRICTION + " BETWEEN 0 AND 1 )," +
            COLUMN_PARTIEAVECRESTRCTION + " INTEGER, CHECK ( " + COLUMN_PARTIEAVECRESTRCTION + " BETWEEN 0 AND 1 )," +
            COLUMN_AUCUNEPRATIQUE + " INTEGER, CHECK ( " + COLUMN_AUCUNEPRATIQUE + " BETWEEN 0 AND 1 )," +
            COLUMN_AUCUNEPARTIE + " INTEGER, CHECK ( " +  COLUMN_AUCUNEPARTIE + " BETWEEN 0 AND 1 )," +
            COLUMN_AUCUNERESTRICTION + " INTEGER, CHECK ( " + COLUMN_AUCUNERESTRICTION + " BETWEEN 0 AND 1 ));");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTE " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUneRestriction(Restriction restriction) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRATIQUEAVECRESTRICTION, restriction.getPratiqueAvecRestriction());
        contentValues.put(COLUMN_PARTIEAVECRESTRCTION, restriction.getPartieAvecRestriction());
        contentValues.put(COLUMN_AUCUNEPRATIQUE, restriction.getAucunePratique());
        contentValues.put(COLUMN_AUCUNEPARTIE, restriction.getAucunePartie());
        contentValues.put(COLUMN_AUCUNERESTRICTION, restriction.getAucuneRestriction());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean modifierUneRestriction(Restriction restriction) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRATIQUEAVECRESTRICTION, restriction.getPratiqueAvecRestriction());
        contentValues.put(COLUMN_PARTIEAVECRESTRCTION, restriction.getPartieAvecRestriction());
        contentValues.put(COLUMN_AUCUNEPRATIQUE, restriction.getAucunePratique());
        contentValues.put(COLUMN_AUCUNEPARTIE, restriction.getAucunePartie());
        contentValues.put(COLUMN_AUCUNERESTRICTION, restriction.getAucuneRestriction());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues,"WHERE id=?", new String[] {String.valueOf(restriction.getId())});
        db.close();
        return true;
    }
    public boolean supprimerUneRestriction(Restriction restriction) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "WHERE id = ?", new String[] {String.valueOf(restriction.getId())});
        db.close();
        return true;
    }
    public boolean trouverUneRestriction(Restriction restriction) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + restriction.getId(), null);
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
    public Cursor find(Restriction restriction) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
            COLUMN_PRATIQUEAVECRESTRICTION + " = " + restriction.getPratiqueAvecRestriction() + " AND " +
            COLUMN_PARTIEAVECRESTRCTION + " = " + restriction.getPartieAvecRestriction() + " AND " +
            COLUMN_AUCUNEPRATIQUE + " = " + restriction.getAucunePratique() + " AND " +
            COLUMN_AUCUNEPARTIE + " = " + restriction.getAucunePartie() + " AND " +
            COLUMN_AUCUNERESTRICTION + " = " + restriction.getAucuneRestriction(), null);
        db.close();
        return cursor;
    }
    public Cursor findById(Restriction restriction) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + restriction.getId(), null);
        db.close();
        return cursor;
    }
    public int getAvailableId() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getColumnName(cursor.getColumnIndex("MAX(id)")) + 1);
    }
}