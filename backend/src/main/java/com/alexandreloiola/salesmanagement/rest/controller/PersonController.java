package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.PersonForm;
import com.alexandreloiola.salesmanagement.rest.form.PersonUpdateForm;
import com.alexandreloiola.salesmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        List<PersonDto> personDtoList = personService.getAllPerson();
        return ResponseEntity.ok().body(personDtoList);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PersonDto> getPersonByCpf (
            @PathVariable("cpf") String cpf
    ) {
        PersonDto personDto = personService.getPersonByCpf(cpf);
        return ResponseEntity.ok().body(personDto);
    }

    @PostMapping
    public ResponseEntity<PersonDto> insertPerson (
            @Valid @RequestBody PersonForm personForm
    ) {
        PersonDto personDto = personService.insertPerson(personForm);
        return ResponseEntity.ok().body(personDto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<PersonDto> updatePerson (
            @PathVariable("cpf") String cpf,
            @Valid @RequestBody PersonUpdateForm personUpdateForm
    ) {
        PersonDto personDto = personService.updatePerson(cpf, personUpdateForm);
        return  ResponseEntity.ok().body(personDto);
    }

    @DeleteMapping("/{cpf}")
    public  ResponseEntity<Void> deletePerson (
            @PathVariable("cpf") String cpf
    ) {
        personService.deletePerson(cpf);
        return ResponseEntity.noContent().build();
    }
}
