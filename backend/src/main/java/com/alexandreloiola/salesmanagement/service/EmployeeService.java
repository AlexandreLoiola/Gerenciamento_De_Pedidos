package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.EmployeeModel;
import com.alexandreloiola.salesmanagement.model.EmployeePositionModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.repository.EmployeePositionRepository;
import com.alexandreloiola.salesmanagement.repository.EmployeeRepository;
import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeeDto;
import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityViolationException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeePositionRepository employeePositionRepository;

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        return convertListToDto(employeeList);
    }

    public EmployeeDto getEmployeeByCpf(String cpf) {
        try {
            Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
            if (personModel.isPresent()) {
                long id = personModel.get().getId();
                EmployeeModel employeeModel = employeeRepository.findById(id).get();
                return convertModelToDto(employeeModel);
            } else {
                throw new ObjectNotFoundException("Funcionário não encontrado!");
            }
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("Funcionário não encontrado!");
        }
    }

    @Transactional
    public EmployeeDto registerEmployee(EmployeeRegistrationForm employeeRegistrationForm) {
        Optional<EmployeePositionModel> employeePositionModel
                = employeePositionRepository.findByDescription(employeeRegistrationForm.getPosition());
        if (!employeePositionModel.isPresent()) {
            throw new ObjectNotFoundException("O cargo informado não foi encontrado");
        }

        PersonForm personForm = new PersonForm();
        personForm.setName(employeeRegistrationForm.getName());
        personForm.setEmail(employeeRegistrationForm.getEmail());
        personForm.setBirthDate(employeeRegistrationForm.getBirthDate());
        personForm.setCpf(employeeRegistrationForm.getCpf());
        personService.insertPerson(personForm);

        UserForm userForm = new UserForm();
        userForm.setEmail(employeeRegistrationForm.getEmail());
        userForm.setPassword(employeeRegistrationForm.getPassword());
        userService.insertUser(userForm);

        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(personRepository.findByCpf(employeeRegistrationForm.getCpf()).get().getId());
        employeeForm.setHireDate(employeeRegistrationForm.getHireDate());
        employeeForm.setSalary(employeeRegistrationForm.getSalary());
        employeeForm.setPosition(employeePositionModel.get().getDescription());

        System.out.println("|||||||1" + employeeForm);

        EmployeeDto employeeDto = insertEmployee(employeeForm);

        System.out.println("|||||||2" + employeeDto);

        return employeeDto;
    }

    @Transactional
    public EmployeeDto insertEmployee(EmployeeForm employeeForm) {
        try {
            Optional<EmployeePositionModel> employeePositionModel
                    = employeePositionRepository.findByDescription(employeeForm.getPosition());
            if (!employeePositionModel.isPresent()) {
                throw new ObjectNotFoundException("O funcionário não existe");
            }
            EmployeeModel newEmployee = convertFormToModel(employeeForm);
            newEmployee.setId(employeeForm.getId());
            newEmployee.setSalary(employeeForm.getSalary());
            newEmployee.setHireDate(employeeForm.getHireDate());
            newEmployee.setEmployeePosition(employeePositionModel.get());
            newEmployee = employeeRepository.save(newEmployee);
            return convertModelToDto(newEmployee);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do funcionário não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public EmployeeDto updateRegisterEmployee(String cpf, EmployeeUpdateRegisterForm employeeUpdateRegisterForm) {
        Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
        if (!personModel.isPresent()) {
            throw new ObjectNotFoundException("O funcionário não existe");
        }
        Optional<EmployeePositionModel> employeePositionModel
                = employeePositionRepository.findByDescription(employeeUpdateRegisterForm.getPosition());
        if (!employeePositionModel.isPresent()) {
            throw new ObjectNotFoundException("O cargo informado não foi encontrado");
        }

        PersonUpdateForm personUpdateForm = new PersonUpdateForm();
        personUpdateForm.setName(employeeUpdateRegisterForm.getName());
        personUpdateForm.setBirthDate(employeeUpdateRegisterForm.getBirthDate());
        personUpdateForm.setIsActive(employeeUpdateRegisterForm.getIsActive());
        personService.updatePerson(cpf, personUpdateForm);

        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setIsActive(employeeUpdateRegisterForm.getIsActive());
        userService.updateUser(personModel.get().getEmail(), userUpdateForm);

        EmployeeUpdateForm employeeUpdateForm = new EmployeeUpdateForm();
        employeeUpdateForm.setHireDate(employeeUpdateRegisterForm.getHireDate());
        employeeUpdateForm.setSalary(employeeUpdateRegisterForm.getSalary());
        employeeUpdateForm.setResignationDate(employeeUpdateRegisterForm.getResignationDate());
        employeeUpdateForm.setIdPosition(employeePositionModel.get().getId());
        EmployeeDto employeeDto = updateEmployee(personModel.get().getId(), employeeUpdateForm);

        return employeeDto;
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, EmployeeUpdateForm employeeUpdateForm) {
        try {
            Optional<EmployeePositionModel> employeePositionModel
                    = employeePositionRepository.findById(employeeUpdateForm.getIdPosition());
            if (!employeePositionModel.isPresent()) {
                throw new ObjectNotFoundException("O cargo informado não foi encontrado");
            }
            Optional<EmployeeModel> employeeModel = employeeRepository.findById(id);
            if (employeeModel.isPresent()) {
                EmployeeModel employeeUpdated = employeeModel.get();
                employeeUpdated.setHireDate(employeeUpdateForm.getHireDate());
                employeeUpdated.setResignationDate(employeeUpdateForm.getResignationDate());
                employeeUpdated.setSalary(employeeUpdateForm.getSalary());

                employeeRepository.save(employeeUpdated);
                return convertModelToDto(employeeUpdated);
            } else {
                throw new DataIntegrityViolationException("O funcionário não pode ser atualizado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do funcionário não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteEmployee(String cpf) {
        try {
            Optional<PersonModel> personModel = personRepository.findByCpf(cpf);
            if (personModel.isPresent()) {
                long id = personModel.get().getId();
                String email = personModel.get().getEmail();

                personService.deletePerson(cpf);
                userService.deleteUser(email);
                employeeRepository.deleteById(id);
            } else {
                throw new DataIntegrityViolationException("O funcionário não pode ser deletado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o funcionário");
        }
    }

    private EmployeeModel convertFormToModel(EmployeeForm employeeForm) {
        Optional<EmployeePositionModel> employeePositionModel
                = employeePositionRepository.findByDescription(employeeForm.getPosition());
        if (!employeePositionModel.isPresent()) {
            throw new ObjectNotFoundException("O cargo informado não foi encontrado");
        }
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(employeeModel.getId());
        employeeModel.setHireDate(employeeForm.getHireDate());
        employeeModel.setSalary(employeeModel.getSalary());
        employeeModel.setEmployeePosition(employeePositionModel.get());

        return employeeModel;
    }

    private EmployeeDto convertModelToDto(EmployeeModel employeeModel) {
        Optional<PersonModel> personModel = personRepository.findById(employeeModel.getId());
        if(!personModel.isPresent()) {
            throw new org.springframework.dao.DataIntegrityViolationException("A pessoa não existe");
        }
        Optional<EmployeePositionModel> employeePositionModel
                = employeePositionRepository.findByDescription(employeeModel.getEmployeePosition().getDescription());
        if (!employeePositionModel.isPresent()) {
            throw new ObjectNotFoundException("O cargo informado não foi encontrado");
        }
        PersonDto personDto = personService.getPersonByCpf(personModel.get().getCpf());

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setPersonDto(personDto);
        employeeDto.setHireDate(employeeModel.getHireDate());
        employeeDto.setResignationDate(employeeModel.getResignationDate());
        employeeDto.setSalary(employeeModel.getSalary());
        employeeDto.setPosition(employeePositionModel.get().getDescription());

        return employeeDto;
    }

    private List<EmployeeDto> convertListToDto(List<EmployeeModel> list) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (EmployeeModel employeeModel : list) {
            EmployeeDto employeeDto = this.convertModelToDto(employeeModel);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }
}