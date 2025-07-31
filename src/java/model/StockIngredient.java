package model;

public class StockIngredient {
    private String nom;
    private String quantiteDisponible;
    private String seuilMinimum;

    public StockIngredient(String nom, String quantiteDisponible, String seuilMinimum) {
        this.nom = nom;
        this.quantiteDisponible = quantiteDisponible;
        this.seuilMinimum = seuilMinimum;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public String getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public String getSeuilMinimum() {
        return seuilMinimum;
    }
}
