package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.ProfileModel;
import com.alexandreloiola.salesmanagement.model.UserModel;
import com.alexandreloiola.salesmanagement.repository.ProfileRepository;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.dto.ProfileDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    ProfileUserService profileUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ProfileDto registerProfile(ProfileRegisterForm profileRegisterForm) {
        try {
            ProfileForm profileForm = new ProfileForm();
            profileForm.setName(profileRegisterForm.getName());
            profileForm.setEmail(profileRegisterForm.getEmail());
            profileForm.setBirthDate(profileRegisterForm.getBirthDate());
            profileForm.setCpf(profileRegisterForm.getCpf());

            UserForm userForm = new UserForm();
            userForm.setEmail(profileRegisterForm.getEmail());
            userForm.setPassword(profileRegisterForm.getPassword());

            ProfileDto profileDto = insertProfile(profileForm);
            userService.insertUser(userForm);

            Optional<ProfileModel> profileModel = profileRepository.findByCpf(profileRegisterForm.getCpf());
            if (!profileModel.isPresent()) {
                throw new DataIntegrityException("Esse perfil não existe");
            }

            Optional<UserModel> userModel = userRepository.findByEmail(profileRegisterForm.getEmail());
            if (!userModel.isPresent()) {
                throw new DataIntegrityException("Esse perfil não existe");
            }

            profileUserService.insertProfileUser(
                    profileRegisterForm.getProfileType(), profileModel.get(), userModel.get()
            );

            return profileDto;
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do Cadastro do cliente não foi(foram) devidamente preenchido(s)."
            );
        }
    };

    public List<ProfileDto> getAllProfile() {
        List<ProfileModel> profileList = profileRepository.findAll();
        return convertListToDto(profileList);
    }

    public ProfileDto getProfileByCpf(String cpf) {
        try {
            ProfileModel profileModel = profileRepository.findByCpf(cpf).get();
            return convertModelToDto(profileModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("Perfil não encontrado!");
        }
    }

    @Transactional
    public ProfileDto insertProfile(ProfileForm profileForm) {
        try {
            ProfileModel newProfile = convertFormToModel(profileForm);
            Optional<ProfileModel> byCpf = profileRepository.findByCpf(newProfile.getCpf());

            if (byCpf.isPresent()) {
                throw new DataIntegrityException("Esse cpf já foi cadastrado em um perfil");
            }

            newProfile.setIsActive(true);

            newProfile = profileRepository.save(newProfile);
            return convertModelToDto(newProfile);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do perfil não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public ProfileDto updateProfile(String cpf, ProfileUpdateForm profileUpdateForm) {
        try {
            Optional<ProfileModel> profileModel = profileRepository.findByCpf(cpf);
            if (profileModel.isPresent()) {
                ProfileModel profileUpdated = profileModel.get();
                profileUpdated.setName(profileUpdateForm.getName());
                profileUpdated.setBirthDate(profileUpdateForm.getBirthDate());
                profileUpdated.setIsActive(profileUpdateForm.getIsActive());

                profileRepository.save(profileUpdated);
                return convertModelToDto(profileUpdated);
            } else {
                throw new DataIntegrityViolationException("O perfil não pode ser atualizado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do perfil não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteProfile(String cpf) {
        try {
            long id = profileRepository.findByCpf(cpf).get().getId();
            if (profileRepository.existsById(id)) {
                profileRepository.deleteById(id);
            } else {
                throw new DataIntegrityViolationException("O perfil não pode ser deletado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o perfil");
        }
    }

    private ProfileModel convertFormToModel(ProfileForm profileForm) {
        ProfileModel profileModel = new ProfileModel();

        profileModel.setName(profileForm.getName());
        profileModel.setEmail(profileForm.getEmail());
        profileModel.setBirthDate(profileForm.getBirthDate());
        profileModel.setCpf(profileForm.getCpf());

        return profileModel;
    }

    private ProfileDto convertModelToDto(ProfileModel profileModel) {
        ProfileDto profileDto = new ProfileDto();

        profileDto.setName(profileModel.getName());
        profileDto.setEmail(profileModel.getEmail());
        profileDto.setBirthDate(profileModel.getBirthDate());
        profileDto.setCpf(profileModel.getCpf());
        profileDto.setIsActive(profileModel.getIsActive());

        return profileDto;
    }

    private List<ProfileDto> convertListToDto(List<ProfileModel> list) {
        List<ProfileDto> profileDtoList = new ArrayList<>();
        for (ProfileModel profileModel : list) {
            ProfileDto profileDto = this.convertModelToDto(profileModel);
            profileDtoList.add(profileDto);
        }
        return profileDtoList;
    }
}

