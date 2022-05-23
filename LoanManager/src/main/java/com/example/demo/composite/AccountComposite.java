package com.example.demo.composite;

import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.InternalErrorException;
import com.example.demo.exceptions.UnavailableInformationsEntity;
import com.example.demo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountComposite {

    RestTemplate restTemplate = new RestTemplate();
    final String BASE_URL = "https://accmanager-345414.ew.r.appspot.com";

    public Account getOnes(Long id){
        try{
            Account res = restTemplate.getForObject(BASE_URL + "/account/"+id,Account.class);
            if(res == null){ throw new EntityNotFoundException(); }
            return res;
        }catch (EntityNotFoundException ENFE){
            throw new EntityNotFoundException();
        }catch (Exception E){
            throw new InternalErrorException();
        }
    }


    public String getRisk(Long id) {
        try {
            String risk = restTemplate.getForObject(BASE_URL + "/account/risk/" + id, String.class);
            return risk;
        }catch (HttpClientErrorException e){
            // Si on reçois une 404 alors c'est que le risk de la personne n'a pas été trouvé
            if(e.getRawStatusCode() == 404){
                return "high";
            }else{
                throw new InternalErrorException();
            }
        }catch (UnavailableInformationsEntity e){
                throw new UnavailableInformationsEntity();
        }catch (Exception e){
            e.printStackTrace();
            throw new InternalErrorException();
        }
    }

    public void ajouterCompteSomme(Account account, Integer somme) {
        try{
            account.setAmount(account.getAmount() + somme);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<Account> requestBody = new HttpEntity<>(account, headers);

            restTemplate.put(BASE_URL + "/account/"+account.getId(),requestBody);
        }catch (EntityNotFoundException ENFE){
            throw new EntityNotFoundException();
        }catch (Exception E){
            throw new InternalErrorException();
        }
    }
}
