package www.samnang_alex.com.applicationaccesathletique.models;

import android.os.Environment;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import www.samnang_alex.com.applicationaccesathletique.R;

public class RapportAthlete {

    public static final float MARGIN_LEFT = (float) (72*0.5);
    public static final float MARGIN_RIGHT = (float) (72*0.5);
    public static final float MARGIN_TOP = 18;
    public static final float MARGIN_BOTTOM = 18;
    public static final Font BOLD_UNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE);
    public static final Font mesTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLACK);
    public static final Font mesSousTitres = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
    public static final Font FONTNORMALUNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK);
    public static final Font FONTNORMAL = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);

    private String nomDuRapport;
    private String logoPath;
    // Coordonnées du patient
    private String nomDuPatient;
    private String prenomDuPatient;
    private String nomEquipe;
    private String nomEcole;
    private String numeroJoueur;
    // Date de l'évènement
    private String typeEvenement;
    private String dateDeLaBlessure;
    private String dateRetourEntrainement;
    private String dateRetourJeu;
    // Description de la blessure
    private String regionAffectee;
    private String precisionRegionAffectee;
    private List<String> listeDescriptionDetailleeRegionAffectee;
    private String contexte;
    private List<String> listeRestrictions;
    // Suivi de la blessure
    private String descriptionDeLaCondition;
    private String mecanismeDeBlessure;
    private String SOAPS;
    private String SOAPO;
    private String SOAPA;
    private String SOAPP;
    private String autreCommentaire;

    public RapportAthlete(TableRapportAthlete tableRapportAthlete) {
        this.nomDuPatient = tableRapportAthlete.getNomAthlete();
        this.prenomDuPatient = tableRapportAthlete.getPrenomAthlete();
        this.nomEquipe = tableRapportAthlete.getNomEquipe();
        this.nomEcole = tableRapportAthlete.getNomEcole();
        this.numeroJoueur = String.valueOf(tableRapportAthlete.getNumeroJoueur());
        this.dateDeLaBlessure = tableRapportAthlete.getJourBlessure() + "/" + tableRapportAthlete.getMoisBlessure() + "/" + tableRapportAthlete.getAnneeBlessure();
        this.typeEvenement = tableRapportAthlete.getTypeEvenement();
        this.dateRetourEntrainement = tableRapportAthlete.getJourRetourEntrainement() + "/" + tableRapportAthlete.getMoisRetourEntrainement() + "/" + tableRapportAthlete.getAnneeRetourEntrainement();
        this.dateRetourJeu = tableRapportAthlete.getJourRetourJeu() + "/" + tableRapportAthlete.getMoisRetourJeu() + "/" + tableRapportAthlete.getAnneeRetourJeu();
        this.regionAffectee = tableRapportAthlete.getMembreAffecte();
        this.precisionRegionAffectee = tableRapportAthlete.getPrecisionMembre();
        this.listeDescriptionDetailleeRegionAffectee = new ArrayList<>();
        this.listeDescriptionDetailleeRegionAffectee.add( tableRapportAthlete.getRaffinementMembre() );
        this.contexte = tableRapportAthlete.getContexte();
        this.listeRestrictions = new ArrayList<>();
        this.listeRestrictions.add(tableRapportAthlete.getRestriction());
        this.descriptionDeLaCondition = tableRapportAthlete.getDescriptionCondition();
        this.mecanismeDeBlessure = tableRapportAthlete.getMecanismeBlessure();
        this.SOAPS = tableRapportAthlete.getSoapS();
        this.SOAPO = tableRapportAthlete.getSoapO();
        this.SOAPA = tableRapportAthlete.getSoapA();
        this.SOAPP = tableRapportAthlete.getSoapP();
        this.autreCommentaire = tableRapportAthlete.getCommentaire();
        this.nomDuRapport = tableRapportAthlete.getJourBlessure() + "_" + tableRapportAthlete.getMoisBlessure() + "_" + tableRapportAthlete.getAnneeBlessure() + "_Rapport Athète " + tableRapportAthlete.getNomAthlete().toUpperCase() + " - " + tableRapportAthlete.getPrenomAthlete().toLowerCase();
    }

    public String getNomDuRapport() { return nomDuRapport; }
    public void setNomDuRapport(String nomDuRapport) { this.nomDuRapport = nomDuRapport; }
    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }
    public String getNomDuPatient() { return nomDuPatient; }
    public void setNomDuPatient(String nomDuPatient) { this.nomDuPatient = nomDuPatient; }
    public String getPrenomDuPatient() { return prenomDuPatient; }
    public void setPrenomDuPatient(String prenomDuPatient) { this.prenomDuPatient = prenomDuPatient; }
    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }
    public String getNomEcole() { return nomEcole; }
    public void setNomEcole(String nomEcole) { this.nomEcole = nomEcole; }
    public String getNumeroJoueur() { return numeroJoueur; }
    public void setNumeroJoueur(String numeroJoueur) { this.numeroJoueur = numeroJoueur; }
    public String getTypeEvenement() { return typeEvenement; }
    public void setTypeEvenement(String typeEvenement) { this.typeEvenement = typeEvenement; }
    public String getDateDeLaBlessure() { return dateDeLaBlessure; }
    public void setDateDeLaBlessure(String dateDeLaBlessure) { this.dateDeLaBlessure = dateDeLaBlessure; }
    public String getDateRetourEntrainement() { return dateRetourEntrainement; }
    public void setDateRetourEntrainement(String dateRetourEntrainement) { this.dateRetourEntrainement = dateRetourEntrainement; }
    public String getDateRetourJeu() { return dateRetourJeu; }
    public void setDateRetourJeu(String dateRetourJeu) { this.dateRetourJeu = dateRetourJeu; }
    public String getRegionAffectee() { return regionAffectee; }
    public void setRegionAffectee(String regionAffectee) { this.regionAffectee = regionAffectee; }
    public String getPrecisionRegionAffectee() { return precisionRegionAffectee; }
    public void setPrecisionRegionAffectee(String precisionRegionAffectee) { this.precisionRegionAffectee = precisionRegionAffectee; }
    public List<String> getListeDescriptionDetailleeRegionAffectee() { return listeDescriptionDetailleeRegionAffectee; }
    public void setListeDescriptionDetailleeRegionAffectee(List<String> listeDescriptionDetailleeRegionAffectee) { this.listeDescriptionDetailleeRegionAffectee = listeDescriptionDetailleeRegionAffectee; }
    public String getContexte() { return contexte; }
    public void setContexte(String contexte) { this.contexte = contexte; }
    public List<String> getListeRestrictions() { return listeRestrictions; }
    public void setListeRestrictions(List<String> listeRestrictions) { this.listeRestrictions = listeRestrictions; }
    public String getDescriptionDeLaCondition() { return descriptionDeLaCondition; }
    public void setDescriptionDeLaCondition(String descriptionDeLaCondition) { this.descriptionDeLaCondition = descriptionDeLaCondition; }
    public String getMecanismeDeBlessure() { return mecanismeDeBlessure; }
    public void setMecanismeDeBlessure(String mecanismeDeBlessure) { this.mecanismeDeBlessure = mecanismeDeBlessure; }
    public String getSOAPS() { return SOAPS; }
    public void setSOAPS(String SOAPS) { this.SOAPS = SOAPS; }
    public String getSOAPO() { return SOAPO; }
    public void setSOAPO(String SOAPO) { this.SOAPO = SOAPO; }
    public String getSOAPA() { return SOAPA; }
    public void setSOAPA(String SOAPA) { this.SOAPA = SOAPA; }
    public String getSOAPP() { return SOAPP; }
    public void setSOAPP(String SOAPP) { this.SOAPP = SOAPP; }
    public String getAutreCommentaire() { return autreCommentaire; }
    public void setAutreCommentaire(String autreCommentaire) { this.autreCommentaire = autreCommentaire; }

    public void creeRapport() throws DocumentException, FileNotFoundException, BadElementException, IOException {
        //Document document = new Document();
        Document document = new Document(new Rectangle(8*72, 11*72), MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);
        //Document document = new Document(PageSize.LETTER);// orientation portrait
        //document.setPageSize(PageSize.LETTER.rotate());// orientation paysage
        PdfWriter pDfWriter = PdfWriter.getInstance(document, new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + this.nomDuRapport + ".pdf"));
        document.open();
        Image accesAthletiqueLogo = Image.getInstance(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/acces_athletique_logo.png");
        document.add(accesAthletiqueLogo);
        Phrase phraseCoordonneesDuPatient = new Phrase();
        phraseCoordonneesDuPatient.add(new Chunk("\nCoordonnées du patient", mesTitres));
        document.add(phraseCoordonneesDuPatient);
        document.add(new Chunk("\nNom du patient : ", mesSousTitres));
        document.add(new Chunk(this.nomDuPatient + ", " + this.prenomDuPatient, FONTNORMALUNDERLINED));
        document.add(new Chunk("\nÉquipe : ", mesSousTitres));
        document.add(new Chunk(this.nomEquipe, FONTNORMALUNDERLINED));
        document.add(new Chunk("\nÉcole : ", mesSousTitres));
        document.add(new Chunk(this.nomEcole, FONTNORMALUNDERLINED));

        Paragraph paragraphNumeroDuJoueur = new Paragraph(new Chunk("Numéro du joueur : ", mesSousTitres));
        paragraphNumeroDuJoueur.add(new Chunk(this.numeroJoueur, FONTNORMALUNDERLINED));
        paragraphNumeroDuJoueur.setSpacingBefore(0f);
        paragraphNumeroDuJoueur.setSpacingAfter(10f);
        document.add(paragraphNumeroDuJoueur);

        PdfPTable pdfPTable = new PdfPTable(2);
        PdfPCell pdfPCell01 = new PdfPCell();
        pdfPCell01.addElement(new Chunk("Date de l'événement", mesTitres));
        Paragraph paragraphDateDeLaBlessure = new Paragraph(new Chunk("Date de la blessure : ", mesSousTitres));
        paragraphDateDeLaBlessure.add(new Chunk(this.dateDeLaBlessure, FONTNORMALUNDERLINED));
        pdfPCell01.addElement(paragraphDateDeLaBlessure);
        Paragraph paragraphDateRetourEntrainement = new Paragraph(new Chunk("Date de retour à l'entraînement: ", mesSousTitres));
        paragraphDateRetourEntrainement.add(new Chunk(this.dateRetourEntrainement, FONTNORMALUNDERLINED));
        pdfPCell01.addElement(paragraphDateRetourEntrainement);
        Paragraph paragraphDateRetourJeu = new Paragraph(new Chunk("Date de retour au jeu : ", mesSousTitres));
        paragraphDateRetourJeu.add(new Chunk(this.dateRetourJeu, FONTNORMALUNDERLINED));
        pdfPCell01.addElement(paragraphDateRetourJeu);
        pdfPCell01.setBorder(0);
        PdfPCell pdfPCell11 = new PdfPCell();
        pdfPCell11.addElement(new Chunk("Description de la blessure", mesTitres));
        Paragraph paragraphMembreAffecte = new Paragraph(new Chunk("Membre affecté : ", mesSousTitres));
        paragraphMembreAffecte.add(new Chunk(this.regionAffectee, FONTNORMALUNDERLINED));
        pdfPCell11.addElement(paragraphMembreAffecte);
        Paragraph paragraphPrecisionMembre = new Paragraph(new Chunk("Précision sur le membre : ", mesSousTitres));
        paragraphPrecisionMembre.add(new Chunk(this.precisionRegionAffectee, FONTNORMALUNDERLINED));
        pdfPCell11.addElement(paragraphPrecisionMembre);
        Paragraph paragraphRaffinementMembre = new Paragraph(new Chunk("Raffinement sur le membre : ", mesSousTitres));
        paragraphRaffinementMembre.add(new Chunk(this.listeDescriptionDetailleeRegionAffectee.toString(), FONTNORMALUNDERLINED));
        pdfPCell11.addElement(paragraphRaffinementMembre);
        pdfPCell11.setBorder(0);
        pdfPTable.addCell(pdfPCell01);
        pdfPTable.addCell(pdfPCell11);
        pdfPTable.setWidthPercentage(100);
        document.add(pdfPTable);

        document.add(new Chunk("\nSuivi de la blessure", mesTitres));
        Paragraph paragraphDescriptionDeLaCondition = new Paragraph(new Chunk("Description de la condition", mesSousTitres));
        paragraphDescriptionDeLaCondition.setSpacingAfter(5f);
        document.add(paragraphDescriptionDeLaCondition);
        PdfPTable pdfPTableDescriptionDeLaCondition = new PdfPTable(1);
        PdfPCell pdfPCellDescriptionDeLaCondition = new PdfPCell();
        pdfPCellDescriptionDeLaCondition.addElement(new Chunk(this.descriptionDeLaCondition, FONTNORMAL));
        pdfPCellDescriptionDeLaCondition.setFixedHeight(40);
        pdfPTableDescriptionDeLaCondition.addCell(pdfPCellDescriptionDeLaCondition);
        pdfPTableDescriptionDeLaCondition.setWidthPercentage(100);
        document.add(pdfPTableDescriptionDeLaCondition);

        Paragraph paragraphMecanismeDeBlessure = new Paragraph(new Chunk("Mécanisme de blessure", mesSousTitres));
        paragraphMecanismeDeBlessure.setSpacingBefore(10f);
        paragraphMecanismeDeBlessure.setSpacingAfter(5f);
        document.add(paragraphMecanismeDeBlessure);
        PdfPTable pdfPTableMecanismeDeBlessure = new PdfPTable(1);
        PdfPCell pdfPCellMecanismeDeBlessure = new PdfPCell();
        pdfPCellMecanismeDeBlessure.addElement(new Chunk(this.mecanismeDeBlessure, FONTNORMAL));
        pdfPCellMecanismeDeBlessure.setFixedHeight(40);
        pdfPTableMecanismeDeBlessure.addCell(pdfPCellMecanismeDeBlessure);
        pdfPTableMecanismeDeBlessure.setWidthPercentage(100);
        document.add(pdfPTableMecanismeDeBlessure);

        Paragraph paragraphSOAP = new Paragraph(new Chunk("SOAP", mesTitres));
        paragraphSOAP.setSpacingBefore(0f);
        paragraphSOAP.setSpacingAfter(0f);
        document.add(paragraphSOAP);
        Paragraph paragraphS = new Paragraph(new Chunk("S", mesSousTitres));
        paragraphS.setSpacingBefore(0f);
        paragraphS.setSpacingAfter(5f);
        document.add(paragraphS);
        PdfPTable pdfPTableS = new PdfPTable(1);
        PdfPCell pdfPCellS = new PdfPCell();
        pdfPCellS.addElement(new Chunk(this.SOAPS, FONTNORMAL));
        pdfPCellS.setFixedHeight(20);
        pdfPTableS.addCell(pdfPCellS);
        pdfPTableS.setWidthPercentage(100);
        document.add(pdfPTableS);
        Paragraph paragraphO = new Paragraph(new Chunk("O", mesSousTitres));
        paragraphO.setSpacingBefore(0f);
        paragraphO.setSpacingAfter(5f);
        document.add(paragraphO);
        PdfPTable pdfPTableO = new PdfPTable(1);
        PdfPCell pdfPCellO = new PdfPCell();
        pdfPCellO.addElement(new Chunk(this.SOAPO, FONTNORMAL));
        pdfPCellO.setFixedHeight(20);
        pdfPTableO.addCell(pdfPCellS);
        pdfPTableO.setWidthPercentage(100);
        document.add(pdfPTableO);
        Paragraph paragraphA = new Paragraph(new Chunk("A", mesSousTitres));
        paragraphA.setSpacingBefore(0f);
        paragraphA.setSpacingAfter(5f);
        document.add(paragraphA);
        PdfPTable pdfPTableA = new PdfPTable(1);
        PdfPCell pdfPCellA = new PdfPCell();
        pdfPCellA.setFixedHeight(20);
        pdfPCellA.addElement(new Chunk(this.SOAPA, FONTNORMAL));
        pdfPTableA.addCell(pdfPCellS);
        pdfPTableA.setWidthPercentage(100);
        document.add(pdfPTableA);
        Paragraph paragraphP = new Paragraph(new Chunk("P", mesSousTitres));
        paragraphP.setSpacingAfter(0f);
        paragraphP.setSpacingAfter(5f);
        document.add(paragraphP);
        PdfPTable pdfPTableP = new PdfPTable(1);
        PdfPCell pdfPCellP = new PdfPCell();
        pdfPCellP.addElement(new Chunk(this.SOAPP, FONTNORMAL));
        pdfPCellP.setFixedHeight(20);
        pdfPTableP.addCell(pdfPCellS);
        pdfPTableP.setWidthPercentage(100);
        document.add(pdfPTableP);

        Paragraph paragraphAutreCommentaireRecommandation = new Paragraph(new Chunk("Autre commentaire/Recommandation", mesTitres));
        paragraphAutreCommentaireRecommandation.setSpacingAfter(0f);
        paragraphAutreCommentaireRecommandation.setSpacingAfter(5f);
        document.add(paragraphAutreCommentaireRecommandation);
        PdfPTable pdfPTableAutreCommentaireRecommandation = new PdfPTable(1);
        PdfPCell pdfPCellAutreCommentaireRecommandation = new PdfPCell();
        pdfPCellAutreCommentaireRecommandation.addElement(new Chunk(this.autreCommentaire, FONTNORMAL));
        pdfPCellAutreCommentaireRecommandation.setFixedHeight(40);
        pdfPTableAutreCommentaireRecommandation.addCell(pdfPCellAutreCommentaireRecommandation);
        pdfPTableAutreCommentaireRecommandation.setWidthPercentage(100);
        pdfPTableAutreCommentaireRecommandation.setSpacingBefore(0f);
        document.add(pdfPTableAutreCommentaireRecommandation);

        PdfPTable pdfPTableCoordonneesEntreprise = new PdfPTable(2);
        PdfPCell pdfPCellAdresse = new PdfPCell();
        pdfPCellAdresse.addElement(new Chunk("Coordonnées de l'entreprise", mesTitres));
        pdfPCellAdresse.addElement(new Chunk("Valérie Janelle", mesSousTitres));
        pdfPCellAdresse.addElement(new Chunk("1587, Rainaud, Montréal, QC\nH1A 4L5 - 514.975-5683", mesSousTitres));
        pdfPCellAdresse.setBorder(0);
        PdfPCell pdfPCellAdresseCourriel = new PdfPCell();
        Paragraph paragraphSite = new Paragraph(new Chunk("www.accesathletique.com", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC | Font.UNDERLINE, BaseColor.BLUE)));
        paragraphSite.setAlignment(Element.ALIGN_RIGHT);
        pdfPCellAdresseCourriel.addElement(paragraphSite);
        Paragraph paragraphCourriel = new Paragraph(new Chunk("info@accesathlétique.com", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC | Font.UNDERLINE, BaseColor.BLUE)));
        paragraphCourriel.setAlignment(Element.ALIGN_RIGHT);
        pdfPCellAdresseCourriel.addElement(paragraphCourriel);
        pdfPCellAdresseCourriel.setBorder(0);
        pdfPTableCoordonneesEntreprise.addCell(pdfPCellAdresse);
        pdfPTableCoordonneesEntreprise.addCell(pdfPCellAdresseCourriel);
        pdfPTableCoordonneesEntreprise.setWidthPercentage(100);
        pdfPTableCoordonneesEntreprise.setSpacingBefore(10f);
        document.add(pdfPTableCoordonneesEntreprise);

        document.close();
    }
}