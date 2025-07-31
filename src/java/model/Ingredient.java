package model;

public class Ingredient {
    private int idIngredient;
    private String nom;
    private double prixUnitaire;
    private double seuilMinimum;
    private Unite unite; // Association avec le modèle Unite

    // Constructeur complet
    public Ingredient(int idIngredient, String nom, double prixUnitaire, double seuilMinimum) {
        this.idIngredient = idIngredient;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.seuilMinimum = seuilMinimum;
        this.unite = unite;
    }

    // Constructeur sans seuilMinimum (valeur par défaut à 0)
    public Ingredient(int idIngredient, String nom, double prixUnitaire, Unite unite) {
        this.idIngredient = idIngredient;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.seuilMinimum = 0;
        this.unite = unite;
    }

    // Getters
    public int getIdIngredient() {
        return idIngredient;
    }

    public String getNom() {
        return nom;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public double getSeuilMinimum() {
        return seuilMinimum;
    }

    public Unite getUnite() {
        return unite;
    }

    // Setters
    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setSeuilMinimum(double seuilMinimum) {
        this.seuilMinimum = seuilMinimum;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    // toString pour afficher les détails de l'ingrédient et de son unité
    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", nom='" + nom + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", seuilMinimum=" + seuilMinimum +
                ", unite=" + (unite != null ? unite.toString() : "null") +
                '}';
    }
}
