package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.AuthorizationDto;
import com.alexandreloiola.salesmanagement.rest.form.AuthorizationForm;
import com.alexandreloiola.salesmanagement.rest.form.AuthorizationUpdateForm;
import com.alexandreloiola.salesmanagement.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<AuthorizationDto>> getAllAuthorizations() {
        List<AuthorizationDto> authorizationDtoList = authorizationService.getAllAuthorization();
        return ResponseEntity.ok().body(authorizationDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorizationDto> getAuthorizationById(
            @PathVariable("id") Long id
    ) {
        AuthorizationDto authorizationDto = authorizationService.getAuthorizationById(id);
        return ResponseEntity.ok().body(authorizationDto);
    }

    @PostMapping
    public ResponseEntity<AuthorizationDto> insertAuthorization(
            @Valid @RequestBody AuthorizationForm authorizationForm
            ) {
        AuthorizationDto authorizationDto = authorizationService.insertAuthorization(authorizationForm);
        return ResponseEntity.ok().body(authorizationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorizationDto> updateAuthorization(
            @PathVariable("id") Long id,
            @Valid @RequestBody AuthorizationUpdateForm authorizationUpdateForm
    ) {
        AuthorizationDto authorizationDto = authorizationService.updateAuthorization(id, authorizationUpdateForm);
        return ResponseEntity.ok().body(authorizationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorizationDto> deleteAuthorization(
            @PathVariable("id") Long id
    ) {
        authorizationService.deleteAuthorization(id);
        return ResponseEntity.noContent().build();
    }
}
