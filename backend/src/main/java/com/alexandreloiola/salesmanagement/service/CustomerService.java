package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.form.CustomerForm;
import com.alexandreloiola.salesmanagement.rest.form.CustomerUpdateForm;
import com.alexandreloiola.salesmanagement.rest.form.CustomerRegisterForm;
import com.alexandreloiola.salesmanagement.rest.form.UserForm;
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
    private UserService userService;

    @Transactional
    public CustomerDto registerCustomer(CustomerRegisterForm registerCustomerForm) {
        try {
            CustomerForm customerForm = new CustomerForm();
            customerForm.setName(registerCustomerForm.getName());
            customerForm.setEmail(registerCustomerForm.getEmail());
            customerForm.setBirthDate(registerCustomerForm.getBirthDate());
            customerForm.setCpf(registerCustomerForm.getCpf());

            UserForm userForm = new UserForm();
            userForm.setEmail(registerCustomerForm.getEmail());
            userForm.setPassword(registerCustomerForm.getPassword());

            CustomerDto customerDto = this.insertCustomer(customerForm);
            userService.insertUser(userForm);

            return customerDto;
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do Cadastro do cliente não foi(foram) devidamente preenchido(s)."
            );
        }
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
            Optional<CustomerModel> byCpf = customerRepository.findByCpf(newCustomer.getCpf());

            if (byCpf.isPresent()) {
                throw new DataIntegrityException("Esse cpf já foi cadastrado em um cliente");
            }

            newCustomer.setIsActive(true);
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
                customerUpdated.setName(customerUpdateForm.getName());
                customerUpdated.setBirthDate(customerUpdateForm.getBirthDate());
                customerUpdated.setIsActive(customerUpdateForm.getIsActive());
                customerRepository.save(customerUpdated);

                return convertModelToDto(customerUpdated);
            } else {
                throw new DataIntegrityViolationException("O cliente não pode ser atualizado");
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
                userService.deleteUser(id);
            } else {
                throw new DataIntegrityViolationException("O cliente não pode ser deletado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o cliente");
        }
    }

    private CustomerModel convertFormToModel(CustomerForm customerForm) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName(customerForm.getName());
        customerModel.setEmail(customerForm.getEmail());
        customerModel.setBirthDate(customerForm.getBirthDate());
        customerModel.setCpf(customerForm.getCpf());

        return customerModel;
    }

    private CustomerDto convertModelToDto(CustomerModel customerModel) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customerModel.getName());
        customerDto.setEmail(customerModel.getEmail());
        customerDto.setBirthDate(customerModel.getBirthDate());
        customerDto.setCpf(customerModel.getCpf());
        customerDto.setIsActive(customerModel.getIsActive());

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