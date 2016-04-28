package www.samnang_alex.com.applicationaccesathletique.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lblDateDuRapport = (TextView) findViewById(R.id.lblDateDuRapport);
        lblDateDuRapport.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblDateDuRapport.setTextColor(Color.BLACK);

        dpDateDuRapport = (DatePicker) findViewById(R.id.dpDateDuRapport);

        btnGenererRapportTherapeute = (Button) findViewById(R.id.btnGenererRapportTherapeute);
        btnGenererRapportTherapeute.setBackgroundColor(Color.parseColor("#ff6666"));
        btnGenererRapportTherapeute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jour, mois, annee = 1;
                jour = dpDateDuRapport.getDayOfMonth();
                mois = dpDateDuRapport.getMonth();
                annee = dpDateDuRapport.getYear();
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