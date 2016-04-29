package www.samnang_alex.com.applicationaccesathletique.models;

import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import www.samnang_alex.com.applicationaccesathletique.R;

public class RapportTherapeute {

    public static final Font FONTENTETE = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
    public static final Font FONTENTETENORMAL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
    public static final Font colonneHeader = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
    public static final Font FONTCOURRIEL = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE);
    public static final Font FONTNORMAL = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
    public static final Font FONTNORMALUNDERLINE = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK);

    private String nomRapport;
    private String logoPath;
    // En-tête
    private String nomEcole;
    private String nomEquipe;
    private String dateEvenement;
    //Tableau
    private List<String> uneLigne = new ArrayList();

    public RapportTherapeute() { }
    public RapportTherapeute(TableRapportTherapeute tableRapportTherapeute) {
        this.nomEcole = tableRapportTherapeute.getNomEcole();
        this.nomEquipe = tableRapportTherapeute.getNomEquipe();
        this.dateEvenement = tableRapportTherapeute.getJourEvenement() + "/" + tableRapportTherapeute.getMoisEvenement() + "/" + tableRapportTherapeute.getAnneeEvenement();
        this.uneLigne = new ArrayList<>();
        this.uneLigne.add(tableRapportTherapeute.getNomPatient().toUpperCase() + ", " + tableRapportTherapeute.getPrenomPatient().toLowerCase());
        this.uneLigne.add(tableRapportTherapeute.getJourBlessure() + "/" + tableRapportTherapeute.getMoisBlessure() + "/" + tableRapportTherapeute.getAnneeBlessure());
        this.uneLigne.add(tableRapportTherapeute.getJourRetourEntrainement() + "/" + tableRapportTherapeute.getMoisRetourEntrainement() + "/" + tableRapportTherapeute.getAnneeRetourEntrainement());
        this.uneLigne.add(tableRapportTherapeute.getJourRetourJeu() + "/" + tableRapportTherapeute.getMoisRetourJeu() + "/" + tableRapportTherapeute.getAnneeRetourJeu());
        this.uneLigne.add(tableRapportTherapeute.getMembreAffecte());
        this.uneLigne.add(tableRapportTherapeute.getPrecisionMembre());
        this.uneLigne.add(tableRapportTherapeute.getRaffinementMembre());
        this.uneLigne.add(tableRapportTherapeute.getSoapA());
        this.uneLigne.add(tableRapportTherapeute.getCommentaire());
        this.nomRapport = tableRapportTherapeute.getJourBlessure() + "_" + tableRapportTherapeute.getMoisBlessure() + "_" + tableRapportTherapeute.getAnneeBlessure() + "_Rapport Thérapeute";
    }

    public String getNomRapport() { return nomRapport; }
    public void setNomRapport(String nomRapport) { this.nomRapport = nomRapport; }
    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }
    public String getNomEcole() { return nomEcole; }
    public void setNomEcole(String nomEcole) { this.nomEcole = nomEcole; }
    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }
    public String getDateEvenement() { return dateEvenement; }
    public void setDateEvenement(String dateEvenement) { this.dateEvenement = dateEvenement; }
    public List<String> getUneLigne() { return uneLigne; }
    public void setUneLigne(List<String> uneLigne) { this.uneLigne = uneLigne; }

    public void creerRapport() {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.LETTER.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + this.nomRapport + ".pdf"));
            document.open();

            PdfPTable tableHeader = new PdfPTable(2);

            PdfPCell colonneIcone = new PdfPCell();
            Image accesAthletiqueLogo = Image.getInstance(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/acces_athletique_logo.png");
            accesAthletiqueLogo.setAbsolutePosition(50, 500);
            document.add(accesAthletiqueLogo);
            colonneIcone.addElement(new Chunk(""));
            colonneIcone.setBorder(0);

            PdfPCell colonneDetails = new PdfPCell();
            Paragraph pRapportDeBlessure = new Paragraph(new Chunk("Rapport de blessure", FONTENTETE));
            colonneDetails.addElement(pRapportDeBlessure);
            Paragraph paragraphNomEcole = new Paragraph(new Chunk("Nom de l'école : ", FONTENTETENORMAL));
            paragraphNomEcole.add(new Chunk(this.nomEcole, FONTNORMALUNDERLINE));
            colonneDetails.addElement(paragraphNomEcole);
            Paragraph paragraphNomEquipe = new Paragraph(new Chunk("Nom de l'équipe : ", FONTENTETENORMAL));
            paragraphNomEquipe.add(new Chunk(this.nomEquipe, FONTNORMALUNDERLINE));
            colonneDetails.addElement(paragraphNomEquipe);
            paragraphNomEquipe.add(new Chunk(this.nomEquipe, FONTNORMALUNDERLINE));
            Paragraph paragraphDateEvenement = new Paragraph(new Chunk("Date de l'évènement : ", FONTENTETENORMAL));
            paragraphDateEvenement.add(new Chunk(this.dateEvenement, FONTNORMALUNDERLINE));
            colonneDetails.addElement(paragraphDateEvenement);
            colonneDetails.setBorder(0);

            tableHeader.addCell(colonneIcone);
            tableHeader.addCell(colonneDetails);

            PdfPTable tableDesBlessures = new PdfPTable(9);

            PdfPCell colonneNomDuPatient = new PdfPCell();
            colonneNomDuPatient.addElement(new Chunk("Nom du patient", colonneHeader));

            PdfPCell colonneDateDeLaBlessure = new PdfPCell();
            colonneDateDeLaBlessure.addElement(new Chunk("Date de la\nblessure", colonneHeader));

            PdfPCell colonneDateDeRetourAEntrainement = new PdfPCell();
            colonneDateDeRetourAEntrainement.addElement(new Chunk("Date de retour à\nl'entrainement", colonneHeader));

            PdfPCell colonneDateDeRetourAuJeu = new PdfPCell();
            colonneDateDeRetourAuJeu.addElement(new Chunk("Date de\nretour au jeu", colonneHeader));

            PdfPCell colonneMembreAffecte = new PdfPCell();
            colonneMembreAffecte.addElement(new Chunk("Membre\naffecté", colonneHeader));

            PdfPCell colonnePrecisionSurLeMembre = new PdfPCell();
            colonnePrecisionSurLeMembre.addElement(new Chunk("Précision sur\nle membre", colonneHeader));

            PdfPCell colonneRaffinementSurLeMembre = new PdfPCell();
            colonneRaffinementSurLeMembre.addElement(new Chunk("Raffinement\nsur le membre", colonneHeader));

            PdfPCell colonneSOAPA = new PdfPCell();
            colonneSOAPA.addElement(new Chunk("SO(A)P", colonneHeader));

            PdfPCell colonneCommentaireRecommandation = new PdfPCell();
            colonneCommentaireRecommandation.addElement(new Chunk("Commentaire/\nRecommandation", colonneHeader));

            tableDesBlessures.addCell(colonneNomDuPatient);
            tableDesBlessures.addCell(colonneDateDeLaBlessure);
            tableDesBlessures.addCell(colonneDateDeRetourAEntrainement);
            tableDesBlessures.addCell(colonneDateDeRetourAuJeu);
            tableDesBlessures.addCell(colonneMembreAffecte);
            tableDesBlessures.addCell(colonnePrecisionSurLeMembre);
            tableDesBlessures.addCell(colonneRaffinementSurLeMembre);
            tableDesBlessures.addCell(colonneSOAPA);
            tableDesBlessures.addCell(colonneCommentaireRecommandation);

            /* Ajouter une nouvelle ligne
            PdfPCell ligne2 = new PdfPCell();
            ligne2.addElement(new Chunk("nouvelle ligne", FONTNORMAL));
            ligne2.setColspan(9);
            tableDesBlessures.addCell(ligne2);
            Source de :  http://stackoverflow.com/questions/4891578/a-new-row-in-pdfptable */

            PdfPCell[] uneLigne = new PdfPCell[9];
            for(int i=0; i < this.uneLigne.size(); i++) {
                uneLigne[i%9] = new PdfPCell();
                uneLigne[i%9].addElement(new Chunk(this.uneLigne.get(i), FONTNORMAL));
                tableDesBlessures.addCell(uneLigne[i%9]);
            }

            tableDesBlessures.setWidthPercentage(100);
            tableDesBlessures.setSpacingBefore(25);

            PdfPTable tableCoordonneesEntreprise = new PdfPTable(2);

            PdfPCell colonneCoordonnes = new PdfPCell();
            Paragraph coordonnes = new Paragraph(new Chunk("1587, Rainaud, Montréal, Qc\nH1A 4L5 - 514.975.5683", FONTNORMAL));
            coordonnes.setAlignment(Element.ALIGN_LEFT);
            colonneCoordonnes.addElement(coordonnes);
            colonneCoordonnes.addElement(new Chunk("Valérie Janelle", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLUE)));
            colonneCoordonnes.setBorder(0);

            PdfPCell colonneCourriels = new PdfPCell();
            Paragraph siteweb = new Paragraph(new Chunk("www.accesathletique.com", FONTCOURRIEL));
            siteweb.setAlignment(Element.ALIGN_RIGHT);
            colonneCourriels.addElement(siteweb);
            Paragraph courriel = new Paragraph(new Chunk("info@accesathletique.com", FONTCOURRIEL));
            courriel.setAlignment(Element.ALIGN_RIGHT);
            colonneCourriels.addElement(courriel);
            colonneCourriels.setBorder(0);

            tableCoordonneesEntreprise.addCell(colonneCoordonnes);
            tableCoordonneesEntreprise.addCell(colonneCourriels);
            tableCoordonneesEntreprise.setWidthPercentage(100);

            document.add(tableHeader);
            document.add(tableDesBlessures);
            document.add(tableCoordonneesEntreprise);

            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(RapportTherapeute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RapportTherapeute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RapportTherapeute.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}