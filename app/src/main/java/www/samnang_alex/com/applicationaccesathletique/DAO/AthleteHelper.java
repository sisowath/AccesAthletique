package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.Athlete;

public class AthleteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Athletes.db";
    public static final String TABLE_NAME = "Athlete";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_NUMEROJOUEUR = "numeroJoueur";
    public static final String COLUMN_IDEQUIPE = "idEquipe";
    public static final String COLUMN_IDECOLE = "idEcole";

    public AthleteHelper(Context context) {
        this(context, null, null, 1);
    }
    public AthleteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOM + " TEXT," +
                COLUMN_PRENOM + " TEXT," +
                COLUMN_NUMEROJOUEUR + " TEXT," +
                COLUMN_IDEQUIPE + " TEXT, FOREIGN KEY (" + COLUMN_IDEQUIPE + ") REFERENCES Equipe(id)," +
                COLUMN_IDECOLE + " TEXT, FOREIGN KEY (" + COLUMN_IDECOLE + ") REFERENCES Ecole(id);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUnAthlete(Athlete athlete) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, athlete.getNom().toLowerCase());
        contentValues.put(COLUMN_PRENOM, athlete.getPrenom().toLowerCase());
        contentValues.put(COLUMN_NUMEROJOUEUR, athlete.getNumeroJoueur());
        contentValues.put(COLUMN_IDEQUIPE, athlete.getIdEquipe());
        contentValues.put(COLUMN_IDECOLE, athlete.getIdEcole());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean modifierUnAthlete(Athlete athlete) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, athlete.getNom().toLowerCase());
        contentValues.put(COLUMN_PRENOM, athlete.getPrenom().toLowerCase());
        contentValues.put(COLUMN_NUMEROJOUEUR, athlete.getNumeroJoueur());
        contentValues.put(COLUMN_IDEQUIPE, athlete.getIdEquipe());
        contentValues.put(COLUMN_IDECOLE, athlete.getIdEcole());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {String.valueOf(athlete.getId())});
        db.close();
        return true;
    }
    public boolean supprimerUnAthlete(Athlete athlete) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] {String.valueOf(athlete.getId())});
        db.close();
        return true;
    }
    public boolean trouverUnAthlete(Athlete athlete) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + athlete.getId(), null);
        if(cursor != null)
            return true;
        else
            return false;
    }
    public Cursor findAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        db.close();
        return cursor;
    }
    public Cursor find(Athlete athlete) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE nom = '" + athlete.getNom().toLowerCase() + "' AND prenom '" + athlete.getPrenom().toLowerCase() + "' AND numeroJoueur = " + athlete.getNumeroJoueur(), null);
        return cursor;
    }
}