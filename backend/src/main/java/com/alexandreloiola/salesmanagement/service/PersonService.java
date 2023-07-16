package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    @Autowired
    private PersonRepository personRepository;

}
