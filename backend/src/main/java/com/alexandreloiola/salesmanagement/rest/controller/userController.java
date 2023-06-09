package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.UserDto;
import com.alexandreloiola.salesmanagement.rest.form.UserForm;
import com.alexandreloiola.salesmanagement.rest.form.UserUpdateForm;
import com.alexandreloiola.salesmanagement.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok().body(userDtoList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDto> getUserByName(
            @PathVariable("name") String name
    ) {
        UserDto userDto = userService.getUserByName(name);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(
            @Valid @RequestBody UserForm userForm
    ) {
        UserDto userDto  = userService.login(userForm.getEmail(), userForm.getPassword());
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> insertUser(
            @Valid @RequestBody UserForm userForm
    ) {
       UserDto userDto = userService.insertUser(userForm);
       return ResponseEntity.ok().body(userDto);
    }

    @PutMapping("/{name}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("name") String name,
            @Valid @RequestBody UserUpdateForm userUpdateForm
    ) {
        UserDto userDto = userService.updateUser(name, userUpdateForm);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
