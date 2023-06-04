package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.UserModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.repository.UserRepository;
import com.alexandreloiola.salesmanagement.rest.dto.UserDto;
import com.alexandreloiola.salesmanagement.rest.form.UserForm;
import com.alexandreloiola.salesmanagement.rest.form.UserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<UserDto> getAllUsers() {
        List<UserModel> userDtoList = userRepository.findAll();
        return convertListToDto(userDtoList);
    }

    public UserDto getUserByName(String name) {
        UserModel userModel = userRepository.findByName(name).get();
        return convertModelToDto(userModel);
    }

    public UserDto insertUser(UserForm userForm) {
        UserModel newUser = convertFormToModel(userForm);
        newUser.setIsActive(true);
        newUser = userRepository.save(newUser);

        return convertModelToDto(newUser);
    }

    public UserDto updateUser(String name, UserUpdateForm userUpdateForm) {
        Optional<UserModel> userModel = userRepository.findByName(name);
        if (userModel.isPresent()) {
            UserModel userUpdated = userModel.get();
            userUpdated.setPassword(userUpdateForm.getPassword());
            userUpdated.setIsActive(userUpdateForm.getIsActive());

            userRepository.save(userUpdated);
            return convertModelToDto(userUpdated);
        } else {
            throw new DataIntegrityViolationException("O usuário não pode ser atualizado");
        }
    }

    public void deleteUser(long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("O cliente não pode ser deletado");
        }
    }

    private UserModel convertFormToModel(UserForm userform) {
        UserModel userModel = new UserModel();

        CustomerModel customerModel = customerRepository.findByName(userform.getName()).get();
        userModel.setCustomerModel(customerModel);

        userModel.setName(userform.getName());
        userModel.setPassword(new BCryptPasswordEncoder().encode(userform.getPassword()));

        return userModel;
    }

    private UserDto convertModelToDto(UserModel userModel) {
        UserDto userDto = new UserDto();

        userDto.setName(userModel.getName());
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