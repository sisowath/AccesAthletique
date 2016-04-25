package www.samnang_alex.com.applicationaccesathletique.models;

public class Blessure {

    private int id;
    private int idAthlete;
    private int idEvenement;
    private int jourtRetourEntrainement;
    private int moisRetourEntrainement;
    private int anneeRetourEntrainement;
    private int jourRetourJeu;
    private int moisRetourJeu;
    private int anneeRetourJeu;
    private String membreAffecte;
    private String precisionMembre;
    private int idRaffinementMembre;
    private String contexte;
    private int idRestriction;
    private String descriptionCondition;
    private String mecanismeBlessure;
    private String soapS;
    private String soapO;
    private String soapA;
    private String soapP;
    private String commentaire;

    public Blessure() { }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getAnneeRetourEntrainement() {return anneeRetourEntrainement; }
    public void setAnneeRetourEntrainement(int anneeRetourEntrainement) { this.anneeRetourEntrainement = anneeRetourEntrainement; }
    public int getAnneeRetourJeu() { return anneeRetourJeu; }
    public void setAnneeRetourJeu(int anneeRetourJeu) {this.anneeRetourJeu = anneeRetourJeu; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public String getContexte() { return contexte; }
    public void setContexte(String contexte) { this.contexte = contexte; }
    public String getDescriptionCondition() { return descriptionCondition; }
    public void setDescriptionCondition(String descriptionCondition) { this.descriptionCondition = descriptionCondition; }
    public int getIdAthlete() { return idAthlete; }
    public void setIdAthlete(int idAthlete) { this.idAthlete = idAthlete; }
    public int getIdEvenement() { return idEvenement; }
    public void setIdEvenement(int idEvenement) { this.idEvenement = idEvenement; }
    public int getIdRestriction() { return idRestriction; }
    public void setIdRestriction(int idRestriction) { this.idRestriction = idRestriction; }
    public int getJourRetourJeu() { return jourRetourJeu; }
    public void setJourRetourJeu(int jourRetourJeu) { this.jourRetourJeu = jourRetourJeu; }
    public int getJourtRetourEntrainement() { return jourtRetourEntrainement; }
    public void setJourtRetourEntrainement(int jourtRetourEntrainement) { this.jourtRetourEntrainement = jourtRetourEntrainement; }
    public String getMecanismeBlessure() { return mecanismeBlessure; }
    public void setMecanismeBlessure(String mecanismeBlessure) { this.mecanismeBlessure = mecanismeBlessure; }
    public String getMembreAffecte() { return membreAffecte; }
    public void setMembreAffecte(String membreAffecte) { this.membreAffecte = membreAffecte; }
    public int getMoisRetourEntrainement() { return moisRetourEntrainement; }
    public void setMoisRetourEntrainement(int moisRetourEntrainement) { this.moisRetourEntrainement = moisRetourEntrainement; }
    public int getMoisRetourJeu() { return moisRetourJeu; }
    public void setMoisRetourJeu(int moisRetourJeu) { this.moisRetourJeu = moisRetourJeu; }
    public String getPrecisionMembre() { return precisionMembre; }
    public void setPrecisionMembre(String precisionMembre) { this.precisionMembre = precisionMembre; }
    public int getIdRaffinementMembre() { return idRaffinementMembre; }
    public void setIdRaffinementMembre(int idRaffinementMembre) { this.idRaffinementMembre = idRaffinementMembre; }
    public String getSoapA() { return soapA; }
    public void setSoapA(String soapA) { this.soapA = soapA; }
    public String getSoapO() { return soapO; }
    public void setSoapO(String soapO) { this.soapO = soapO; }
    public String getSoapP() { return soapP; }
    public void setSoapP(String soapP) { this.soapP = soapP; }
    public String getSoapS() { return soapS; }
    public void setSoapS(String soapS) { this.soapS = soapS; }
}