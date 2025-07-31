package model;

import java.time.LocalDateTime;

public class Vente {
    private int id;
    private String nomProduit;
    private LocalDateTime dateVente;
    private int quantite;
    private double montantTotal;
    private String nomVendeur;
    private String sexeVendeur; // Nouvel attribut
    private double commission;

    // Constructeur
    public Vente(int id, String nomProduit, LocalDateTime dateVente, int quantite, double montantTotal, String nomVendeur, String sexeVendeur, double commission) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.dateVente = dateVente;
        this.quantite = quantite;
        this.montantTotal = montantTotal;
        this.nomVendeur = nomVendeur;
        this.sexeVendeur = sexeVendeur; // Initialisation du sexe
        this.commission = commission;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomProduit() { return nomProduit; }
    public void setNomProduit(String nomProduit) { this.nomProduit = nomProduit; }
    public LocalDateTime getDateVente() { return dateVente; }
    public void setDateVente(LocalDateTime dateVente) { this.dateVente = dateVente; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
    public String getNomVendeur() { return nomVendeur; }
    public void setNomVendeur(String nomVendeur) { this.nomVendeur = nomVendeur; }
    public String getSexeVendeur() { return sexeVendeur; } // Getter pour le sexe
    public void setSexeVendeur(String sexeVendeur) { this.sexeVendeur = sexeVendeur; } // Setter pour le sexe
    public double getCommission() { return commission; }
    public void setCommission(double commission) { this.commission = commission; }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", nomProduit='" + nomProduit + '\'' +
                ", dateVente=" + dateVente +
                ", quantite=" + quantite +
                ", montantTotal=" + montantTotal +
                ", nomVendeur='" + nomVendeur + '\'' +
                ", sexeVendeur='" + sexeVendeur + '\'' +
                ", commission=" + commission +
                '}';
    }
}