package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.Blessure;
import www.samnang_alex.com.applicationaccesathletique.models.Evenement;

public class BlessureHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MesBlessures.db";
    public static final String TABLE_NAME = "Blessure";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDATHLETE = "idAthlete";
    public static final String COLUMN_IDEVENEMENT = "idEvenement";
    public static final String COLUMN_JOURRETOURENTRAINEMENT = "jourRetourEntrainement";
    public static final String COLUMN_MOISRETOURENTRAINEMENT = "moisRetourEntrainement";
    public static final String COLUMN_ANNEERETOURENTRAINEMENT = "anneeRetourEntrainement";
    public static final String COLUMN_JOURRETOURJEU = " jourRetourJeu";
    public static final String COLUMN_MOISRETOURJEU = "moisRetourJeu";
    public static final String COLUMN_ANNEERETOURJEU = "anneeRetourJeu";
    public static final String COLUMN_MEMBREAFFECTE = "membreAffecte";
    public static final String COLUMN_PRECISIONMEMBRE = "precisionMembre";
    public static final String COLUMN_IDRAFFINEMENTMEMBRE = "idRaffinementMembre";
    public static final String COLUMN_CONTEXTE = "contexte";
    public static final String COLUMN_IDRESTRICTION = "idRestriction";
    public static final String COLUMN_DESCRIPTIONCONDITION = "descriptionCondition";
    public static final String COLUMN_MECANISMEBLESSURE = "mecanismeBlessure";
    public static final String COLUMN_SOAPS = "soapS";
    public static final String COLUMN_SOAPO = "soapO";
    public static final String COLUMN_SOAPA = "soapA";
    public static final String COLUMN_SOAPP = "soapP";
    public static final String COLUMN_COMMENTAIRE = "commentaire";

    public BlessureHelper(Context context) { this(context, null, null, 1); }
    public BlessureHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_IDATHLETE + " INTEGER, FOREIGN KEY (" + COLUMN_IDATHLETE + ") REFERENCES Athlete(id)," +
            COLUMN_IDEVENEMENT + " INTEGER, FOREIGN KEY (" + COLUMN_IDEVENEMENT + ") REFERENCES Evenement(id)," +
            COLUMN_JOURRETOURENTRAINEMENT + " INTEGER," +
            COLUMN_MOISRETOURENTRAINEMENT + " INTEGER," +
            COLUMN_ANNEERETOURENTRAINEMENT + " INTEGER," +
            COLUMN_JOURRETOURJEU + " INTEGER," +
            COLUMN_MOISRETOURJEU + " INTEGER," +
            COLUMN_ANNEERETOURJEU + " INTEGER," +
            COLUMN_MEMBREAFFECTE + " TEXT," +
            COLUMN_PRECISIONMEMBRE + " TEXT," +
            COLUMN_IDRAFFINEMENTMEMBRE + " INTEGER, FOREIGN KEY (" + COLUMN_IDRAFFINEMENTMEMBRE + ") REFERENCES RaffinementMembre(id)," +
            COLUMN_CONTEXTE + " TEXT," +
            COLUMN_IDRESTRICTION + " INTEGER, FOREIGN KEY (" + COLUMN_IDRESTRICTION + ") REFERENCES Restriction(id)," +
            COLUMN_DESCRIPTIONCONDITION + " TEXT," +
            COLUMN_MECANISMEBLESSURE + " TEXT," +
            COLUMN_SOAPS + " TEXT," +
            COLUMN_SOAPO + " TEXT," +
            COLUMN_SOAPA + " TEXT," +
            COLUMN_SOAPP + " TEXT," +
            COLUMN_COMMENTAIRE + " TEXT);");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUneBlessure(Blessure blessure) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDATHLETE, blessure.getIdAthlete());
        contentValues.put(COLUMN_IDEVENEMENT, blessure.getIdEvenement());
        contentValues.put(COLUMN_JOURRETOURENTRAINEMENT, blessure.getJourtRetourEntrainement());
        contentValues.put(COLUMN_MOISRETOURENTRAINEMENT, blessure.getMoisRetourEntrainement());
        contentValues.put(COLUMN_ANNEERETOURENTRAINEMENT, blessure.getMoisRetourEntrainement());
        contentValues.put(COLUMN_JOURRETOURJEU, blessure.getJourRetourJeu());
        contentValues.put(COLUMN_MOISRETOURJEU, blessure.getMoisRetourJeu());
        contentValues.put(COLUMN_ANNEERETOURJEU, blessure.getAnneeRetourJeu());
        contentValues.put(COLUMN_MEMBREAFFECTE, blessure.getMembreAffecte());
        contentValues.put(COLUMN_PRECISIONMEMBRE, blessure.getPrecisionMembre());
        contentValues.put(COLUMN_IDRAFFINEMENTMEMBRE, blessure.getIdRaffinementMembre());
        contentValues.put(COLUMN_CONTEXTE, blessure.getContexte());
        contentValues.put(COLUMN_IDRESTRICTION, blessure.getIdRestriction());
        contentValues.put(COLUMN_DESCRIPTIONCONDITION, blessure.getDescriptionCondition());
        contentValues.put(COLUMN_MECANISMEBLESSURE, blessure.getMecanismeBlessure());
        contentValues.put(COLUMN_SOAPS, blessure.getSoapS());
        contentValues.put(COLUMN_SOAPO, blessure.getSoapO());
        contentValues.put(COLUMN_SOAPA, blessure.getSoapA());
        contentValues.put(COLUMN_SOAPP, blessure.getSoapP());
        contentValues.put(COLUMN_COMMENTAIRE, blessure.getCommentaire());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean modifierUneBlessure(Blessure blessure) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDATHLETE, blessure.getIdAthlete());
        contentValues.put(COLUMN_IDEVENEMENT, blessure.getIdEvenement());
        contentValues.put(COLUMN_JOURRETOURENTRAINEMENT, blessure.getJourtRetourEntrainement());
        contentValues.put(COLUMN_MOISRETOURENTRAINEMENT, blessure.getMoisRetourEntrainement());
        contentValues.put(COLUMN_ANNEERETOURENTRAINEMENT, blessure.getMoisRetourEntrainement());
        contentValues.put(COLUMN_JOURRETOURJEU, blessure.getJourRetourJeu());
        contentValues.put(COLUMN_MOISRETOURJEU, blessure.getMoisRetourJeu());
        contentValues.put(COLUMN_ANNEERETOURJEU, blessure.getAnneeRetourJeu());
        contentValues.put(COLUMN_MEMBREAFFECTE, blessure.getMembreAffecte());
        contentValues.put(COLUMN_PRECISIONMEMBRE, blessure.getPrecisionMembre());
        contentValues.put(COLUMN_IDRAFFINEMENTMEMBRE, blessure.getIdRaffinementMembre());
        contentValues.put(COLUMN_CONTEXTE, blessure.getContexte());
        contentValues.put(COLUMN_IDRESTRICTION, blessure.getIdRestriction());
        contentValues.put(COLUMN_DESCRIPTIONCONDITION, blessure.getDescriptionCondition());
        contentValues.put(COLUMN_MECANISMEBLESSURE, blessure.getMecanismeBlessure());
        contentValues.put(COLUMN_SOAPS, blessure.getSoapS());
        contentValues.put(COLUMN_SOAPO, blessure.getSoapO());
        contentValues.put(COLUMN_SOAPA, blessure.getSoapA());
        contentValues.put(COLUMN_SOAPP, blessure.getSoapP());
        contentValues.put(COLUMN_COMMENTAIRE, blessure.getCommentaire());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, "WHERE id = ?", new String[] {String.valueOf(blessure.getId())});
        db.close();
        return true;
    }
    public boolean supprimerUneBlessure(Blessure blessure) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "WHERE id = ?", new String[] {String.valueOf(blessure.getId())});
        db.close();
        return true;
    }
    public Cursor findAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
    public Cursor find(Blessure blessure) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_IDATHLETE + " = " + blessure.getIdAthlete() + " AND " +
                COLUMN_IDEVENEMENT + " = " + blessure.getIdEvenement() + " AND " +
                COLUMN_JOURRETOURENTRAINEMENT + " = " + blessure.getJourtRetourEntrainement() + " AND " +
                COLUMN_MOISRETOURENTRAINEMENT + " = " + blessure.getMoisRetourEntrainement() + " AND " +
                COLUMN_ANNEERETOURENTRAINEMENT + " = " + blessure.getAnneeRetourEntrainement() + " AND " +
                COLUMN_JOURRETOURJEU + " = " + blessure.getJourRetourJeu() + " AND " +
                COLUMN_MOISRETOURJEU + " = " + blessure.getMoisRetourJeu() + " AND " +
                COLUMN_ANNEERETOURJEU + " = " + blessure.getAnneeRetourJeu() + " AND " +
                COLUMN_MEMBREAFFECTE + " = '" + blessure.getMembreAffecte() + "' AND " +
                COLUMN_PRECISIONMEMBRE + " = '" + blessure.getPrecisionMembre() + "' AND " +
                COLUMN_IDRAFFINEMENTMEMBRE + " = "+ blessure.getIdRaffinementMembre() + " AND " +
                COLUMN_CONTEXTE + " = '" + blessure.getContexte() + "' AND " +
                COLUMN_IDRESTRICTION + " = " + blessure.getIdRestriction() + " AND " +
                COLUMN_DESCRIPTIONCONDITION + " = '" + blessure.getDescriptionCondition() + "' AND " +
                COLUMN_MECANISMEBLESSURE + " = '" + blessure.getMecanismeBlessure() + "' AND " +
                COLUMN_SOAPS + " = '" + blessure.getSoapS() + "' AND " +
                COLUMN_SOAPO + " = '" + blessure.getSoapO() + "' AND " +
                COLUMN_SOAPA + " = '" + blessure.getSoapA() + "' AND " +
                COLUMN_SOAPP + " = '" + blessure.getSoapP() + "' AND " +
                COLUMN_COMMENTAIRE + " = '" + blessure.getCommentaire() + "'", null);
        db.close();
        return cursor;
    }
    public Cursor findByIdEvenement(Blessure blessure) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE idEvenement = " + blessure.getIdEvenement(), null);
        db.close();
        return cursor;
    }
}