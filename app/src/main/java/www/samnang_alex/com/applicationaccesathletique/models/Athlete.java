package www.samnang_alex.com.applicationaccesathletique.models;

public class Athlete {

    private int id;
    private String nom;
    private String prenom;
    private int numeroJoueur;
    private int idEquipe;
    private int idEcole;

    public Athlete() {}
    public Athlete(int id, int idEcole, int idEquipe, String nom, int numeroJoueur, String prenom) {
        this.id = id;
        this.idEcole = idEcole;
        this.idEquipe = idEquipe;
        this.nom = nom;
        this.numeroJoueur = numeroJoueur;
        this.prenom = prenom;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdEcole() { return idEcole; }
    public void setIdEcole(int idEcole) { this.idEcole = idEcole; }
    public int getIdEquipe() { return idEquipe; }
    public void setIdEquipe(int idEquipe) { this.idEquipe = idEquipe; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public int getNumeroJoueur() { return numeroJoueur; }
    public void setNumeroJoueur(int numeroJoueur) { this.numeroJoueur = numeroJoueur; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}