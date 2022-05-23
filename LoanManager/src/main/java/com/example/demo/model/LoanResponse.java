package com.example.demo.model;

public class LoanResponse {

    private String nom;
    private Long idCompte;
    private String reponse;
    private Integer code;
    private Boolean allowed = false;
    private Integer somme;

    public LoanResponse(String nom, Long idCompte, String reponse, Integer code, Boolean allowed, Integer somme) {
        this.nom = nom;
        this.idCompte = idCompte;
        this.reponse = reponse;
        this.code = code;
        this.allowed = allowed;
        this.somme = somme;
    }

    public LoanResponse() {
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Long getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }
    public String getReponse() {
        return reponse;
    }
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Boolean getAllowed() {
        return allowed;
    }
    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }
    public Integer getSomme() {
        return somme;
    }
    public void setSomme(Integer somme) {
        this.somme = somme;
    }
}
