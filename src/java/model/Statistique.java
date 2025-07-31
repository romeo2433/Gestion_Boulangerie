package model;

public class Statistique {
    private int idStatistique;
    private String date;
    private double benefices;
    private double pertes;
    private int produitPopulaire;

    public Statistique() {}

    public Statistique(int idStatistique, String date, double benefices, double pertes, int produitPopulaire) {
        this.idStatistique = idStatistique;
        this.date = date;
        this.benefices = benefices;
        this.pertes = pertes;
        this.produitPopulaire = produitPopulaire;
    }

    public int getIdStatistique() {
        return idStatistique;
    }

    public void setIdStatistique(int idStatistique) {
        this.idStatistique = idStatistique;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBenefices() {
        return benefices;
    }

    public void setBenefices(double benefices) {
        this.benefices = benefices;
    }

    public double getPertes() {
        return pertes;
    }

    public void setPertes(double pertes) {
        this.pertes = pertes;
    }

    public int getProduitPopulaire() {
        return produitPopulaire;
    }

    public void setProduitPopulaire(int produitPopulaire) {
        this.produitPopulaire = produitPopulaire;
    }

    @Override
    public String toString() {
        return "Statistique [idStatistique=" + idStatistique + ", date=" + date +
               ", benefices=" + benefices + ", pertes=" + pertes +
               ", produitPopulaire=" + produitPopulaire + "]";
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
