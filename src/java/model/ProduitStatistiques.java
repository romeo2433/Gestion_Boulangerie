package model;

public class ProduitStatistiques {
    private String nom;
    private int quantiteVendue;

    public ProduitStatistiques(String nom, int quantiteVendue) {
        this.nom = nom;
        this.quantiteVendue = quantiteVendue;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantiteVendue() {
        return quantiteVendue;
    }
}
