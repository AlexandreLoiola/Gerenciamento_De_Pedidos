package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.EmployeeModel;
import com.alexandreloiola.salesmanagement.model.EmployeePositionModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.repository.EmployeeRepository;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeeDto;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeePositionDto;
import com.alexandreloiola.salesmanagement.rest.dto.PersonDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityViolationException;
import com.alexandreloiola.salesmanagement.service.exceptions.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PersonService personService;
    private final UserService userService;
    private final EmployeePositionService employeePositionService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PersonService personService, UserService userService, EmployeePositionService employeePositionService) {
        this.employeeRepository = employeeRepository;
        this.personService = personService;
        this.userService = userService;
        this.employeePositionService = employeePositionService;
    }

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        return convertListToDto(employeeList);
    }

    private EmployeeModel findEmployeeModelByCpf(String cpf) {
        Long personId = personService.findPersonIdByCpf(cpf);
        return employeeRepository.findById(personId)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado"));
    }

    public EmployeeDto getEmployeeByCpf(String cpf) {
        EmployeeModel employeeModel = findEmployeeModelByCpf(cpf);
        return convertModelToDto(employeeModel);
    }

    @Transactional
    public EmployeeDto registerEmployee(EmployeeRegistrationForm employeeRegistrationForm) {
        PersonForm personForm = new PersonForm();
        personForm.setName(employeeRegistrationForm.getName());
        personForm.setEmail(employeeRegistrationForm.getEmail());
        personForm.setBirthDate(employeeRegistrationForm.getBirthDate());
        personForm.setCpf(employeeRegistrationForm.getCpf());
        personService.insertPerson(personForm);
        Long personId = personService.findPersonIdByCpf(personForm.getCpf());

        UserForm userForm = new UserForm();
        userForm.setEmail(employeeRegistrationForm.getEmail());
        userForm.setPassword(employeeRegistrationForm.getPassword());
        userService.insertUser(userForm);

        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(personId);
        employeeForm.setHireDate(employeeRegistrationForm.getHireDate());
        employeeForm.setSalary(employeeRegistrationForm.getSalary());
        employeeForm.setPosition(employeeRegistrationForm.getPosition());
        EmployeeDto employeeDto = insertEmployee(employeeForm);
        return employeeDto;
    }

    @Transactional
    public EmployeeDto insertEmployee(EmployeeForm employeeForm) {
        PersonModel personModel = personService.findPersonModelById(employeeForm.getId());
        if (employeeRepository.findById(employeeForm.getId()).isPresent()) {
            throw new EmployeeAlreadyExistsException(
                    String.format("O funcionário '%s' já está cadastrado", personModel.getName())
            );
        }
        EmployeePositionModel employeePositionModel
                = employeePositionService.findEmployeePositionModelByDescription(employeeForm.getPosition());
        try {
            EmployeeModel newEmployee = convertFormToModel(employeeForm);
            newEmployee.setId(employeeForm.getId());
            newEmployee.setSalary(employeeForm.getSalary());
            newEmployee.setHireDate(employeeForm.getHireDate());
            newEmployee.setEmployeePosition(employeePositionModel);
            newEmployee = employeeRepository.save(newEmployee);
            return convertModelToDto(newEmployee);
        } catch (DataIntegrityViolationException err) {
            throw new EmployeeInsertException(
                    String.format("Falha ao cadastrar funcionário de cpf '%s'. Verifique se os dados estão corretos", personModel.getCpf())
            );
        }
    }

    @Transactional
    public EmployeeDto updateRegisterEmployee(String cpf, EmployeeUpdateRegisterForm employeeUpdateRegisterForm) {
        PersonUpdateForm personUpdateForm = new PersonUpdateForm();
        personUpdateForm.setName(employeeUpdateRegisterForm.getName());
        personUpdateForm.setBirthDate(employeeUpdateRegisterForm.getBirthDate());
        personUpdateForm.setIsActive(employeeUpdateRegisterForm.getIsActive());
        personService.updatePerson(cpf, personUpdateForm);
        PersonModel personModel = personService.findPersonModelByCpf(cpf);

        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setIsActive(employeeUpdateRegisterForm.getIsActive());
        userService.updateUser(personModel.getEmail(), userUpdateForm);

        EmployeeUpdateForm employeeUpdateForm = new EmployeeUpdateForm();
        employeeUpdateForm.setHireDate(employeeUpdateRegisterForm.getHireDate());
        employeeUpdateForm.setSalary(employeeUpdateRegisterForm.getSalary());
        employeeUpdateForm.setResignationDate(employeeUpdateRegisterForm.getResignationDate());
        employeeUpdateForm.setPosition(employeeUpdateRegisterForm.getPosition());
        EmployeeDto employeeDto = updateEmployee(personModel.getId(), employeeUpdateForm);

        return employeeDto;
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, EmployeeUpdateForm employeeUpdateForm) {
        EmployeePositionModel employeePositionModel
                = employeePositionService.findEmployeePositionModelByDescription(employeeUpdateForm.getPosition());
        EmployeeModel employeeUpdated
                = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(""));
        try {
                employeeUpdated.setHireDate(employeeUpdateForm.getHireDate());
                employeeUpdated.setResignationDate(employeeUpdateForm.getResignationDate());
                employeeUpdated.setSalary(employeeUpdateForm.getSalary());
                employeeUpdated.setEmployeePosition(employeePositionModel);

                employeeRepository.save(employeeUpdated);
                return convertModelToDto(employeeUpdated);
        } catch (DataIntegrityViolationException err) {
            throw new EmployeeUpdateException("Campo(s) obrigatório(s) do funcionário não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteEmployee(String cpf) {
        PersonModel personModel = personService.findPersonModelByCpf(cpf);
        try {
            personService.deletePerson(cpf);
            userService.deleteUser(personModel.getEmail());
            employeeRepository.deleteById(personModel.getId());
        } catch (DataIntegrityViolationException err) {
            throw new EmployeeDeleteException(
                    String.format("Não foi possível deletar o funcionário de cpf '%s'.", cpf)
            );
        }
    }

    private EmployeeModel convertFormToModel(EmployeeForm employeeForm) {
        EmployeePositionModel employeePositionModel
                = employeePositionService.findEmployeePositionModelByDescription(employeeForm.getPosition());
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(employeeModel.getId());
        employeeModel.setHireDate(employeeForm.getHireDate());
        employeeModel.setSalary(employeeModel.getSalary());
        employeeModel.setEmployeePosition(employeePositionModel);
        return employeeModel;
    }

    private EmployeeDto convertModelToDto(EmployeeModel employeeModel) {
        PersonModel personModel = personService.findPersonModelById(employeeModel.getId());
        PersonDto personDto = personService.getPersonByCpf(personModel.getCpf());
        EmployeePositionModel employeePositionModel
                = employeePositionService.findEmployeePositionModelByDescription(employeeModel.getEmployeePosition().getDescription());
        EmployeePositionDto employeePositionDto
                = employeePositionService.getEmployeePositionByDescription(employeePositionModel.getDescription());
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setPersonDto(personDto);
        employeeDto.setHireDate(employeeModel.getHireDate());
        employeeDto.setResignationDate(employeeModel.getResignationDate());
        employeeDto.setSalary(employeeModel.getSalary());
        employeeDto.setPosition(employeePositionDto);
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