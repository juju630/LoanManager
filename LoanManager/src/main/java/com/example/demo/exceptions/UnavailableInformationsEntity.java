package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "Entity not found Error")
public class UnavailableInformationsEntity extends RuntimeException{
}
