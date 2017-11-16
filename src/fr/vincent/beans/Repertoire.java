package fr.vincent.beans;

import java.util.ArrayList;
import java.util.List;

public class Repertoire {
	private String nom;
	private List<Personne> listePersonnes;
	
	// Constructor
	public Repertoire() {
		listePersonnes = new ArrayList<Personne>();
		}
	public Repertoire(String nom) {
		this(); //Appel constructeur vide
		setNom(nom);
	}
	
	// Setter and Getter
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void addPersonne(Personne personne) {
		this.listePersonnes.add(personne);
	}
	public List<Personne> getListePersonnes() {
		return listePersonnes;
	}
	
}
