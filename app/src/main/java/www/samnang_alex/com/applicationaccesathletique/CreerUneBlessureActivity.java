package www.samnang_alex.com.applicationaccesathletique;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import www.samnang_alex.com.applicationaccesathletique.models.RapportAthlete;
import www.samnang_alex.com.applicationaccesathletique.models.RapportTherapeute;

import static java.lang.Character.toUpperCase;

public class CreerUneBlessureActivity extends Activity {

    private int annee;
    private int mois;
    private int jour;
    AutoCompleteTextView atxtNomPatient;
    AutoCompleteTextView atxtPrenomPatient;
    AutoCompleteTextView atxtNomEquipe;
    AutoCompleteTextView atxtNomEcole;
    AutoCompleteTextView atxtNumeroJoueur;
    String[] listeDesColleges = {"Cégep André-Laurendeau", "Collège Ahuntsic", "Collège de Bois-De-Boulogne", "Cégep de Rosemont", "Cégep de Saint-Laurent"};
    TextView lblCoordonnesDuPatient;
    TextView lblDateEvenement;
    TextView lblTypeEvenement;
    Spinner sTypeEvenement;
    TextView lblDateDeLaBlessure;
    TextView lblDateDeRetourEntrainement;
    TextView lblDateDeRetourAuJeu;
    TextView lblDescriptionDeLaBlessure;
    TextView lblRegionAffectee;
    Spinner sRegionAffectee;
    TextView lblPrecisionSurLaRegionAffectee;
    Spinner sPrecisionSurLaRegionAffectee;
    TextView lblDescriptionDetailleeDeLaRegionBlessee;
    DatePicker dpDateDeLaBlessure;
    DatePicker dpDateDeRetourEntrainement;
    DatePicker dpDateRetourJeu;
    TextView lblInformationsSupplementaires;
    TextView lblContexteDeLaBlessure;
    Spinner sContexteDeLaBlessure;
    TextView lblRestrictionAthlete;
    TextView lblSuiviDeLaBlessure;
    TextView lblDescriptionDeLaCondition;
    EditText txtDescriptionDeLaCondition;
    TextView lblMecanismeDeBlessure;
    EditText txtMecanismeDeBlessure;
    TextView lblSoapS;
    TextView lblSoapO;
    TextView lblSoapA;
    TextView lblSoapP;
    TextView lblAutreCommentaireRecommandation;
    Button btnSauvegarderRapport;
    public static final Font BOLD_UNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE);
    public static final Font mesTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLACK);
    public static final Font mesSousTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
    LinearLayout[] mesLinearLayout = new LinearLayout[6];
    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_une_blessure);

        checkBoxes = new CheckBox[30];
        checkBoxes[0] = (CheckBox) findViewById(R.id.cbxRien);
        checkBoxes[1] = (CheckBox) findViewById(R.id.cbxOeilDroit);
        checkBoxes[2] = (CheckBox) findViewById(R.id.cbxOeilGauche);
        checkBoxes[3] = (CheckBox) findViewById(R.id.cbxNez);
        checkBoxes[4] = (CheckBox) findViewById(R.id.cbxBouche);
        checkBoxes[5] = (CheckBox) findViewById(R.id.cbxDents);
        checkBoxes[6] = (CheckBox) findViewById(R.id.cbxAutre);
        checkBoxes[7] = (CheckBox) findViewById(R.id.cbxMuscles);
        checkBoxes[8] = (CheckBox) findViewById(R.id.cbxLigaments);
        checkBoxes[9] = (CheckBox) findViewById(R.id.cbxOs);
        checkBoxes[10] = (CheckBox) findViewById(R.id.cbxNerfs);
        checkBoxes[11] = (CheckBox) findViewById(R.id.cbxAutre2);
        checkBoxes[12] = (CheckBox) findViewById(R.id.cbxSternum);
        checkBoxes[13] = (CheckBox) findViewById(R.id.cbxCotes);
        checkBoxes[14] = (CheckBox) findViewById(R.id.cbxMuscles2);
        checkBoxes[15] = (CheckBox) findViewById(R.id.cbxAutre3);
        checkBoxes[16] = (CheckBox) findViewById(R.id.cbxSacrum);
        checkBoxes[17] = (CheckBox) findViewById(R.id.cbxOsIliaque);
        checkBoxes[18] = (CheckBox) findViewById(R.id.cbxCoccyx);
        checkBoxes[19] = (CheckBox) findViewById(R.id.cbxMuscles3);
        checkBoxes[20] = (CheckBox) findViewById(R.id.cbxLigaments2);
        checkBoxes[21] = (CheckBox) findViewById(R.id.cbxAutre4);
        checkBoxes[22] = (CheckBox) findViewById(R.id.cbxFoie);
        checkBoxes[23] = (CheckBox) findViewById(R.id.cbxRate);
        checkBoxes[24] = (CheckBox) findViewById(R.id.cbxPancreas);
        checkBoxes[25] = (CheckBox) findViewById(R.id.cbxRein);
        checkBoxes[26] = (CheckBox) findViewById(R.id.cbxCoeur);
        checkBoxes[27] = (CheckBox) findViewById(R.id.cbxPoumon);
        checkBoxes[28] = (CheckBox) findViewById(R.id.cbxGenitaux);
        checkBoxes[29] = (CheckBox) findViewById(R.id.cbxAutre5);
        checkBoxes[30] = (CheckBox) findViewById(R.id.cbxPratiqueAvecRestriction);
        checkBoxes[31] = (CheckBox) findViewById(R.id.cbxPartieAvecRestriction);
        checkBoxes[32] = (CheckBox) findViewById(R.id.cbxAucunePratique);
        checkBoxes[33] = (CheckBox) findViewById(R.id.cbxAucunePartie);
        checkBoxes[34] = (CheckBox) findViewById(R.id.cbxAucuneRestriction);

        atxtNomPatient = (AutoCompleteTextView) findViewById(R.id.atxtNomPatient);
        atxtPrenomPatient = (AutoCompleteTextView) findViewById(R.id.atxtPrenomPatient);
        atxtNomEquipe = (AutoCompleteTextView) findViewById(R.id.atxtNomEquipe);
        atxtNomEcole = (AutoCompleteTextView) findViewById(R.id.atxtNomEcole);
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listeDesColleges);
        atxtNomEcole.setAdapter(dataAdapter);
        atxtNomEcole.setThreshold(3);
        atxtNumeroJoueur = (AutoCompleteTextView) findViewById(R.id.atxtNumeroJoueur);
        dpDateDeRetourEntrainement = (DatePicker) findViewById(R.id.dpDateDeRetourEntrainement);
        /*final Calendar c = Calendar.getInstance();
        annee = c.get(Calendar.YEAR);
        mois = c.get(Calendar.MONTH);
        jour = c.get(Calendar.DAY_OF_MONTH);
        dpDateDeLaBlessure.init(annee, mois, jour, null);
        dpDateDeRetourEntrainement.init(annee, mois, jour, null);*/
        dpDateRetourJeu = (DatePicker) findViewById(R.id.dpDateDeRetourAuJeu);

        mesLinearLayout[0] = (LinearLayout) findViewById(R.id.llRien);
        mesLinearLayout[1] = (LinearLayout) findViewById(R.id.llVisage);
        mesLinearLayout[2] = (LinearLayout) findViewById(R.id.llColonne);
        mesLinearLayout[3] = (LinearLayout) findViewById(R.id.llCageThoracique);
        mesLinearLayout[4] = (LinearLayout) findViewById(R.id.llBassin);
        mesLinearLayout[5] = (LinearLayout) findViewById(R.id.llOrganesInternes);
        for(int i=1; i < mesLinearLayout.length; i++) {
            mesLinearLayout[i].setVisibility(View.GONE);
        }

        lblCoordonnesDuPatient = (TextView) findViewById(R.id.lblCoordonneDuPatient);
        lblCoordonnesDuPatient.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblCoordonnesDuPatient.setTextColor(Color.BLACK);

        lblDateEvenement = (TextView) findViewById(R.id.lblDateEvenement);
        lblDateEvenement.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblDateEvenement.setTextColor(Color.BLACK);
        lblTypeEvenement = (TextView) findViewById(R.id.lblTypeEvenement);
        lblTypeEvenement.setBackgroundColor(Color.parseColor("#9999ff"));
        lblTypeEvenement.setTextColor(Color.BLACK);
        sTypeEvenement = (Spinner) findViewById(R.id.sTypeEvenement);
        List<String> listeDesEvenement = new ArrayList<>();
        listeDesEvenement.add("Pratique");
        listeDesEvenement.add("Match");
        listeDesEvenement.add("Tournoi");
        ArrayAdapter<String> adapterEvenement = new ArrayAdapter<String>(this, R.layout.spinner_item, listeDesEvenement);
        sTypeEvenement.setAdapter(adapterEvenement);
        lblDateDeLaBlessure = (TextView) findViewById(R.id.lblDateDeLaBlessure);
        lblDateDeLaBlessure.setBackgroundColor(Color.parseColor("#9999ff"));
        lblDateDeLaBlessure.setTextColor(Color.BLACK);
        lblDateDeRetourEntrainement = (TextView) findViewById(R.id.lblDateDeRetourEntrainement);
        lblDateDeRetourEntrainement.setBackgroundColor(Color.parseColor("#9999ff"));
        lblDateDeRetourEntrainement.setTextColor(Color.BLACK);
        lblDateDeRetourAuJeu = (TextView) findViewById(R.id.lblDateDeRetourAuJeu);
        lblDateDeRetourAuJeu.setBackgroundColor(Color.parseColor("#9999ff"));
        lblDateDeRetourAuJeu.setTextColor(Color.BLACK);

        lblDescriptionDeLaBlessure = (TextView) findViewById(R.id.lblDescriptionDeLaBlessure);
        lblDescriptionDeLaBlessure.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblDescriptionDeLaBlessure.setTextColor(Color.BLACK);
        lblRegionAffectee = (TextView) findViewById(R.id.lblRegionAffectee);
        lblRegionAffectee.setBackgroundColor(Color.parseColor("#9999ff"));
        lblRegionAffectee.setTextColor(Color.BLACK);
        sRegionAffectee = (Spinner) findViewById(R.id.sRegionAffectee);
        List<String> listeDesRegionsAffectees = new ArrayList<>();
        listeDesRegionsAffectees.add("Tête");
        listeDesRegionsAffectees.add("Cou");
        listeDesRegionsAffectees.add("Tronc");
        listeDesRegionsAffectees.add("Membre supérieur gauche");
        listeDesRegionsAffectees.add("Membre supérieur droite");
        listeDesRegionsAffectees.add("Membre inférieur gauche");
        listeDesRegionsAffectees.add("Membre inférieur droite");
        sRegionAffectee.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, listeDesRegionsAffectees));/*
        Source de : http://stackoverflow.com/questions/9476665/how-to-change-spinner-text-size-and-text-color */
        dpDateDeLaBlessure = (DatePicker) findViewById(R.id.dpDateDeLaBlessure);
        lblPrecisionSurLaRegionAffectee = (TextView) findViewById(R.id.lblPrecisionSurLaRegionAffectee);
        lblPrecisionSurLaRegionAffectee.setBackgroundColor(Color.parseColor("#9999ff"));
        lblPrecisionSurLaRegionAffectee.setTextColor(Color.BLACK);
        sPrecisionSurLaRegionAffectee = (Spinner) findViewById(R.id.sPrecisionSurLaRegionAffectee);

        sRegionAffectee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(CreerUneBlessureActivity.this, "getSelectedItem() = " + sRegionAffectee.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                List<String> listeDesOptions = new ArrayList<String>();
                switch (sRegionAffectee.getSelectedItem().toString()) {
                    case "Tête":
                        listeDesOptions.add("Crâne");
                        listeDesOptions.add("Visage");
                        listeDesOptions.add("Mâchoire");
                        listeDesOptions.add("Autre");
                        break;
                    case "Cou":
                        listeDesOptions.add("Colonne cervicale haute");
                        listeDesOptions.add("Colonne cervicale basse");
                        listeDesOptions.add("Gorge");
                        listeDesOptions.add("Autre");
                        break;
                    case "Tronc":
                        listeDesOptions.add("Colonne dorsale haut D1 à D4");
                        listeDesOptions.add("Colonne dorsale moyen D5 à D8");
                        listeDesOptions.add("Colonne dorsale bas D9 à D12");
                        listeDesOptions.add("Colonne lombaire haut L1 à L3");
                        listeDesOptions.add("Colonne lombaire bas L5 à L5");
                        listeDesOptions.add("Bassin");
                        listeDesOptions.add("Organes internes");
                        listeDesOptions.add("Cage thoraxique");
                        listeDesOptions.add("Autre");
                        break;
                    case "Membre supérieur gauche":
                    case "Membre supérieur droite":
                        listeDesOptions.add("Épaule");
                        listeDesOptions.add("Bras");
                        listeDesOptions.add("Coude");
                        listeDesOptions.add("Avant-bras");
                        listeDesOptions.add("Poignet");
                        listeDesOptions.add("Main");
                        listeDesOptions.add("Autre");
                        break;
                    case "Membre inférieur gauche":
                    case "Membre inférieur droite":
                        listeDesOptions.add("Hanche");
                        listeDesOptions.add("Cuisse");
                        listeDesOptions.add("Genou");
                        listeDesOptions.add("Jambe");
                        listeDesOptions.add("Cheville");
                        listeDesOptions.add("Pied");
                        listeDesOptions.add("Autre");
                        break;
                }
                /*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CreerUneBlessureActivity.this, android.R.layout.simple_spinner_item, listeDesOptions);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sPrecisionSurLaRegionAffectee.setAdapter(dataAdapter);*/
                sPrecisionSurLaRegionAffectee.setAdapter(new ArrayAdapter<String>(CreerUneBlessureActivity.this, R.layout.spinner_item, listeDesOptions));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lblDescriptionDetailleeDeLaRegionBlessee = (TextView) findViewById(R.id.lblDescriptionDetailleeDeLaRegionBlessee);
        lblDescriptionDetailleeDeLaRegionBlessee.setBackgroundColor(Color.parseColor("#9999ff"));
        lblDescriptionDetailleeDeLaRegionBlessee.setTextColor(Color.BLACK);
        sPrecisionSurLaRegionAffectee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(CheckBox cbx : checkBoxes) {
                    cbx.setChecked(false);
                }
                List<String> listeDesOptions = new ArrayList<String>();
                switch (sPrecisionSurLaRegionAffectee.getSelectedItem().toString()) {
                    case "Crâne":
                    case "Mâchoire":
                    case "Autre":
                    case "Gorge":
                        //listeDesOptions.add("Rien");
                        for(int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[0].setVisibility(View.VISIBLE);
                        checkBoxes[0].setChecked(true);
                        break;
                    case "Visage":
                        /*listeDesOptions.add("Oeil droit");
                        listeDesOptions.add("Oeil gauche");
                        listeDesOptions.add("Nez");
                        listeDesOptions.add("Bouche");
                        listeDesOptions.add("Dents");
                        listeDesOptions.add("Autre");*/
                        for(int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[1].setVisibility(View.VISIBLE);
                        break;
                    case "Cage thoraxique":
                        /*listeDesOptions.add("Sternum");
                        listeDesOptions.add("Côtes");
                        listeDesOptions.add("Muscles");
                        listeDesOptions.add("Autre");*/
                        for (int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[3].setVisibility(View.VISIBLE);
                        break;
                    case "Bassin":
                        /*listeDesOptions.add("Sacrum");
                        listeDesOptions.add("Os iliaque");
                        listeDesOptions.add("Coccyx");
                        listeDesOptions.add("Muscles");
                        listeDesOptions.add("Ligaments");
                        listeDesOptions.add("Autre");*/
                        for (int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[4].setVisibility(View.VISIBLE);
                        break;
                    case "Organes internes":
                        /*listeDesOptions.add("Foie");
                        listeDesOptions.add("Rate");
                        listeDesOptions.add("Pancréas");
                        listeDesOptions.add("Rein");
                        listeDesOptions.add("Coeur");
                        listeDesOptions.add("Poumon");
                        listeDesOptions.add("Génitaux");
                        listeDesOptions.add("Autre");*/
                        for (int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[5].setVisibility(View.VISIBLE);
                        break;
                    case "Colonne cervicale haute":
                    case "Colonne cervicale basse":
                    case "Colonne dorsale haut D1 à D4":
                    case "Colonne dorsale moyen D5 à D8":
                    case "Colonne dorsale haut D9 à D12":
                    case "Colonne lombaire haut L1 à L3":
                    case "Colonne lombaire haut L4 à L5":
                    case "Épaule":
                    case "Bras":
                    case "Coude":
                    case "Avant-bras":
                    case "Poignet":
                    case "Main":
                    case "Hanche":
                    case "Cuisse":
                    case "Genou":
                    case "Jambe":
                    case "Cheville":
                    case "Pied":
                        /*listeDesOptions.add("Muscles");
                        listeDesOptions.add("Ligaments");
                        listeDesOptions.add("Os");
                        listeDesOptions.add("Nerfs");
                        listeDesOptions.add("Autre");*/
                        for (int i=0; i < mesLinearLayout.length; i++) {
                            mesLinearLayout[i].setVisibility(View.GONE);
                        }
                        mesLinearLayout[2].setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lblInformationsSupplementaires = (TextView) findViewById(R.id.lblInformationsSupplementaires);
        lblInformationsSupplementaires.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblInformationsSupplementaires.setTextColor(Color.BLACK);
        lblContexteDeLaBlessure = (TextView) findViewById(R.id.lblContexteDeLaBlessure);
        lblContexteDeLaBlessure.setBackgroundColor(Color.parseColor("#9999ff"));
        lblContexteDeLaBlessure.setTextColor(Color.BLACK);
        List<String> listeContextes = new ArrayList<>();
        listeContextes.add("Pratique");
        listeContextes.add("Partie");
        listeContextes.add("Condition médicale");
        listeContextes.add("Pratique d'une autre activité sportive");
        ArrayAdapter<String> data = new ArrayAdapter<String>(this, R.layout.spinner_item, listeContextes);
        sContexteDeLaBlessure = (Spinner) findViewById(R.id.sContexteDeLaBlessure);
        sContexteDeLaBlessure.setAdapter(data);
        lblRestrictionAthlete = (TextView) findViewById(R.id.lblRestrictionsAthlete);
        lblRestrictionAthlete.setBackgroundColor(Color.parseColor("#9999ff"));
        lblRestrictionAthlete.setTextColor(Color.BLACK);

        lblSuiviDeLaBlessure = (TextView) findViewById(R.id.lblSuiviDeLaBlessure);
        lblSuiviDeLaBlessure.setBackgroundColor(Color.parseColor("#ffffb2"));
        lblSuiviDeLaBlessure.setTextColor(Color.BLACK);
        lblDescriptionDeLaCondition = (TextView) findViewById(R.id.lblDescriptionDeLaCondition);
        lblDescriptionDeLaCondition.setBackgroundColor(Color.parseColor("#9999ff"));
        lblDescriptionDeLaCondition.setTextColor(Color.BLACK);
        txtDescriptionDeLaCondition = (EditText) findViewById(R.id.txtDescriptionDeLaCondition);
        lblMecanismeDeBlessure = (TextView) findViewById(R.id.lblMecanismeDeBlessure);
        lblMecanismeDeBlessure.setBackgroundColor(Color.parseColor("#9999ff"));
        lblMecanismeDeBlessure.setTextColor(Color.BLACK);
        lblSoapS = (TextView) findViewById(R.id.lblSoapS);
        lblSoapS.setBackgroundColor(Color.parseColor("#9999ff"));
        lblSoapS.setTextColor(Color.BLACK);
        lblSoapO = (TextView) findViewById(R.id.lblSoapO);
        lblSoapO.setBackgroundColor(Color.parseColor("#9999ff"));
        lblSoapO.setTextColor(Color.BLACK);
        lblSoapA = (TextView) findViewById(R.id.lblSoapA);
        lblSoapA.setBackgroundColor(Color.parseColor("#9999ff"));
        lblSoapA.setTextColor(Color.BLACK);
        lblSoapP = (TextView) findViewById(R.id.lblSoapP);
        lblSoapP.setBackgroundColor(Color.parseColor("#9999ff"));
        lblSoapP.setTextColor(Color.BLACK);
        lblAutreCommentaireRecommandation = (TextView) findViewById(R.id.lblAutreCommentaireRecommandation);
        lblAutreCommentaireRecommandation.setBackgroundColor(Color.parseColor("#9999ff"));
        lblAutreCommentaireRecommandation.setTextColor(Color.BLACK);

        btnSauvegarderRapport = (Button) findViewById(R.id.btnSauvegarderRapport);
        btnSauvegarderRapport.setBackgroundColor(Color.parseColor("#ff6666"));
        btnSauvegarderRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable d = getResources().getDrawable(R.drawable.acces_athletique_logo);
                try {
                    RapportAthlete rapportAthlete = new RapportAthlete();
                    rapportAthlete.setNomDuRapport("Rapport athlète du " + dpDateDeLaBlessure.getDayOfMonth() + "_" + dpDateDeLaBlessure.getMonth() + "_" + dpDateDeLaBlessure.getYear());
                    rapportAthlete.setLogoPath(d.toString());
                    rapportAthlete.setNomDuPatient(atxtNomPatient.getText().toString());
                    rapportAthlete.setPrenomDuPatient(atxtPrenomPatient.getText().toString());
                    rapportAthlete.setNomEquipe(atxtNomEquipe.getText().toString());
                    rapportAthlete.setNomEcole(atxtNomEcole.getText().toString());
                    rapportAthlete.setNumeroJoueur(atxtNumeroJoueur.getText().toString());
                    rapportAthlete.setTypeEvenement(sTypeEvenement.getSelectedItem().toString());
                    rapportAthlete.setDateDeLaBlessure(dpDateDeLaBlessure.getDayOfMonth() + "/" + dpDateDeLaBlessure.getMonth() + "/" + dpDateDeLaBlessure.getYear());
                    rapportAthlete.setDateRetourEntrainement(dpDateDeRetourEntrainement.getDayOfMonth() + "/" + dpDateDeRetourEntrainement.getMonth() + "/" + dpDateDeRetourEntrainement.getYear());
                    rapportAthlete.setDateRetourJeu(dpDateRetourJeu.getDayOfMonth() + "/" + dpDateRetourJeu.getMonth() + "/" + dpDateRetourJeu.getYear());
                    rapportAthlete.setRegionAffectee(sRegionAffectee.getSelectedItem().toString());
                    rapportAthlete.setPrecisionRegionAffectee(sPrecisionSurLaRegionAffectee.getSelectedItem().toString());
                    List<String> liste = new ArrayList<String>();
                    for(int i=0; i < checkBoxes.length; i++) {
                        if(checkBoxes[i].isChecked()) {
                            liste.add(checkBoxes[i].getText().toString());
                        }
                    }
                    rapportAthlete.setListeDescriptionDetailleeRegionAffectee(liste);
                    rapportAthlete.setContexte(sContexteDeLaBlessure.getSelectedItem().toString());
                    List<String> listeRestriction = new ArrayList<String>();
                    for(int i=30; i < checkBoxes.length; i++) {
                        if(checkBoxes[i].isChecked()) {
                            listeRestriction.add(checkBoxes[i].getText().toString());
                        }
                    }
                    rapportAthlete.setListeRestrictions(listeRestriction);
                    rapportAthlete.setDescriptionDeLaCondition(txtDescriptionDeLaCondition.getText().toString());
                    txtMecanismeDeBlessure = (EditText) findViewById(R.id.txtMecanismeDeBlessure);
                    rapportAthlete.setMecanismeDeBlessure(txtMecanismeDeBlessure.getText().toString());
                    TextView txtSoapS = (TextView) findViewById(R.id.txtSoapS);
                    rapportAthlete.setSOAPS(txtSoapS.getText().toString());
                    TextView txtSoapO = (TextView) findViewById(R.id.txtSoapO);
                    rapportAthlete.setSOAPO(txtSoapO.getText().toString());
                    TextView txtSoapA = (TextView) findViewById(R.id.txtSoapA);
                    rapportAthlete.setSOAPA(txtSoapA.getText().toString());
                    TextView txtSoapP = (TextView) findViewById(R.id.txtSoapP);
                    rapportAthlete.setSOAPP(txtSoapP.getText().toString());
                    TextView txtAutreCommentaire = (TextView) findViewById(R.id.txtAutreCommentaireRecommandation);
                    rapportAthlete.setAutreCommentaire(txtAutreCommentaire.getText().toString());
                    rapportAthlete.creeRapport();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                RapportTherapeute rapportTherapeute = new RapportTherapeute();
                rapportTherapeute.setNomRapport("Rapport thérapeute du " + dpDateDeLaBlessure.getDayOfMonth() + "_" + dpDateDeLaBlessure.getMonth() + "_" + dpDateDeLaBlessure.getYear());
                rapportTherapeute.setLogoPath(d.toString());
                rapportTherapeute.setNomEcole(atxtNomEcole.getText().toString());
                rapportTherapeute.setNomEquipe(atxtNomEquipe.getText().toString());
                rapportTherapeute.setDateEvenement(dpDateDeLaBlessure.getDayOfMonth() + "/" + dpDateDeLaBlessure.getMonth() + "/" + dpDateDeLaBlessure.getYear());
                List<String> liste = new ArrayList<String>();
                liste = rapportTherapeute.getUneLigne();
                liste.add(atxtNomPatient.getText().toString().toUpperCase() + ", " + atxtPrenomPatient.getText().toString());
                liste.add(dpDateDeLaBlessure.getDayOfMonth() + "/" + dpDateDeLaBlessure.getMonth() + "/" + dpDateDeLaBlessure.getYear());
                liste.add(dpDateDeRetourEntrainement.getDayOfMonth() + "/" + dpDateDeRetourEntrainement.getMonth() + "/" + dpDateDeRetourEntrainement.getYear());
                liste.add(dpDateRetourJeu.getDayOfMonth() + "/" + dpDateRetourJeu.getMonth() + "/" + dpDateRetourJeu.getYear());
                liste.add(sRegionAffectee.getSelectedItem().toString());
                liste.add(sPrecisionSurLaRegionAffectee.getSelectedItem().toString());
                String descriptionDetaillee = "";
                for(int i=0; i < checkBoxes.length; i++) {
                    if(checkBoxes[i].isChecked()) {
                        descriptionDetaillee += checkBoxes[i].getText() + ", ";
                    }
                }
                liste.add(descriptionDetaillee);
                TextView soapA = (TextView) findViewById(R.id.txtSoapA);
                liste.add(soapA.getText().toString());
                TextView txtCommentaire = (TextView) findViewById(R.id.txtAutreCommentaireRecommandation);
                liste.add(txtCommentaire.getText().toString());
                rapportTherapeute.creerRapport();
                Toast toast = Toast.makeText(CreerUneBlessureActivity.this, "Les rapports ont été générés.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
            }
        });
        /*Toast toast = Toast.makeText(this, "View.GONE = " + mesLinearLayout[5].getVisibility() + "\nView.VISIBLE = " + mesLinearLayout[0].getVisibility(), Toast.LENGTH_LONG);
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(36);
        toast.show();*/
    }
}