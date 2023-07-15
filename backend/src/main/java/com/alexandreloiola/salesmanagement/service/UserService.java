package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.UserModel;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.UserDto;
import com.alexandreloiola.salesmanagement.rest.form.UserForm;
import com.alexandreloiola.salesmanagement.rest.form.UserUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileUserRepository profileUserRepository;

    public List<UserDto> getAllUsers() {
        List<UserModel> userDtoList = userRepository.findAll();
        return convertListToDto(userDtoList);
    }

    public UserDto getUserByName(String name) {
        try {
            UserModel userModel = userRepository.findByEmail(name).get();
            return convertModelToDto(userModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Usuário não encontrado!");
        }
    }

    public UserDto login(String email, String password) {
        try {
            UserModel userModel = userRepository.findByEmail(email).orElseThrow(
                    () -> new ObjectNotFoundException("Credenciais de login inválidas")
            );
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!(passwordEncoder.matches(password, userModel.getPassword()))) {
                throw new ObjectNotFoundException("Credenciais de login inválidas");
            }
            return convertModelToDto(userModel);
        } catch (ObjectNotFoundException err) {
            throw new ObjectNotFoundException("Credenciais de login inválidas");
        }
    }

    @Transactional
    public UserDto insertUser(UserForm userForm) {
        try {
            UserModel newUser = convertFormToModel(userForm);
            newUser.setIsActive(true);
            newUser = userRepository.save(newUser);

            return convertModelToDto(newUser);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do usuário não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public UserDto updateUser(String name, UserUpdateForm userUpdateForm) {
        try {
            Optional<UserModel> userModel = userRepository.findByEmail(name);
            if (userModel.isPresent()) {
                UserModel userUpdated = userModel.get();
                userUpdated.setPassword(userUpdateForm.getPassword());
                userUpdated.setIsActive(userUpdateForm.getIsActive());

                userRepository.save(userUpdated);
                return convertModelToDto(userUpdated);
            } else {
                throw new DataIntegrityViolationException("O usuário não pode ser atualizado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Usuário não foi(foram) devidamente preenchido(s).");
        }
    }

    public void deleteUser(long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não é possível deletar um usuário");
        }
    }

    private UserModel convertFormToModel(UserForm userform) {
        UserModel userModel = new UserModel();

        userModel.setEmail(userform.getEmail());
        userModel.setPassword(new BCryptPasswordEncoder().encode(userform.getPassword()));
        return userModel;
    }

    private UserDto convertModelToDto(UserModel userModel) {
        UserDto userDto = new UserDto();

        userDto.setEmail(userModel.getEmail());
        userDto.setIsActive(userModel.getIsActive());

        return userDto;
    }

    private List<UserDto> convertListToDto(List<UserModel> list) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserModel userModel : list) {
            UserDto userDto = this.convertModelToDto(userModel);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}