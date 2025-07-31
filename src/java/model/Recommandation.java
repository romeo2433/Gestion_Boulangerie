/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Romeo
 */
import java.sql.Date;

public class Recommandation {
    private int idRecommandation;
    private Date dateDebut; // java.sql.Date
    private Date dateFin;   // java.sql.Date
    private Produit produit; // Objet Produit associé

    // Constructeurs
    public Recommandation() {}

    public Recommandation(int idRecommandation, Date dateDebut, Date dateFin, Produit produit) {
        this.idRecommandation = idRecommandation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.produit = produit;
    }

    // Getters et Setters
    public int getIdRecommandation() {
        return idRecommandation;
    }

    public void setIdRecommandation(int idRecommandation) {
        this.idRecommandation = idRecommandation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Recommandation{" +
                "idRecommandation='" + idRecommandation + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", produit=" + produit +
                '}';
    }
}
