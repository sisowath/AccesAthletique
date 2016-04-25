package www.samnang_alex.com.applicationaccesathletique.models;

public class Ecole {

    private int id;
    private String nom;

    public Ecole() { }
    public Ecole(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() { return this.id + " :: " + this.nom; }
}