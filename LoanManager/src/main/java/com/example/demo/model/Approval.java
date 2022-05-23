package com.example.demo.model;

public class Approval {

    private Integer id;
    private String nom;
    private int reponse_manuelle;

    private long idCompte;

    public Approval(String nom, int reponse_manuelle, long idCompte) {
        this.nom = nom;
        this.reponse_manuelle = reponse_manuelle;
        this.idCompte = idCompte;
    }

    public Approval() {
    }

    public Integer getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getReponse_manuelle() {
        return reponse_manuelle;
    }
    public void setReponse_manuelle(int reponse_manuelle) {
        this.reponse_manuelle = reponse_manuelle;
    }
    public long getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(long id_compte) {
        this.idCompte = id_compte;
    }
}