package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.form.CustomerForm;
import com.alexandreloiola.salesmanagement.rest.form.CustomerUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<CustomerModel> customerList = customerRepository.findAll();
        return convertListToDto(customerList);
    }

    public CustomerDto getCustomerById(long id) {
        CustomerModel customerModel = customerRepository.findById(id).get();
        return convertModelToDto(customerModel);
    }

    public CustomerDto insertCustomer(CustomerForm customerForm) {
        CustomerModel newCustomer = convertFormToModel(customerForm);
        newCustomer.setIsActive(true);
        newCustomer = customerRepository.save(newCustomer);

        return convertModelToDto(newCustomer);
    }

    public CustomerDto updateCustomer(long id, CustomerUpdateForm customerUpdateForm) {
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
    }

    public void deleteCustomer(long id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("O cliente não pode ser deletado");
        }
    }

    private CustomerModel convertFormToModel(CustomerForm customerForm) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName(customerForm.getName());
        customerModel.setBirthDate(customerForm.getBirthDate());
        customerModel.setCpf(customerForm.getCpf());

        return customerModel;
    }

    private CustomerDto convertModelToDto(CustomerModel customerModel) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customerModel.getName());
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
