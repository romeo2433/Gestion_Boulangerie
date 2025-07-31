package model;

import java.time.LocalDateTime;

public class Commande {
    private int commandeId;
    private String nomProduit;
    private int quantite;
    private String nomClient;
    private String statut;
    private LocalDateTime dateCommande;

    // Constructeur
    public Commande(int commandeId, String nomProduit, int quantite, String nomClient, String statut, LocalDateTime dateCommande) {
        this.commandeId = commandeId;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.nomClient = nomClient;
        this.statut = statut;
        this.dateCommande = dateCommande;
    }

    // Getters et Setters
    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }
}
