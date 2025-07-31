package model;

public class PanierItem {
    private int produitId;
    private String produitNom;
    private double prixUnitaire;
    private int quantite;
    
    public PanierItem(int produitId, String produitNom, double prixUnitaire, int quantite) {
        this.produitId = produitId;
        this.produitNom = produitNom;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    public int getProduitId() {
        return produitId;
    }

    public String getProduitNom() {
        return produitNom;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getTotal() {
        return prixUnitaire * quantite;
    }
}
