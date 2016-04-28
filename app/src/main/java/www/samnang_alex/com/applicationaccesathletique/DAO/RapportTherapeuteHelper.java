package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.samnang_alex.com.applicationaccesathletique.models.RapportTherapeute;
import www.samnang_alex.com.applicationaccesathletique.models.TableRapportTherapeute;

public class RapportTherapeuteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MesRapportsTherapeutes.db";
    public static final String TABLE_NAME = "RapportTherapeute";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMECOLE = "nomEcole";
    public static final String COLUMN_NOMEQUIPE = "nomEquipe";
    public static final String COLUMN_JOUREVENEMENT = "jourEvenement";
    public static final String COLUMN_MOISEVENEMENT = "moisEvenement";
    public static final String COLUMN_ANNEEEVENEMENT = "anneeEvenement";
    public static final String COLUMN_NOMPATIENT = "nomPatient";
    public static final String COLUMN_PRENOMPATIENT = "prenomPatient";
    public static final String COLUMN_JOURBLESSURE = "jourBlessure";
    public static final String COLUMN_MOISBLESSURE = "moisBlessure";
    public static final String COLUMN_ANNEEBLESSURE = "anneeBlessure";
    public static final String COLUMN_JOURRETOURENTRAINEMENT = "jourRetourEntrainement";
    public static final String COLUMN_MOISRETOURENTRAINEMENT = "moisRetourEntrainement";
    public static final String COLUMN_ANNEERETOURENTRAINEMENT = "anneeRetourEntrainement";
    public static final String COLUMN_JOURRETOURJEU = "jourRetourJeu";
    public static final String COLUMN_MOISRETOURJEU = "moisRetourJeu";
    public static final String COLUMN_ANNEERETOURJEU = "anneeRetourJeu";
    public static final String COLUMN_MEMBREAFFECTE = "membreAffecte";
    public static final String COLUMN_PRECISIONMEMBRE = "precisionMembre";
    public static final String COLUMN_RAFFINEMENTMEMBRE = "raffinementMembre";
    public static final String COLUMN_SOAPA = "soapA";
    public static final String COLUMN_COMMENTAIRE = "commentaire";

    public RapportTherapeuteHelper(Context context) {
        this(context, null, null, 1);
    }
    public RapportTherapeuteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NOMECOLE + " TEXT NOT NULL," +
            COLUMN_NOMEQUIPE + " TEXT NOT NULL," +
            COLUMN_JOUREVENEMENT + " INTEGER CHECK ( " + COLUMN_JOUREVENEMENT + " BETWEEN 1 AND 31 )," +
            COLUMN_MOISEVENEMENT + " INTEGER CHECK ( " + COLUMN_MOISEVENEMENT + " BETWEEN 1 AND 12 )," +
            COLUMN_ANNEEEVENEMENT + " INTEGER CHECK ( " + COLUMN_ANNEEEVENEMENT + " > 2000 )," +
            COLUMN_NOMPATIENT + " TEXT," +
            COLUMN_PRENOMPATIENT + " TEXT," +
            COLUMN_JOURBLESSURE + " INTEGER CHECK ( " + COLUMN_JOURBLESSURE + " BETWEEN 1 AND 31 )," +
            COLUMN_MOISBLESSURE + " INTEGER CHECK ( " + COLUMN_MOISBLESSURE + " BETWEEN 1 AND 12 )," +
            COLUMN_ANNEEBLESSURE + " INTEGER CHECK ( " + COLUMN_ANNEEBLESSURE + " > 2000 )," +
            COLUMN_JOURRETOURENTRAINEMENT + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_JOURRETOURENTRAINEMENT + " BETWEEN 1 AND 31 )," +
            COLUMN_MOISRETOURENTRAINEMENT + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_MOISRETOURENTRAINEMENT + " BETWEEN 1 AND 12 )," +
            COLUMN_ANNEERETOURENTRAINEMENT + " INTEGER CHECK ( " + COLUMN_ANNEERETOURENTRAINEMENT + " > 2000 )," +
            COLUMN_JOURRETOURJEU + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_JOURRETOURJEU + " BETWEEN 1 AND 31 )," +
            COLUMN_MOISRETOURJEU + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_MOISRETOURJEU + " BETWEEN 1 AND 12 )," +
            COLUMN_ANNEERETOURJEU + " INTEGER CHECK ( " + COLUMN_ANNEERETOURJEU+ " > 2000 )," +
            COLUMN_MEMBREAFFECTE + " TEXT," +
            COLUMN_PRECISIONMEMBRE + " TEXT," +
            COLUMN_RAFFINEMENTMEMBRE + " TEXT," +
            COLUMN_SOAPA + " TEXT," +
            COLUMN_COMMENTAIRE + " TEXT)");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean ajouterUnRapportTherapeute(TableRapportTherapeute tableRapportTherapeute) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOMECOLE, tableRapportTherapeute.getNomEcole());
        contentValues.put(COLUMN_NOMEQUIPE, tableRapportTherapeute.getNomEquipe());
        contentValues.put(COLUMN_JOUREVENEMENT, tableRapportTherapeute.getJourEvenement());
        contentValues.put(COLUMN_MOISEVENEMENT, tableRapportTherapeute.getMoisEvenement());
        contentValues.put(COLUMN_ANNEEEVENEMENT, tableRapportTherapeute.getAnneeEvenement());
        contentValues.put(COLUMN_NOMPATIENT, tableRapportTherapeute.getNomPatient());
        contentValues.put(COLUMN_PRENOMPATIENT, tableRapportTherapeute.getPrenomPatient());
        contentValues.put(COLUMN_JOURBLESSURE, tableRapportTherapeute.getJourBlessure());
        contentValues.put(COLUMN_MOISBLESSURE, tableRapportTherapeute.getMoisBlessure());
        contentValues.put(COLUMN_ANNEEBLESSURE, tableRapportTherapeute.getAnneeBlessure());
        contentValues.put(COLUMN_JOURRETOURENTRAINEMENT, tableRapportTherapeute.getJourRetourEntrainement());
        contentValues.put(COLUMN_MOISRETOURENTRAINEMENT, tableRapportTherapeute.getMoisRetourEntrainement());
        contentValues.put(COLUMN_ANNEERETOURENTRAINEMENT, tableRapportTherapeute.getAnneeRetourEntrainement());
        contentValues.put(COLUMN_JOURRETOURJEU, tableRapportTherapeute.getJourRetourJeu());
        contentValues.put(COLUMN_MOISRETOURJEU, tableRapportTherapeute.getMoisRetourJeu());
        contentValues.put(COLUMN_ANNEERETOURJEU, tableRapportTherapeute.getAnneeRetourJeu());
        contentValues.put(COLUMN_MEMBREAFFECTE, tableRapportTherapeute.getMembreAffecte());
        contentValues.put(COLUMN_PRECISIONMEMBRE, tableRapportTherapeute.getPrecisionMembre());
        contentValues.put(COLUMN_RAFFINEMENTMEMBRE, tableRapportTherapeute.getRaffinementMembre());
        contentValues.put(COLUMN_SOAPA, tableRapportTherapeute.getSoapA());
        contentValues.put(COLUMN_COMMENTAIRE, tableRapportTherapeute.getCommentaire());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public Cursor findAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        db.close();
        return cursor;
    }
    public Cursor findByDate(int jour, int mois, int annee) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_JOUREVENEMENT + " = " + jour + " AND " + COLUMN_MOISEVENEMENT + " = " + mois + " AND " + COLUMN_ANNEEEVENEMENT + " = " + annee, null);
        db.close();
        return cursor;
    }
}