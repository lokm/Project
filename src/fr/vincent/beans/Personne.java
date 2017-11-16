package fr.vincent.beans;

import fr.vincent.utils.Groupe;

public class Personne {
	private String nom;
	private String prenom;
	private String tel;
	private Adresse adresse;
	private Boolean fav;
	private Groupe groupe;
	
	
	// Contructor
	public Personne() {
		groupe = Groupe.CONTACT;
	}
	public Personne(String nom, String prenom, String tel, Adresse adresse) {
		this();
		setNom(nom);
		setPrenom(prenom);
		setTel(tel);
		setAdresse(adresse);
	}
	
	// Getters and Setter
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	public Boolean getFav() {
		return fav;
	}
	public void setFav(Boolean fav) {
		this.fav = fav;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
}
