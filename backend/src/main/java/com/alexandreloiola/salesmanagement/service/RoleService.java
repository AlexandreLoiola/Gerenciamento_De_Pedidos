package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.RoleModel;
import com.alexandreloiola.salesmanagement.repository.RoleRepository;
import com.alexandreloiola.salesmanagement.rest.dto.RoleDto;
import com.alexandreloiola.salesmanagement.rest.form.RoleForm;
import com.alexandreloiola.salesmanagement.rest.form.RoleUpdateForm;
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
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDto> getAllRole() {
        List<RoleModel> roleModelList = roleRepository.findAll();
        return convertListToDto(roleModelList);
    }

    public RoleDto getRoleById(Long id) {
        try {
            RoleModel roleModel = roleRepository.findById(id).get();
            return convertModelToDto(roleModel);
        }  catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Autorização não encontrado!");
        }
    }

    public RoleDto insertRole(RoleForm roleForm) {
        try {
            Optional<RoleModel> byDescription = roleRepository.findByDescription(roleForm.getDescription());
            if (byDescription.isPresent()) {
                throw new DataIntegrityException("Essa autorização já foi cadastrado");
            }
            RoleModel roleModel = convertFormToModel(roleForm);
            roleModel.setIsActive(true);
            roleModel = roleRepository.save(roleModel);

            return convertModelToDto(roleModel);
        }   catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Authorização não foi(foram) devidamente preenchido(s).");
        }
    }

    public RoleDto updateRole(Long id, RoleUpdateForm roleUpdateForm) {
        try {
            Optional<RoleModel> roleModel = roleRepository.findById(id);
            if (roleModel.isPresent()) {
                RoleModel roleUpdate = roleModel.get();
                roleUpdate.setDescription(roleUpdateForm.getDescription());
                roleUpdate.setIsActive(roleUpdateForm.getIsActive());
                roleUpdate = roleRepository.save(roleUpdate);
                return convertModelToDto(roleUpdate);
            }  else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) da autorização não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Esse produto não está cadastrado");
        }
    }

    public void deleteRole(Long id) {
        try {
            if (roleRepository.existsById(id)) {
                roleRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar a authorização");
        }
    }

    public RoleDto convertModelToDto(RoleModel roleModel) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(roleModel.getId());
        roleDto.setDescription(roleModel.getDescription());
        roleDto.setIsActive(roleModel.getIsActive());

        return roleDto;
    }

    public RoleModel convertFormToModel(RoleForm roleForm) {
        RoleModel roleModel = new RoleModel();
        roleModel.setDescription(roleForm.getDescription());
        return roleModel;
    }

    public List<RoleDto> convertListToDto(List<RoleModel> list) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleModel roleModel : list) {
            RoleDto roleDto = convertModelToDto(roleModel);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}
