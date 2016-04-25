package www.samnang_alex.com.applicationaccesathletique.models;

public class Restriction {

    private int id;
    private int pratiqueAvecRestriction;
    private int partieAvecRestriction;
    private int aucunePratique;
    private int aucunePartie;
    private int aucuneRestriction;

    public Restriction() { }
    public int getAucunePartie() {return aucunePartie;}
    public void setAucunePartie(int aucunePartie) {this.aucunePartie = aucunePartie;}
    public int getAucunePratique() {return aucunePratique;}
    public void setAucunePratique(int aucunePratique) {this.aucunePratique = aucunePratique;}
    public int getAucuneRestriction() {return aucuneRestriction;}
    public void setAucuneRestriction(int aucuneRestriction) {this.aucuneRestriction = aucuneRestriction;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getPartieAvecRestriction() {return partieAvecRestriction;}
    public void setPartieAvecRestriction(int partieAvecRestriction) {this.partieAvecRestriction = partieAvecRestriction;}
    public int getPratiqueAvecRestriction() {return pratiqueAvecRestriction; }
    public void setPratiqueAvecRestriction(int pratiqueAvecRestriction) { this.pratiqueAvecRestriction = pratiqueAvecRestriction; }
}