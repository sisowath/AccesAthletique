package www.samnang_alex.com.applicationaccesathletique.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import www.samnang_alex.com.applicationaccesathletique.DAO.RapportAthleteHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.RapportTherapeuteHelper;
import www.samnang_alex.com.applicationaccesathletique.R;

public class ImportExportDB extends Activity {
    Button btnExport, btnImport, btnRestore, btnHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_export_import);
        //creating a new folder for the database to be backuped to
        File direct = new File(Environment.getExternalStorageDirectory() + "/AccesAthletique");
        if(!direct.exists())
        {
            if(direct.mkdir())
            {
                Toast toast = Toast.makeText(this, "Un dossier AccesAthletique a été créé sur votre carte SD.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
            }

        }
        direct = new File(Environment.getExternalStorageDirectory() + "/AccesAthletique/Import");
        if(!direct.exists())
        {
            if(direct.mkdir())
            {
                Toast toast = Toast.makeText(this, "Un dossier Import a été créé sur votre carte SD dans le dossier AccesAthletique.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
            }

        }

        btnExport = (Button) findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(ImportExportDB.this);
                RapportAthleteHelper rapportAthleteHelper = new RapportAthleteHelper(ImportExportDB.this);
                try {
                    rapportTherapeuteHelper.backupDatabase();
                    rapportAthleteHelper.backupDatabase();
                    Toast toast = Toast.makeText(ImportExportDB.this, "Enregistrement réussit!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextColor(Color.WHITE);
                    messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                    messageTextView.setTextSize(36);
                    toast.show();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(ImportExportDB.this, "Il n'y a pas de base de données à exporter!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextColor(Color.WHITE);
                    //messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                    messageTextView.setTextSize(36);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        btnImport = (Button) findViewById(R.id.btnImport);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(ImportExportDB.this);
                RapportAthleteHelper rapportAthleteHelper = new RapportAthleteHelper(ImportExportDB.this);
                try {
                    if (!rapportTherapeuteHelper.importDatabase() || !rapportAthleteHelper.importDatabase())
                    {
                        Toast toast = Toast.makeText(ImportExportDB.this, "Vérifier que les fichiers sont bien dans AccesAthletique/Import", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        //messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(ImportExportDB.this, "Importation réussite!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnRestore = (Button) findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(ImportExportDB.this);
                RapportAthleteHelper rapportAthleteHelper = new RapportAthleteHelper(ImportExportDB.this);
                try {
                    if (!rapportTherapeuteHelper.restoreDatabase() || !rapportAthleteHelper.restoreDatabase())
                    {
                        Toast toast = Toast.makeText(ImportExportDB.this, "Il n'y a pas de base de données en backup!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        //messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(ImportExportDB.this, "Restoration réussite!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ImportExportDB.this);
                dlgAlert.setTitle("Aide");
                dlgAlert.setMessage("Pour l'importation, il est important d'avoir mis au préavis les fichiers mesRapportsAthletes_bak.db et MesRapportsTherapeutes_bak.db dans le dossier AccesAthletique/Import de votre carte SD de votre téléphone cellulaire.");
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        });
                dlgAlert.show();
            }
        });
    }
}
