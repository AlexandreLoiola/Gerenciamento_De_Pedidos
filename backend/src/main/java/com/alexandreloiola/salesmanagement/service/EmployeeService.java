package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.EmployeeModel;
import com.alexandreloiola.salesmanagement.model.EmployeePositionModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import com.alexandreloiola.salesmanagement.repository.EmployeePositionRepository;
import com.alexandreloiola.salesmanagement.repository.EmployeeRepository;
import com.alexandreloiola.salesmanagement.repository.PersonRepository;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeeDto;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeePositionDto;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeUpdateForm;
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
    private EmployeePositionRepository employeePositionRepository;

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        return convertListToDto(employeeList);
    }

    public EmployeeDto getEmployeeById(long id) {
        try {
            EmployeeModel employeeModel = employeeRepository.findById(id).get();
            return convertModelToDto(employeeModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Funcionário não encontrado!");
        }
    }

    @Transactional
    public EmployeeDto insertEmployee(EmployeeForm employeeForm) {
        try {
            EmployeeModel newemployee = convertFormToModel(employeeForm);

            newemployee = employeeRepository.save(newemployee);
            return convertModelToDto(newemployee);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do funcionário não foi(foram) devidamente preenchido(s).");
        }
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
                employeeUpdated.setHireDate(employeeUpdateForm.getHire_date());
                employeeUpdated.setResignationDate(employeeUpdateForm.getResignation_date());
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
    public void deleteEmployee(long id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
            } else {
                throw new DataIntegrityViolationException("O funcionário não pode ser deletado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o funcionário");
        }
    }

    private EmployeeModel convertFormToModel(EmployeeForm employeeForm) {
        EmployeeModel employeeModel = new EmployeeModel();

        Optional<PersonModel> personModel
                = personRepository.findById(employeeForm.getIdPerson());
        if (personModel.isPresent()) {
            employeeModel.setId(personModel.get().getId());
        } else {
            throw new ObjectNotFoundException("A pessoa informada não foi encontrado");
        }
        Optional<EmployeePositionModel> employeePositionModel
                = employeePositionRepository.findById(employeeForm.getIdPosition());
        if (employeePositionModel.isPresent()) {
            employeeModel.setEmployeePosition(employeePositionModel.get());
        } else {
            throw new ObjectNotFoundException("O cargo informado não foi encontrado");
        }

        employeeModel.setHireDate(employeeForm.getHire_date());
        employeeModel.setSalary(employeeForm.getSalary());

        return employeeModel;
    }

    private EmployeeDto convertModelToDto(EmployeeModel employeeModel) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employeeModel.getId());
        employeeDto.setHire_date(employeeModel.getHireDate());
        employeeDto.setResignation_date(employeeModel.getResignationDate());
        employeeDto.setSalary(employeeModel.getSalary());
        employeeDto.setPosition(employeeModel.getEmployeePosition().getDescription());

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