package www.samnang_alex.com.applicationaccesathletique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import www.samnang_alex.com.applicationaccesathletique.models.TableRapportAthlete;

public class RapportAthleteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mesRapportsAthletes.db";
    public static final String TABLE_NAME = "RapportAthlete";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMATHLETE = "nomAthlete";
    public static final String COLUMN_PRENOMATHLETE = "prenomAthlete";
    public static final String COLUMN_NOMEQUIPE = "nomEquipe";
    public static final String COLUMN_NOMECOLE = "nomEcole";
    public static final String COLUMN_NUMEROJOUEUR = "numeroJoueur";
    public static final String COLUMN_JOURBLESSURE = "jourBlessure";
    public static final String COLUMN_MOISBLESSURE = "moisBlessure";
    public static final String COLUMN_ANNEEBLESSURE = "anneeBlessure";
    public static final String COLUMN_TYPEEVENEMENT = "typeEvenement";
    public static final String COLUMN_JOURRETOURENTRAINEMENT = "jourRetourEntrainement";
    public static final String COLUMN_MOISRETOURENTRAINEMENT = "moisRetourEntrainement";
    public static final String COLUMN_ANNEERETOURENTRAINEMENT = "anneeRetourEntrainement";
    public static final String COLUMN_JOURRETOURJEU = "jourRetourJeu";
    public static final String COLUMN_MOISRETOURJEU = "moisRetourJeu";
    public static final String COLUMN_ANNEERETOURJEU = "anneeRetourJeu";
    public static final String COLUMN_MEMBREAFFECTE = "membreAffecte";
    public static final String COLUMN_PRECISIONMEMBRE = "precisionMembre";
    public static final String COLUMN_RAFFINEMENTMEMBRE = "raffinementMembre";
    public static final String COLUMN_CONTEXTE = "contexte";
    public static final String COLUMN_RESTRICTION = "restriction";
    public static final String COLUMN_DESCRIPTIONCONDITION = "descriptionCondition";
    public static final String COLUMN_MECANISMEBLESSURE = "mecanismeBlessure";
    public static final String COLUMN_SOAPS = "soapS";
    public static final String COLUMN_SOAPO = "soapO";
    public static final String COLUMN_SOAPA = "soapA";
    public static final String COLUMN_SOAPP = "soapP";
    public static final String COLUMN_COMMENTAIRE = "commentaire";
    public String DB_FILEPATH;

    public RapportAthleteHelper(Context context) {
        this(context, null, null, 1);
        final String packageName = context.getPackageName();
        DB_FILEPATH = "/data/data/" + packageName + "/databases/mesRapportsAthletes.db";
    }
    public RapportAthleteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOMATHLETE + " TEXT NOT NULL," +
                COLUMN_PRENOMATHLETE + " TEXT NOT NULL," +
                COLUMN_NOMEQUIPE + " TEXT NOT NULL," +
                COLUMN_NOMECOLE + " TEXT NOT NULL," +
                COLUMN_NUMEROJOUEUR + " INTEGER NOT NULL," +
                COLUMN_JOURBLESSURE + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_JOURBLESSURE + " BETWEEN 1 AND 31 )," +
                COLUMN_MOISBLESSURE + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_MOISBLESSURE + " BETWEEN 1 AND 12 )," +
                COLUMN_ANNEEBLESSURE + " INTEGER CHECK ( " + COLUMN_ANNEEBLESSURE + " > 2000 )," +
                COLUMN_TYPEEVENEMENT + " TEXT CHECK ( " + COLUMN_TYPEEVENEMENT + " IN ('Pratique', 'Match', 'Tournoi') )," +
                COLUMN_JOURRETOURENTRAINEMENT + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_JOURRETOURENTRAINEMENT + " BETWEEN 1 AND 31 )," +
                COLUMN_MOISRETOURENTRAINEMENT + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_MOISRETOURENTRAINEMENT + " BETWEEN 1 AND 12 )," +
                COLUMN_ANNEERETOURENTRAINEMENT + " INTEGER CHECK ( " + COLUMN_ANNEERETOURENTRAINEMENT + " > 2000 )," +
                COLUMN_JOURRETOURJEU + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_JOURRETOURJEU + " BETWEEN 1 AND 31 )," +
                COLUMN_MOISRETOURJEU + " INTEGER DEFAULT 1 CHECK ( " + COLUMN_MOISRETOURJEU + " BETWEEN 1 AND 12 )," +
                COLUMN_ANNEERETOURJEU + " INTEGER CHECK ( " + COLUMN_ANNEERETOURJEU + " > 2000 )," +
                COLUMN_MEMBREAFFECTE + " TEXT," +
                COLUMN_PRECISIONMEMBRE + " TEXT," +
                COLUMN_RAFFINEMENTMEMBRE + " TEXT," +
                COLUMN_CONTEXTE + " TEXT," +
                COLUMN_RESTRICTION + " TEXT," +
                COLUMN_DESCRIPTIONCONDITION + " TEXT," +
                COLUMN_MECANISMEBLESSURE + " TEXT," +
                COLUMN_SOAPS + " TEXT," +
                COLUMN_SOAPO + " TEXT," +
                COLUMN_SOAPA + " TEXT," +
                COLUMN_SOAPP + " TEXT," +
                COLUMN_COMMENTAIRE + " TEXT)");
        //db.close();
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 1, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ajouterUnRapportAthlete(TableRapportAthlete tableRapportAthlete) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOMATHLETE, tableRapportAthlete.getNomAthlete());
        contentValues.put(COLUMN_PRENOMATHLETE, tableRapportAthlete.getPrenomAthlete());
        contentValues.put(COLUMN_NOMEQUIPE, tableRapportAthlete.getNomEquipe());
        contentValues.put(COLUMN_NOMECOLE, tableRapportAthlete.getNomEcole());
        contentValues.put(COLUMN_NUMEROJOUEUR, tableRapportAthlete.getNumeroJoueur());
        contentValues.put(COLUMN_JOURBLESSURE, tableRapportAthlete.getJourBlessure());
        contentValues.put(COLUMN_MOISBLESSURE, tableRapportAthlete.getMoisBlessure());
        contentValues.put(COLUMN_ANNEEBLESSURE, tableRapportAthlete.getAnneeBlessure());
        contentValues.put(COLUMN_TYPEEVENEMENT, tableRapportAthlete.getTypeEvenement());
        contentValues.put(COLUMN_JOURRETOURENTRAINEMENT, tableRapportAthlete.getJourRetourEntrainement());
        contentValues.put(COLUMN_MOISRETOURENTRAINEMENT, tableRapportAthlete.getMoisRetourEntrainement());
        contentValues.put(COLUMN_ANNEERETOURENTRAINEMENT, tableRapportAthlete.getAnneeRetourEntrainement());
        contentValues.put(COLUMN_JOURRETOURJEU, tableRapportAthlete.getJourRetourJeu());
        contentValues.put(COLUMN_MOISRETOURJEU, tableRapportAthlete.getMoisRetourJeu());
        contentValues.put(COLUMN_ANNEERETOURJEU, tableRapportAthlete.getAnneeRetourJeu());
        contentValues.put(COLUMN_MEMBREAFFECTE, tableRapportAthlete.getMembreAffecte());
        contentValues.put(COLUMN_PRECISIONMEMBRE, tableRapportAthlete.getPrecisionMembre());
        contentValues.put(COLUMN_RAFFINEMENTMEMBRE, tableRapportAthlete.getRaffinementMembre());
        contentValues.put(COLUMN_CONTEXTE, tableRapportAthlete.getContexte());
        contentValues.put(COLUMN_RESTRICTION, tableRapportAthlete.getRestriction());
        contentValues.put(COLUMN_DESCRIPTIONCONDITION, tableRapportAthlete.getDescriptionCondition());
        contentValues.put(COLUMN_MECANISMEBLESSURE, tableRapportAthlete.getMecanismeBlessure());
        contentValues.put(COLUMN_SOAPS, tableRapportAthlete.getSoapS());
        contentValues.put(COLUMN_SOAPO, tableRapportAthlete.getSoapO());
        contentValues.put(COLUMN_SOAPA, tableRapportAthlete.getSoapA());
        contentValues.put(COLUMN_SOAPP, tableRapportAthlete.getSoapP());
        contentValues.put(COLUMN_COMMENTAIRE, tableRapportAthlete.getCommentaire());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    // IMPORT
    public boolean importDatabase() throws IOException {

        // Close the SQLiteOpenHelper so it will
        // commit the created empty database to internal storage.
        close();
        File newDb = new File(Environment.getExternalStorageDirectory() + "/AccesAthletique/Import/mesRapportsAthletes_bak.db");
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper
            // will cache it and mark it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }
    public boolean restoreDatabase() throws IOException {

        // Close the SQLiteOpenHelper so it will
        // commit the created empty database to internal storage.
        close();
        File newDb = new File(Environment.getExternalStorageDirectory() + "/AccesAthletique/Backup/mesRapportsAthletes_bak.db");
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper
            // will cache it and mark it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }
    private void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }
    public void backupDatabase() throws IOException {

        if (isSDCardWriteable()) {
            File direct = new File(Environment.getExternalStorageDirectory() + "/AccesAthletique/Backup");
            if(!direct.exists())
            {
                if(direct.mkdir())
                {
                    //directory is created;
                }

            }
            // Open your local db as the input stream
            String inFileName = DB_FILEPATH;
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            String outFileName = Environment.getExternalStorageDirectory() + "/AccesAthletique/Backup/mesRapportsAthletes_bak.db";
            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Close the streams
            output.flush();
            output.close();
            fis.close();
        }
    }

    private boolean isSDCardWriteable() {
        boolean rc = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            rc = true;
        }
        return rc;
    }
}