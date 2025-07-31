package model;

public class Client {
    private int idClient;
    private String nom;
    private String email;
    private String telephone; // Peut être null si le numéro de téléphone est facultatif

    // Constructeur complet
    public Client(int idClient, String nom) {
        this.idClient = idClient;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
    }

    // Constructeur sans téléphone (valeur par défaut à null)
    public Client(int idClient, String nom, String email) {
        this.idClient = idClient;
        this.nom = nom;
        this.email = email;
        this.telephone = null;
    }

    // Getters
    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    // Setters
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // toString pour afficher les détails du client
    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + (telephone != null ? telephone : "non renseigné") + '\'' +
                '}';
    }
}
