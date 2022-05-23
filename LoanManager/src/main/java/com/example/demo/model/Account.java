package com.example.demo.model;

public class Account {

    private Long id;
    private String nomCompte;
    private Integer amount;
    // If risk == true => High Risk for approval
    private Boolean risk;

    public Account(String nomCompte, Integer amount) {
        this.nomCompte = nomCompte;
        this.amount = amount;
        this.risk = false;
    }

    public Account() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomCompte() {
        return nomCompte;
    }
    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Boolean getRisk() {
        return risk;
    }
    public void setRisk(Boolean risk) {
        this.risk = risk;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", nomCompte='" + nomCompte + '\'' +
                ", amount=" + amount +
                '}';
    }
}
