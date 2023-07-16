package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.RoleDto;
import com.alexandreloiola.salesmanagement.rest.form.RoleForm;
import com.alexandreloiola.salesmanagement.rest.form.RoleUpdateForm;
import com.alexandreloiola.salesmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRole() {
        List<RoleDto> roleDtoList = roleService.getAllRole();
        return ResponseEntity.ok().body(roleDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(
            @PathVariable("id") Long id
    ) {
        RoleDto roleDto = roleService.getRoleById(id);
        return ResponseEntity.ok().body(roleDto);
    }

    @PostMapping
    public ResponseEntity<RoleDto> insertRole(
            @Valid @RequestBody RoleForm roleForm
            ) {
        RoleDto roleDto = roleService.insertRole(roleForm);
        return ResponseEntity.ok().body(roleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(
            @PathVariable("id") Long id,
            @Valid @RequestBody RoleUpdateForm roleUpdateForm
    ) {
        RoleDto roleDto = roleService.updateRole(id, roleUpdateForm);
        return ResponseEntity.ok().body(roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoleDto> deleteRole(
            @PathVariable("id") Long id
    ) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
