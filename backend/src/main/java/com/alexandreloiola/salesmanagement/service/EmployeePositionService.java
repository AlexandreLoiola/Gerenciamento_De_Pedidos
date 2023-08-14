package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.EmployeePositionModel;
import com.alexandreloiola.salesmanagement.repository.EmployeePositionRepository;
import com.alexandreloiola.salesmanagement.rest.dto.EmployeePositionDto;
import com.alexandreloiola.salesmanagement.rest.form.EmployeePositionForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeePositionUpdateForm;
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
public class EmployeePositionService {

    @Autowired
    private EmployeePositionRepository employeePositionRepository;

    public List<EmployeePositionDto> getAllEmployeePosition() {
        List<EmployeePositionModel> employeePositionModelList = employeePositionRepository.findAll();
        return convertListToDto(employeePositionModelList);
    }

    public EmployeePositionDto getEmployeePositionByDescription(String description) {
        try {
            EmployeePositionModel employeePositionModel = employeePositionRepository.findByDescription(description).get();
            return convertModelToDto(employeePositionModel);
        }  catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Cargo não encontrado!");
        }
    }

    public EmployeePositionDto insertEmployeePosition(EmployeePositionForm employeePositionForm) {
        try {
            Optional<EmployeePositionModel> byDescription = employeePositionRepository.findByDescription(employeePositionForm.getDescription());
            if (byDescription.isPresent()) {
                throw new DataIntegrityException("Este cargo já foi cadastrado");
            }
            EmployeePositionModel employeePositionModel = convertFormToModel(employeePositionForm);
            employeePositionModel.setIsActive(true);
            employeePositionModel = employeePositionRepository.save(employeePositionModel);

            return convertModelToDto(employeePositionModel);
        }   catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do cargo não foi(foram) devidamente preenchido(s).");
        }
    }

    public EmployeePositionDto updateEmployeePosition(String description, EmployeePositionUpdateForm employeePositionUpdateForm) {
        try {
            Optional<EmployeePositionModel> employeePositionModel = employeePositionRepository.findByDescription(description);
            if (employeePositionModel.isPresent()) {
                EmployeePositionModel employeePositionUpdate = employeePositionModel.get();
                employeePositionUpdate.setDescription(employeePositionUpdateForm.getDescription());
                employeePositionUpdate.setIsActive(employeePositionUpdateForm.getIsActive());
                employeePositionUpdate = employeePositionRepository.save(employeePositionUpdate);
                return convertModelToDto(employeePositionUpdate);
            }  else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do cargo não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Cargo não está cadastrado");
        }
    }

    public void deleteEmployeePosition(String description) {
        try {
            Optional<EmployeePositionModel> employeePositionModel = employeePositionRepository.findByDescription(description);
            if (!employeePositionModel.isPresent()) {
                throw new DataIntegrityViolationException("Cargo não está cadastrado");
            }
            long id = employeePositionModel.get().getId();
            employeePositionRepository.deleteById(id);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar a authorização");
        }
    }

    public EmployeePositionDto convertModelToDto(EmployeePositionModel employeePositionModel) {
        EmployeePositionDto employeePositionDto = new EmployeePositionDto();

        employeePositionDto.setDescription(employeePositionModel.getDescription());
        employeePositionDto.setIsActive(employeePositionModel.getIsActive());

        return employeePositionDto;
    }

    public EmployeePositionModel convertFormToModel(EmployeePositionForm employeePositionForm) {
        EmployeePositionModel employeePositionModel = new EmployeePositionModel();
        employeePositionModel.setDescription(employeePositionForm.getDescription());
        return employeePositionModel;
    }

    public List<EmployeePositionDto> convertListToDto(List<EmployeePositionModel> list) {
        List<EmployeePositionDto> employeePositionDtoList = new ArrayList<>();
        for (EmployeePositionModel employeePositionModel : list) {
            EmployeePositionDto employeePositionDto = convertModelToDto(employeePositionModel);
            employeePositionDtoList.add(employeePositionDto);
        }
        return employeePositionDtoList;
    }
}
