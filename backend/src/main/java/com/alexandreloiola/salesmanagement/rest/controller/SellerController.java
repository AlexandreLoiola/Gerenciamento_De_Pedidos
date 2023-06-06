package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.SellerDto;
import com.alexandreloiola.salesmanagement.rest.form.SellerForm;
import com.alexandreloiola.salesmanagement.rest.form.SellerUpdateForm;
import com.alexandreloiola.salesmanagement.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers() {
        List<SellerDto> sellerDtoList = sellerService.getAllSellers();
        return ResponseEntity.ok().body(sellerDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> getSellerById(@PathVariable("id") long id) {
        SellerDto sellerDto = sellerService.getSellerById(id);
        return ResponseEntity.ok().body(sellerDto);
    }

    @PostMapping
    public ResponseEntity<SellerDto> insertSeller(@Valid @RequestBody SellerForm sellerForm) {
        SellerDto sellerDto = sellerService.insertSeller(sellerForm);
        return ResponseEntity.ok().body(sellerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDto> updateSeller(
            @PathVariable("id") long id,
            @Valid @RequestBody SellerUpdateForm sellerUpdateForm
    ) {
        SellerDto sellerDto = sellerService.updateSeller(id, sellerUpdateForm);
        return ResponseEntity.ok().body(sellerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}