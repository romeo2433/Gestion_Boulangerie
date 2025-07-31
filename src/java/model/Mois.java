package model;

public class Mois {
    private int id;
    private String moisNom;

    // Constructeur
    public Mois(int id, String moisNom) {
        this.id = id;
        this.moisNom = moisNom;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoisNom() {
        return moisNom;
    }

    public void setMoisNom(String moisNom) {
        this.moisNom = moisNom;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Mois{" +
                "id=" + id +
                ", moisNom='" + moisNom + '\'' +
                '}';
    }
}
