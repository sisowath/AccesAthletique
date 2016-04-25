package www.samnang_alex.com.applicationaccesathletique.models;

public class Entreprise {

    private int id;
    private String nom;
    private String nomResponsable;
    private String rue;
    private String ville;
    private String Province;
    private String codePostal;
    private String numeroTelephone;
    private String siteWeb;
    private String courriel;

    public Entreprise() {}

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public String getCourriel() { return courriel; }
    public void setCourriel(String courriel) { this.courriel = courriel; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getNomResponsable() { return nomResponsable; }
    public void setNomResponsable(String nomResponsable) { this.nomResponsable = nomResponsable; }
    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }
    public String getProvince() { return Province; }
    public void setProvince(String province) { Province = province; }
    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }
    public String getSiteWeb() { return siteWeb; }
    public void setSiteWeb(String siteWeb) { this.siteWeb = siteWeb; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
}