package www.samnang_alex.com.applicationaccesathletique.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import www.samnang_alex.com.applicationaccesathletique.DAO.AthleteHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.BlessureHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.EcoleHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.EquipeHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.EvenementHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.RaffinementMembreHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.RapportTherapeuteHelper;
import www.samnang_alex.com.applicationaccesathletique.DAO.RestrictionHelper;
import www.samnang_alex.com.applicationaccesathletique.R;
import www.samnang_alex.com.applicationaccesathletique.models.Athlete;
import www.samnang_alex.com.applicationaccesathletique.models.Blessure;
import www.samnang_alex.com.applicationaccesathletique.models.Ecole;
import www.samnang_alex.com.applicationaccesathletique.models.Equipe;
import www.samnang_alex.com.applicationaccesathletique.models.Evenement;
import www.samnang_alex.com.applicationaccesathletique.models.RaffinementMembre;
import www.samnang_alex.com.applicationaccesathletique.models.RapportTherapeute;
import www.samnang_alex.com.applicationaccesathletique.models.Restriction;

public class GenererRapportTherapeuteActivity extends AppCompatActivity {

    TextView lblDateDuRapport;
    DatePicker dpDateDuRapport;
    Button btnGenererRapportTherapeute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generer_rapport_therapeute);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        lblDateDuRapport = (TextView) findViewById(R.id.lblDateDuRapport);
        lblDateDuRapport.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblDateDuRapport.setTextColor(Color.BLACK);

        dpDateDuRapport = (DatePicker) findViewById(R.id.dpDateDuRapport);

        btnGenererRapportTherapeute = (Button) findViewById(R.id.btnGenererRapportTherapeute);
        btnGenererRapportTherapeute.setBackgroundColor(Color.parseColor("#ff6666"));
        btnGenererRapportTherapeute.setTypeface(null, Typeface.BOLD);
        btnGenererRapportTherapeute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jour, mois, annee = 1;
                jour = dpDateDuRapport.getDayOfMonth();
                mois = dpDateDuRapport.getMonth();
                annee = dpDateDuRapport.getYear();
                RapportTherapeuteHelper rapportTherapeuteHelper = new RapportTherapeuteHelper(GenererRapportTherapeuteActivity.this);
                // Création des rapports pour thérapeute regroupés par école
                Cursor curseurSchoolTeamName = rapportTherapeuteHelper.findSchoolTeamNameByDate(jour, mois, annee);
                while(curseurSchoolTeamName.moveToNext()) {
                    String nomEcole = curseurSchoolTeamName.getString(curseurSchoolTeamName.getColumnIndex("nomEcole"));
                    String nomEquipe = curseurSchoolTeamName.getString(curseurSchoolTeamName.getColumnIndex("nomEquipe"));
                    RapportTherapeute rapportTherapeute = new RapportTherapeute();
                    rapportTherapeute.setNomEcole(nomEcole);
                    rapportTherapeute.setNomEquipe(nomEquipe);
                    rapportTherapeute.setDateEvenement( jour + "/" + mois + "/" + annee );
                    rapportTherapeute.setNomRapport(jour + "_" + mois + "_" + annee + " - Rapport " + nomEcole + ":" + nomEquipe);
                    Cursor curseurAthletesBlesses = rapportTherapeuteHelper.findAllBySchoolTeamDate(nomEcole, nomEquipe, jour, mois, annee);
                    List<String> uneLigne = new ArrayList<String>();
                    while(curseurAthletesBlesses.moveToNext()) {
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("nomPatient")).toUpperCase() + " - " + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("prenomPatient")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("jourBlessure")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("moisBlessure")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("anneeBlessure")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("jourRetourEntrainement")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("moisRetourEntrainement")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("anneeRetourEntrainement")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("jourRetourJeu")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("moisRetourJeu")) + "/" + curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("anneeRetourJeu")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("membreAffecte")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("precisionMembre")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("raffinementMembre")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("soapA")));
                        uneLigne.add(curseurAthletesBlesses.getString(curseurAthletesBlesses.getColumnIndex("commentaire")));
                    }
                    rapportTherapeute.setUneLigne(uneLigne);
                    rapportTherapeute.creerRapport();
                }

                // Création du fichier CSV pour Excel
                Cursor curseurRapportTherapeute = rapportTherapeuteHelper.findByDate(jour, mois, annee);
                //curseurRapportTherapeute.moveToFirst();
                if(curseurRapportTherapeute.moveToNext()) {
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
                    }while(curseurRapportTherapeute.moveToNext());
                    printWriter.close();
                    Toast toast = Toast.makeText(GenererRapportTherapeuteActivity.this, "BRAVO ! " + compteurLignes + " blessure a été bien enregistré le " + jour + "/" + mois + "/" + annee /*+ " - \n" + message*/, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextColor(Color.WHITE);
                    messageTextView.setBackgroundColor(Color.parseColor("#66e166"));
                    messageTextView.setTextSize(24);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(GenererRapportTherapeuteActivity.this, "Désolé. Aucune blessure a été enregistrée le " + jour + "/" + mois + "/" + annee, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextColor(Color.WHITE);
                    messageTextView.setBackgroundColor(Color.parseColor("#ffdb99"));
                    messageTextView.setTextSize(24);
                    toast.show();
                }
                /*
                Evenement evenement = new Evenement();
                evenement.setJour(jour);
                evenement.setMois(mois);
                evenement.setAnnee(annee);
                EvenementHelper evenementHelper = new EvenementHelper(GenererRapportTherapeuteActivity.this);
                Cursor curseurEvenement = evenementHelper.findByDate(evenement);
                if(curseurEvenement!=null) {
                    int idEvenement = Integer.parseInt( curseurEvenement.getColumnName(curseurEvenement.getColumnIndex("id")) );
                    EcoleHelper ecoleHelper = new EcoleHelper(GenererRapportTherapeuteActivity.this);
                    RapportTherapeute[] rapportsTherapeutes = new RapportTherapeute[ecoleHelper.getNombreEcolesAssocies(idEvenement)];
                    for(int i=0; i < rapportsTherapeutes.length; i ++)
                        rapportsTherapeutes[i] = new RapportTherapeute();
                    List<String> listeNomsEcoles = ecoleHelper.getListeNomsEcoles(idEvenement);
                    for(int i=0; i < listeNomsEcoles.size(); i++) {
                        rapportsTherapeutes[i].setNomEcole(listeNomsEcoles.get(i));
                        rapportsTherapeutes[i].setDateEvenement(jour + "-" + mois + "-" + annee);
                        rapportsTherapeutes[i].setNomRapport("Rapport Thérapeute - " + rapportsTherapeutes[i].getNomEcole() + " " + rapportsTherapeutes[i].getDateEvenement());
                    }
                    Blessure blessure = new Blessure();
                    blessure.setIdEvenement(idEvenement);
                    BlessureHelper blessureHelper = new BlessureHelper(GenererRapportTherapeuteActivity.this);
                    Cursor curseurBlessure = blessureHelper.findByIdEvenement(blessure);
                    while(curseurBlessure.moveToNext()) {
                        Athlete athlete = new Athlete();
                        athlete.setId(Integer.parseInt(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("idAthlete"))));
                        AthleteHelper athleteHelper = new AthleteHelper(GenererRapportTherapeuteActivity.this);
                        Cursor curseurAthlete = athleteHelper.findById(athlete);
                        String nomEcole = ecoleHelper.findNameById(Integer.parseInt(curseurAthlete.getColumnName(curseurAthlete.getColumnIndex("idEcole"))));
                        for (int i = 0; i < rapportsTherapeutes.length; i++) {
                            if (rapportsTherapeutes[i].getNomEcole().equals(nomEcole)) {
                                EquipeHelper equipeHelper = new EquipeHelper(GenererRapportTherapeuteActivity.this);
                                rapportsTherapeutes[i].setNomEquipe(equipeHelper.findNameById(Integer.parseInt(curseurAthlete.getColumnName(curseurAthlete.getColumnIndex("")))));
                                List<String> uneLigne = new ArrayList<String>();
                                uneLigne = rapportsTherapeutes[i].getUneLigne();
                                uneLigne.add(curseurAthlete.getColumnName(curseurAthlete.getColumnIndex("nom")).toUpperCase() + ", " + curseurAthlete.getColumnName(curseurAthlete.getColumnIndex("prenom")));
                                uneLigne.add(jour + "/" + mois + "/" + annee);
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("jourRetourEntrainement")) + "/" + curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("moisRetourEntrainement")) + "/" + curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("anneeRetourEntrainement")));
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("jourRetourJeu")) + "/" + curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("moisRetourJeu")) + "/" + curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("anneeRetourJeu")));
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("membreAffecte")));
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("precisionMembre")));
                                RaffinementMembre raffinementMembre = new RaffinementMembre();
                                raffinementMembre.setId(Integer.parseInt(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("idRaffinementMembre"))));
                                RaffinementMembreHelper raffinementMembreHelper = new RaffinementMembreHelper(GenererRapportTherapeuteActivity.this);
                                Cursor curseurRaffinementMembre = raffinementMembreHelper.findById(raffinementMembre);
                                List<String> listeRaffinementMembre = new ArrayList<String>();
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("musles"))) == 1)
                                    listeRaffinementMembre.add("Muscles");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("ligaments"))) == 1)
                                    listeRaffinementMembre.add("Ligaments");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("os"))) == 1)
                                    listeRaffinementMembre.add("Os");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("nerfs"))) == 1)
                                    listeRaffinementMembre.add("Nerfs");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("autre"))) == 1)
                                    listeRaffinementMembre.add("Autre");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("rien"))) == 1)
                                    listeRaffinementMembre.add("Rien");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("oeilDroit"))) == 1)
                                    listeRaffinementMembre.add("Oeil droit");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("oeilGauche"))) == 1)
                                    listeRaffinementMembre.add("Oeil gauche");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("nez"))) == 1)
                                    listeRaffinementMembre.add("Nez");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("bouche"))) == 1)
                                    listeRaffinementMembre.add("Bouche");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("dents"))) == 1)
                                    listeRaffinementMembre.add("Dents");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("sternum"))) == 1)
                                    listeRaffinementMembre.add("Sternum");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("cotes"))) == 1)
                                    listeRaffinementMembre.add("Côtes");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("sacrum"))) == 1)
                                    listeRaffinementMembre.add("Sacrum");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("osIliaque"))) == 1)
                                    listeRaffinementMembre.add("Os iliaques");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("coccyx"))) == 1)
                                    listeRaffinementMembre.add("Coccyx");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("foie"))) == 1)
                                    listeRaffinementMembre.add("Foie");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("rate"))) == 1)
                                    listeRaffinementMembre.add("Rate");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("pancreas"))) == 1)
                                    listeRaffinementMembre.add("Pancréas");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("rein"))) == 1)
                                    listeRaffinementMembre.add("Rein");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("coeur"))) == 1)
                                    listeRaffinementMembre.add("Coeur");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("poumon"))) == 1)
                                    listeRaffinementMembre.add("Poumon");
                                if (Integer.parseInt(curseurRaffinementMembre.getColumnName(curseurRaffinementMembre.getColumnIndex("genitaux"))) == 1)
                                    listeRaffinementMembre.add("Génitaux");
                                uneLigne.add(listeRaffinementMembre.toString());
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("soapA")));
                                uneLigne.add(curseurBlessure.getColumnName(curseurBlessure.getColumnIndex("commentaire")));
                            }
                            break;
                        }
                    }
                    for (RapportTherapeute rapport : rapportsTherapeutes) {
                        rapport.creerRapport();
                    }
                    Toast toast = Toast.makeText(GenererRapportTherapeuteActivity.this, rapportsTherapeutes.length + " rapports ont été créés.", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(24);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(GenererRapportTherapeuteActivity.this, "Désolé. Aucune blessure a eu lieu le " + jour + "/" + mois + "/" + annee + ".", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(24);
                    toast.show();
                }
                */
            }
        });
    }
}