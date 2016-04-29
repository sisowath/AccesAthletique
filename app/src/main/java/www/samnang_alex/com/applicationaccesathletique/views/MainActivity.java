package www.samnang_alex.com.applicationaccesathletique.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import www.samnang_alex.com.applicationaccesathletique.DAO.RapportAthleteHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.RapportTherapeuteHelper;
import www.samnang_alex.com.applicationaccesathletique.R;

public class MainActivity extends Activity {

    ImageView imgLogo;
    TextView lblCoordonnesEntreprise;
    Button btnCreerUneBlessure;
    Button btnAjouterUnEvenement;
    Button btnGenererRapport;
    Button btnGestionDeLaBD;
    Button btnGenererCSV;
    Button btnResetDatabase;
    TextView lblCopyrightSamnang;

    public static final Font BOLD_UNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE);
    public static final Font mesTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLACK);
    public static final Font mesSousTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.acces_athletique_logo);
        lblCoordonnesEntreprise = (TextView) findViewById(R.id.lblCoordonneesEntreprise);
        lblCoordonnesEntreprise.setText("1587, Rainaud\nMontréal (Québec), Canada H1A 4L5\nTéléphone : 514 795-5683");
        lblCoordonnesEntreprise.setTextColor(Color.BLACK);
        //txtCoordonnesEntreprise.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCreerUneBlessure = (Button) findViewById(R.id.btnCreerUneBlessure);
        btnCreerUneBlessure.setBackgroundColor(Color.parseColor("#0080ff"));
        btnCreerUneBlessure.setTextColor(Color.WHITE);
        btnCreerUneBlessure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreerUneBlessureActivity.class);
                startActivity(intent);
            }
        });
        btnAjouterUnEvenement = (Button) findViewById(R.id.btnAjouterUnEvenement);
        btnAjouterUnEvenement.setBackgroundColor(Color.parseColor("#0080ff"));
        btnAjouterUnEvenement.setTextColor(Color.WHITE);
        btnAjouterUnEvenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
            }
        });
        btnGenererRapport = (Button) findViewById(R.id.btnGenererRapportPDF);
        btnGenererRapport.setBackgroundColor(Color.parseColor("#0080ff"));
        btnGenererRapport.setTextColor(Color.WHITE);
        btnGenererRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenererRapportTherapeuteActivity.class);
                startActivity(intent);
                /*
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
                */
                /* oi-haut source de :
                http://stackoverflow.com/questions/15321186/how-to-display-toast-at-center-of-screen
                http://stackoverflow.com/questions/5274354/how-can-we-increase-the-font-size-in-toast
                */
            }
        });
        btnGestionDeLaBD = (Button) findViewById(R.id.btnGestionDeLaBD);
        btnGestionDeLaBD.setBackgroundColor(Color.parseColor("#0080ff"));
        btnGestionDeLaBD.setTextColor(Color.WHITE);
        btnGestionDeLaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
            }
        });
        btnGenererCSV = (Button) findViewById(R.id.btnGenererCSV);
        btnGenererCSV.setBackgroundColor(Color.parseColor("#0080ff"));
        btnGenererCSV.setTextColor(Color.WHITE);
        btnGenererCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Création du fichier CSV pour Excel
                Calendar calendar = Calendar.getInstance();
                int jour = calendar.get(Calendar.DAY_OF_MONTH);
                int mois = calendar.get(Calendar.MONTH);
                int annee = calendar.get(Calendar.YEAR);
                RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(MainActivity.this);
                Cursor curseurRapportTherapeute = rapportTherapeuteHelper.findAll();/*rapportTherapeuteHelper.findByDate(jour, mois, annee);*/
                //curseurRapportTherapeute.moveToFirst();
                if (curseurRapportTherapeute.moveToNext()) {
                    File fichier = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), jour + "_" + mois + "_" + annee + " - CSV.txt");
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(fichier);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    PrintWriter printWriter = new PrintWriter(fileOutputStream);
                    String message = "";
                    int compteurLignes = 0;
                    printWriter.println("Nom École, Nom Équipe, Date de l'événement, Nom du patient, Date de la blessure, Date de retour à l'entrainement, Date de retour au jeu, Membre affecté, Précision sur le membre, Raffinement sur le membre, SO(A)P, Commentaire/Recommandations");
                    printWriter.flush();
                    do {
                        String nomEcole = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("nomEcole"));
                        String nomEquipe = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("nomEquipe"));
                        String dateEvenement = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("jourEvenement")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("moisEvenement")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("anneeEvenement"));
                        String nomPrenomPatient = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("nomPatient")).toUpperCase() + "-" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("prenomPatient"));
                        String dateDeLaBlessure = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("jourBlessure")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("moisBlessure")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("anneeBlessure"));
                        String dateRetourEntrainement = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("jourRetourEntrainement")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("moisRetourEntrainement")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("anneeRetourEntrainement"));
                        String dateRetourJeu = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("jourRetourJeu")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("moisRetourJeu")) + "/" + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("anneeRetourJeu"));
                        String membreAffecte = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("membreAffecte"));
                        String precisionMembre = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("precisionMembre"));
                        String raffinementMembre = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("raffinementMembre"));
                        String soapA = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("soapA"));
                        String commentaire = curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("commentaire"));
                        printWriter.println(nomEcole + "," + nomEquipe + "," + dateEvenement + "," + nomPrenomPatient + "," + dateDeLaBlessure + "," + dateRetourEntrainement + "," + dateRetourJeu + "," + membreAffecte + "," + precisionMembre + "," + raffinementMembre + "," + soapA + "," + commentaire);
                        printWriter.flush();
                        message += curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("id")) + " :: " + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("nomPatient")) + " :: " + curseurRapportTherapeute.getString(curseurRapportTherapeute.getColumnIndex("prenomPatient")) + "\n";
                        compteurLignes++;
                    } while (curseurRapportTherapeute.moveToNext());
                    printWriter.close();
                    Toast toast = Toast.makeText(MainActivity.this, "BRAVO ! Votre fichier < " + jour + "_" + mois + "_" + annee + " - CSV.txt > a été créé.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextColor(Color.WHITE);
                    messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                    messageTextView.setTextSize(24);
                    toast.show();
                }
            }
        });
        btnResetDatabase = (Button) findViewById(R.id.btnResetDatabase);
        btnResetDatabase.setBackgroundColor(Color.parseColor("#0080ff"));
        btnResetDatabase.setTextColor(Color.WHITE);
        btnResetDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new AlertDialog.Builder(MainActivity.this)
                .setTitle("Avertissement")
                .setMessage("Attention ! Voulez-vous vraiment réinitialiser la base de données ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(/*android.R.string.yes*/"Oui", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        RapportAthleteHelper rapportAthleteHelper = new RapportAthleteHelper(MainActivity.this);
                        rapportAthleteHelper.dropTable();
                        RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(MainActivity.this);
                        rapportTherapeuteHelper.dropTable();
                        Toast toast = Toast.makeText(MainActivity.this, "Les tables ont été supprimées avec succès !", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    }
                })
                .setNegativeButton(/*android.R.string.no*/"Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(MainActivity.this, "Aucune suppression a été fait.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextColor(Color.WHITE);
                        //messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                        messageTextView.setTextSize(36);
                        toast.show();
                    }
                }).show();
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //btnGenererRapport.setText("Widhth : " + size.x + " :: Height : " + size.y);
        if(size.x < 1000) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            /*ImageView logo = (ImageView) findViewById(R.id.imgLogo);
            lp.setMargins(0, 500, 0, 0);
            logo.setLayoutParams(lp);*/
            lblCoordonnesEntreprise.setTextSize(18);
            lp.setMargins(50, 100, 50, 0);
            btnCreerUneBlessure.setLayoutParams(lp);
            lp.setMargins(50, 20, 50, 20);
            btnAjouterUnEvenement.setLayoutParams(lp);
            btnGenererRapport.setLayoutParams(lp);
            btnGestionDeLaBD.setLayoutParams(lp);
            btnGenererCSV.setLayoutParams(lp);
            btnResetDatabase.setLayoutParams(lp);
            /* Source de :
                http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
                http://stackoverflow.com/questions/12728255/in-android-how-do-i-set-margins-in-dp-programmatically
            */
        }
        btnAjouterUnEvenement.setVisibility(View.GONE);
        btnGestionDeLaBD.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.iGererAthlete :
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Titre");
                alertDialogBuilder.setMessage("Message");
                break;
            case R.id.iGereEquipe :

                break;
            case R.id.iGererEcole :

                break;
        }
        return true;
    }
}