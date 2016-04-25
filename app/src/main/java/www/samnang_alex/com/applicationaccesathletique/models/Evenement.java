package www.samnang_alex.com.applicationaccesathletique.models;

public class Evenement {

    private int id;
    private String type;
    private int jour;
    private int mois;
    private int annee;

    public Evenement() { }
    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getJour() { return jour; }
    public void setJour(int jour) { this.jour = jour; }
    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}