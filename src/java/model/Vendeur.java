package model;

public class Vendeur {
    private int id;       // Identifiant unique du vendeur
    private String nom;   // Nom du vendeur
    private String sexe;  // Sexe du vendeur

    // Constructeur par défaut
    public Vendeur() {}

    // Constructeur avec paramètres
    public Vendeur(int id, String nom, String sexe) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    @Override
    public String toString() {
        return "Vendeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }
}