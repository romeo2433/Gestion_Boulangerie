package model;

public class StockProduit {
    private int id;
    private int produitId;
    private String nom;
    private int quantiteDisponible;
    private int seuilMinimum;

    // Nouveau constructeur avec produitId
    public StockProduit(int id, int produitId, int quantiteDisponible, String nom, int seuilMinimum) {
        this.id = id;
        this.produitId = produitId;
        this.quantiteDisponible = quantiteDisponible;
        this.nom = nom;
        this.seuilMinimum = seuilMinimum;
    }

    // Getters
    public int getProduitId() {
        return produitId;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public String getNom() {
        return nom;
    }

    public int getSeuilMinimum() {
        return seuilMinimum;
    }
}
