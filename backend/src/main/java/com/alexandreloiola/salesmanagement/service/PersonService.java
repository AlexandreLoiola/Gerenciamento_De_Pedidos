package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.PersonForm;
import com.alexandreloiola.salesmanagement.rest.form.PersonUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<PersonDto> getAllPerson() {
        List<PersonModel> personModelList = personRepository.findAll();
        return convertListToDto(personModelList);
    }

    public PersonDto getPersonById(Long id) {
        try {
            PersonModel personModel = personRepository.findById(id).get();
            return convertModelToDto(personModel);
        }  catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Pessoa não encontrada!");
        }
    }

    public PersonDto insertPerson(PersonForm personForm) {
        try {
            Optional<PersonModel> byDescription = personRepository.findByCpf(personForm.getCpf());
            if (byDescription.isPresent()) {
                throw new DataIntegrityException("Essz pessoa já foi cadastrado");
            }
            PersonModel personModel = convertFormToModel(personForm);
            personModel.setIsActive(true);
            personModel = personRepository.save(personModel);

            return convertModelToDto(personModel);
        }   catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Authorização não foi(foram) devidamente preenchido(s).");
        }
    }

    public PersonDto updatePerson(Long id, PersonUpdateForm personUpdateForm) {
        try {
            Optional<PersonModel> personModel = personRepository.findById(id);
            if (personModel.isPresent()) {
                PersonModel personUpdate = personModel.get();
                personUpdate.setName(personUpdateForm.getName());
                personUpdate.setEmail(personUpdateForm.getEmail());
                personUpdate.setBirthDate(personUpdateForm.getBirthDate());
                personUpdate.setIsActive(personUpdateForm.getIsActive());
                personUpdate = personRepository.save(personUpdate);
                return convertModelToDto(personUpdate);
            }  else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) da autorização não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Esse produto não está cadastrado");
        }
    }

    public void deletePerson(Long id) {
        try {
            if (personRepository.existsById(id)) {
                personRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar a authorização");
        }
    }

    public PersonDto convertModelToDto(PersonModel personModel) {
        PersonDto personDto = new PersonDto();
        personDto.setName(personModel.getName());
        personDto.setEmail(personModel.getEmail());
        personDto.setBirthDate(personModel.getBirthDate());
        personDto.setCpf(personModel.getCpf());
        personDto.setIsActive(personModel.getIsActive());
        return personDto;
    }

    public PersonModel convertFormToModel(PersonForm personForm) {
        PersonModel personModel = new PersonModel();
        personModel.setName(personForm.getName());
        personModel.setEmail(personForm.getEmail());
        personModel.setBirthDate(personForm.getBirthDate());
        personModel.setCpf(personForm.getCpf());
        return personModel;
    }

    public List<PersonDto> convertListToDto(List<PersonModel> list) {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonModel personModel : list) {
            PersonDto personDto = convertModelToDto(personModel);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }
}