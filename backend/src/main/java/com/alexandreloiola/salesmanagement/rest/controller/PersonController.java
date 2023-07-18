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

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById (
            @PathVariable("id") Long id
    ) {
        PersonDto personDto = personService.getPersonById(id);
        return ResponseEntity.ok().body(personDto);
    }

    @PostMapping
    public ResponseEntity<PersonDto> insertPerson (
            @Valid @RequestBody PersonForm personForm
    ) {
        PersonDto personDto = personService.insertPerson(personForm);
        return ResponseEntity.ok().body(personDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson (
            @PathVariable("id") Long id,
            @Valid @RequestBody PersonUpdateForm personUpdateForm
    ) {
        PersonDto personDto = personService.updatePerson(id, personUpdateForm);
        return  ResponseEntity.ok().body(personDto);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePerson (
            @PathVariable("id") Long id
    ) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
