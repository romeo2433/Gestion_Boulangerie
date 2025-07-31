
package controller;

import model.Utilisateur;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurController {
    private final List<Utilisateur> utilisateurs;

    public UtilisateurController() {
        this.utilisateurs = new ArrayList<>();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurs;
    }

    public void addUtilisateur(int id, String nom, String email, String motDePasse, String role) {
        Utilisateur utilisateur = new Utilisateur(id, nom, email, motDePasse, role);
        utilisateurs.add(utilisateur);
    }

    public void updateUtilisateur(int id, String nom, String email, String motDePasse, String role) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id) {
                utilisateur.setNom(nom);
                utilisateur.setEmail(email);
                utilisateur.setMotDePasse(motDePasse);
                utilisateur.setRole(role);
                break;
            }
        }
    }

    public void deleteUtilisateur(int id) {
        // Remplacer la lambda par une boucle traditionnelle
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).getId() == id) {
                utilisateurs.remove(i);
                break;
            }
        }
    }
}




