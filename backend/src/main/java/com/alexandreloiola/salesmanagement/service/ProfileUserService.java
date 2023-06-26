package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.ProfileModel;
import com.alexandreloiola.salesmanagement.model.ProfileUserModel;
import com.alexandreloiola.salesmanagement.model.UserModel;
import com.alexandreloiola.salesmanagement.repository.ProfileUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProfileUserService {
    @Autowired
    private ProfileUserRepository profileUserRepository;

    public void insertProfileUser(
            String profileType, ProfileModel profileModel, UserModel userModel
    ) {
        try {
            ProfileUserModel profileUserModel = new ProfileUserModel();
            profileUserModel.setProfileType(profileType);
            profileUserModel.setProfileModel(profileModel);
            profileUserModel.setUserModel(userModel);

            profileUserRepository.save(profileUserModel);

        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do Cadastro do cliente não foi(foram) devidamente preenchido(s)."
            );
        }
    }
}
