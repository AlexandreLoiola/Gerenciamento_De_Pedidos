package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.model.UserModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public List<CustomerDto> getAllCustomers() {
        List<CustomerModel> customerList = customerRepository.findAll();
        return convertListToDto(customerList);
    }

    public CustomerDto getCustomerByCpf(String cpf) {
        try {
            Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
            if (personModel.isPresent()) {
                long id = personModel.get().getId();
                CustomerModel customerModel = customerRepository.findById(id).get();
                return convertModelToDto(customerModel);
            } else {
                throw new ObjectNotFoundException("Cliente não encontrado!");
            }
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("Cliente não encontrado!");
        }
    }

    @Transactional
    public CustomerDto registerCustomer(CustomerRegisterForm customerRegisterForm) {
        PersonForm personForm = new PersonForm();
        personForm.setName(customerRegisterForm.getName());
        personForm.setEmail(customerRegisterForm.getEmail());
        personForm.setBirthDate(customerRegisterForm.getBirthDate());
        personForm.setCpf(customerRegisterForm.getCpf());
        personService.insertPerson(personForm);

        UserForm userForm = new UserForm();
        userForm.setEmail(customerRegisterForm.getEmail());
        userForm.setPassword(customerRegisterForm.getPassword());
        userService.insertUser(userForm);

        CustomerForm customerForm = new CustomerForm();
        customerForm.setId(personRepository.findByCpf(customerRegisterForm.getCpf()).get().getId());

        CustomerDto customerDto = insertCustomer(customerForm);

        return customerDto;
    }

    @Transactional
    public CustomerDto insertCustomer(CustomerForm customerForm) {
        try {
            CustomerModel newCustomer = convertFormToModel(customerForm);
            newCustomer.setRegistrationDate(LocalDate.now());

            newCustomer = customerRepository.save(newCustomer);
            return convertModelToDto(newCustomer);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do cliente não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public CustomerDto updateRegisterCustomer(String cpf, CustomerUpdateRegisterForm customerUpdateRegisterForm) {
        Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
        if (!personModel.isPresent()) {
            throw new DataIntegrityViolationException("O cliente não existe");
        }

        PersonUpdateForm personUpdateForm = new PersonUpdateForm();
        personUpdateForm.setName(customerUpdateRegisterForm.getName());
        personUpdateForm.setBirthDate(customerUpdateRegisterForm.getBirthDate());
        personUpdateForm.setIsActive(customerUpdateRegisterForm.getIsActive());
        personService.updatePerson(cpf, personUpdateForm);

        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setIsActive(customerUpdateRegisterForm.getIsActive());
        userService.updateUser(personModel.get().getEmail(), userUpdateForm);

        CustomerUpdateForm customerUpdateForm = new CustomerUpdateForm();
        customerUpdateForm.setRegistrationDate(customerUpdateRegisterForm.getRegistrationDate());
        CustomerDto customerDto = updateCustomer(personModel.get().getId(), customerUpdateForm);

        return customerDto;
    }

    @Transactional
    public CustomerDto updateCustomer(long id, CustomerUpdateForm customerUpdateForm) {
        try {
            Optional<CustomerModel> customerModel = customerRepository.findById(id);
            if (!customerModel.isPresent()) {
                throw new DataIntegrityViolationException("O cliente não existe");
            }

            CustomerModel customerUpdated = customerModel.get();
            customerUpdated.setRegistrationDate(customerUpdateForm.getRegistrationDate());

            customerRepository.save(customerUpdated);

            return convertModelToDto(customerUpdated);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("1Campo(s) obrigatório(s) do cliente não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteCustomer(String cpf) {
        try {
            Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
            if (personModel.isPresent()) {
                long id = personModel.get().getId();
                String email = personModel.get().getEmail();

                personService.deletePerson(cpf);
                userService.deleteUser(email);
                customerRepository.deleteById(id);
            } else {
                throw new DataIntegrityViolationException("Este cliente não existe");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o cliente");
        }
    }

    private CustomerModel convertFormToModel(CustomerForm customerForm) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerForm.getId());
        customerModel.setRegistrationDate(customerForm.getRegistrationDate());

        return customerModel;
    }

    private CustomerDto convertModelToDto(CustomerModel customerModel) {
        CustomerDto customerDto = new CustomerDto();

        Optional<PersonModel> personModel = personRepository.findById(customerModel.getId());
        if(!personModel.isPresent()) {
            throw new DataIntegrityViolationException("A pessoa não existe");
        }
        customerDto.setRegistrationDate(customerModel.getRegistrationDate());
        PersonDto personDto = personService.getPersonByCpf(personModel.get().getCpf());
        customerDto.setPersonDto(personDto);
        return customerDto;
    }

    private List<CustomerDto> convertListToDto(List<CustomerModel> list) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (CustomerModel customerModel : list) {
            CustomerDto customerDto = this.convertModelToDto(customerModel);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }
}