package com.example.demo.controller;


import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.InternalErrorException;
import com.example.demo.model.LoanResponse;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public LoanResponse gestionEmprunt(@RequestParam("nom") Long nom,@RequestParam("somme") Integer somme){
        try{
            return loanService.gererEmprunt(nom,somme);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }catch (InternalErrorException e){
            throw new InternalErrorException();
        }catch (Exception e){
            e.printStackTrace();
            throw new InternalErrorException();
        }
    }
}
