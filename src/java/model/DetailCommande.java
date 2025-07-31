package model;

public class DetailCommande {
    private int idCommande;
    private String dateCommande;
    private String statut;
    private int idClient;
    private String nomClient;
    private int idProduit;
    private String produit;
    private int quantite;

    public DetailCommande(int idCommande, String dateCommande, String statut, int idClient, String nomClient, int idProduit, String produit, int quantite) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.idProduit = idProduit;
        this.produit = produit;
        this.quantite = quantite;
    }

    // Getters et Setters
    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public String getDateCommande() { return dateCommande; }
    public void setDateCommande(String dateCommande) { this.dateCommande = dateCommande; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public String getProduit() { return produit; }
    public void setProduit(String produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
