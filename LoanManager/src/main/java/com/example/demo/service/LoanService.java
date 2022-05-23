package com.example.demo.service;

import com.example.demo.composite.AccountComposite;
import com.example.demo.composite.Approval_Composite;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.UnavailableInformationsEntity;
import com.example.demo.model.Account;
import com.example.demo.model.Approval;
import com.example.demo.model.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private AccountComposite accountComposite;
    @Autowired
    private Approval_Composite approval_composite;

    public LoanResponse gererEmprunt(Long id,Integer somme){

        LoanResponse response = new LoanResponse();
        response.setIdCompte(id);
        try{
            Account account = accountComposite.getOnes(id);
            response.setNom(account.getNomCompte());
            response.setIdCompte(account.getId());

            String risk = accountComposite.getRisk(id);
            if(risk == null){
                risk = "high";
            }
            if(risk == "high" ||somme >= 10000 ){
                // On fait appel au Approval Manager pour savoir la décision manuel.
                Approval resp = approval_composite.getOnes(account.getId());
                if(resp == null){
                    response.setReponse(" Error, Le sytème ne peux pas déterminer de décision et aucune indication manuelle n'est inscrite.");
                    response.setCode(404);
                    return response;
                }
                if(resp.getReponse_manuelle() == 1){
                    accountComposite.ajouterCompteSomme(account,somme);
                    response.setReponse(" Prêt accordé ");
                    response.setCode(200);
                    response.setAllowed(true);
                    response.setSomme(somme);
                    return response;
                }else if(resp.getReponse_manuelle() == 0){
                    response.setReponse(" Prêt non accordé , Raison : décision manuel de l'organisme  ");
                    response.setCode(200);
                    response.setSomme(somme);
                    return response;
                }
            }
            if(somme < 10000){
                // On accorde le prêt
                if(risk == "low"){
                    accountComposite.ajouterCompteSomme(account,somme);
                    response.setReponse(" Code correct ");
                    response.setCode(200);
                    response.setAllowed(true);
                    response.setSomme(somme);
                    return response;
                }
            }
        }catch (EntityNotFoundException e){
            response.setReponse("Error, Entity with this id {"+id+"} does not exist");
            response.setCode(404);
            return response;
        }catch (UnavailableInformationsEntity e) {
            e.printStackTrace();
            response.setReponse("Error, We cant evaluate this person Risk, looking for manual processing for Loan");
            response.setCode(500);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setReponse("Error, Internal Error with this id {"+id+"} ");
            response.setCode(500);
            return response;
        }
        return response;
    }
}
