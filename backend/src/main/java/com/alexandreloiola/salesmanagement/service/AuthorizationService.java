package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.AuthorizationModel;
import com.alexandreloiola.salesmanagement.repository.AuthorizationRepository;
import com.alexandreloiola.salesmanagement.rest.dto.AuthorizationDto;
import com.alexandreloiola.salesmanagement.rest.form.AuthorizationForm;
import com.alexandreloiola.salesmanagement.rest.form.AuthorizationUpdateForm;
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
public class AuthorizationService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    public List<AuthorizationDto> getAllAuthorization() {
        List<AuthorizationModel> authorizationModelList = authorizationRepository.findAll();
        return convertListToDto(authorizationModelList);
    }

    public AuthorizationDto getAuthorizationById(Long id) {
        try {
            AuthorizationModel authorizationModel = authorizationRepository.findById(id).get();
            return convertModelToDto(authorizationModel);
        }  catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Autorização não encontrado!");
        }
    }

    public AuthorizationDto insertAuthorization(AuthorizationForm authorizationForm) {
        try {
            Optional<AuthorizationModel> byDescription = authorizationRepository.findByDescription(authorizationForm.getDescription());
            if (byDescription.isPresent()) {
                throw new DataIntegrityException("Essa autorização já foi cadastrado");
            }
            AuthorizationModel authorizationModel = convertFormToModel(authorizationForm);
            authorizationModel.setIsActive(true);
            authorizationModel = authorizationRepository.save(authorizationModel);

            return convertModelToDto(authorizationModel);
        }   catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Authorização não foi(foram) devidamente preenchido(s).");
        }
    }

    public AuthorizationDto updateAuthorization(Long id, AuthorizationUpdateForm authorizationUpdateForm) {
        try {
            Optional<AuthorizationModel> authorizationModel = authorizationRepository.findById(id);
            if (authorizationModel.isPresent()) {
                AuthorizationModel authorizationUpdate = authorizationModel.get();
                authorizationUpdate.setDescription(authorizationUpdateForm.getDescription());
                authorizationUpdate.setIsActive(authorizationUpdateForm.getIsActive());
                authorizationUpdate = authorizationRepository.save(authorizationUpdate);
                return convertModelToDto(authorizationUpdate);
            }  else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) da autorização não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Esse produto não está cadastrado");
        }
    }

    public void deleteAuthorization(Long id) {
        try {
            if (authorizationRepository.existsById(id)) {
                authorizationRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar a authorização");
        }
    }

    public AuthorizationDto convertModelToDto(AuthorizationModel authorizationModel) {
        AuthorizationDto authorizationDto = new AuthorizationDto();

        authorizationDto.setId(authorizationModel.getId());
        authorizationDto.setDescription(authorizationModel.getDescription());
        authorizationDto.setIsActive(authorizationModel.getIsActive());

        return authorizationDto;
    }

    public AuthorizationModel convertFormToModel(AuthorizationForm authorizationForm) {
        AuthorizationModel authorizationModel = new AuthorizationModel();
        authorizationModel.setDescription(authorizationForm.getDescription());
        return authorizationModel;
    }

    public List<AuthorizationDto> convertListToDto(List<AuthorizationModel> list) {
        List<AuthorizationDto> authorizationDtoList = new ArrayList<>();
        for (AuthorizationModel authorizationModel : list) {
            AuthorizationDto authorizationDto = convertModelToDto(authorizationModel);
            authorizationDtoList.add(authorizationDto);
        }
        return authorizationDtoList;
    }
}
