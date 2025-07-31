package model;

import java.sql.ResultSet;
import java.util.Date;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int categorieId;
    private String categorieNom;
    private Date dateCreation;
    private Date dateModification;
    private double seuilMinimum;
    private Type type; // Champ pour le Type

    // Constructeur avec paramètres
  public Produit(int id, String nom) {
    this.id = id;
    this.nom = nom;
}
  
  
  public Produit(int id, String nom , double prix , String categorieNom) {
    this.id = id;
    this.nom = nom;
    this.prix = prix;
    this.categorieNom = categorieNom;
}

    // Constructeur par défaut
    public Produit() {
        this.dateCreation = new Date();
        this.dateModification = new Date();
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getCategorieId() { return categorieId; }
    public void setCategorieId(int categorieId) { this.categorieId = categorieId; }

    public String getCategorieNom() { return categorieNom; }
    public void setCategorieNom(String categorieNom) { this.categorieNom = categorieNom; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public Date getDateModification() { return dateModification; }
    public void setDateModification(Date dateModification) { this.dateModification = dateModification; }

    public double getSeuilMinimum() { return seuilMinimum; }
    public void setSeuilMinimum(double seuilMinimum) { this.seuilMinimum = seuilMinimum; }

    // Nouveau champ Type
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
}
