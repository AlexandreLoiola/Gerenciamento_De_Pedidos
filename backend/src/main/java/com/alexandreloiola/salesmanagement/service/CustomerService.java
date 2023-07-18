package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        customerForm.setResignationDate(customerRegisterForm.getResignationDate());

        CustomerDto customerDto = insertCustomer(customerForm);

        return customerDto;
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerModel> customerList = customerRepository.findAll();
        return convertListToDto(customerList);
    }

    public CustomerDto getCustomerById(long id) {
        try {
            CustomerModel customerModel = customerRepository.findById(id).get();
            return convertModelToDto(customerModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Cliente não encontrado!");
        }
    }

    @Transactional
    public CustomerDto insertCustomer(CustomerForm customerForm) {
        try {
            CustomerModel newCustomer = convertFormToModel(customerForm);

            newCustomer = customerRepository.save(newCustomer);
            return convertModelToDto(newCustomer);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do cliente não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public CustomerDto updateCustomer(long id, CustomerUpdateForm customerUpdateForm) {
        try {
            Optional<CustomerModel> customerModel = customerRepository.findById(id);
            if (customerModel.isPresent()) {
                CustomerModel customerUpdated = customerModel.get();
                customerUpdated.setId(customerUpdated.getId());
                customerUpdated.setResignationDate(customerUpdateForm.getResignationDate());

                customerRepository.save(customerUpdated);

                return convertModelToDto(customerUpdated);
            } else {
                throw new DataIntegrityViolationException("O cliente não existe");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do cliente não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteCustomer(long id) {
        try {
            if(customerRepository.existsById(id)) {
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
        customerModel.setResignationDate(customerForm.getResignationDate());

        return customerModel;
    }

    private CustomerDto convertModelToDto(CustomerModel customerModel) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setResignationDate(customerModel.getResignationDate());
        PersonDto personDto = personService.getPersonById(customerModel.getId());
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