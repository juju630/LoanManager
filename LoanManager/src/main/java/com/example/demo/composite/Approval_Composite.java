package com.example.demo.composite;

import com.example.demo.exceptions.ApprovalEntityNotFound;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.InternalErrorException;
import com.example.demo.exceptions.UnavailableInformationsEntity;
import com.example.demo.model.Account;
import com.example.demo.model.Approval;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class Approval_Composite {

    RestTemplate restTemplate = new RestTemplate();
    final String BASE_URL = "https://quiet-everglades-10625.herokuapp.com";

    public Approval getOnes(Long id){
        try {
            Approval res = restTemplate.getForObject(BASE_URL + "/approval/id_compte/" + id, Approval.class);
            return res;
        }catch (HttpClientErrorException e){
                // Si on reçois une 404 alors c'est que le approval de la personne n'a pas été trouvé
                if(e.getRawStatusCode() == 404){
                    throw new ApprovalEntityNotFound();
                }else{
                    throw new InternalErrorException();
                }
        }catch (EntityNotFoundException ENFE){
            throw new EntityNotFoundException();
        }catch (Exception E){
            throw new InternalErrorException();
        }
    }
}
