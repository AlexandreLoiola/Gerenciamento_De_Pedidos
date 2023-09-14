package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.form.ProductForm;
import com.alexandreloiola.salesmanagement.rest.form.ProductUpdateForm;
import com.alexandreloiola.salesmanagement.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productService.getAllProducts();
        return ResponseEntity.ok().body(productDtoList);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ProductDto> getProductByName (
            @PathVariable("productName") String productName
    ) {
        ProductDto productDto = productService.getProductByName(productName);
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insertProduct (
            @Valid @RequestBody ProductForm productForm
    ) {
        ProductDto productDto = productService.insertProduct(productForm);
        return ResponseEntity.ok().body(productDto);
    }

    @PutMapping("/{productName}")
    public ResponseEntity<ProductDto> updateProduct (
            @PathVariable("productName") String productName,
            @Valid @RequestBody ProductUpdateForm productUpdateForm
    ) {
        ProductDto productDto = productService.updateProduct(productName, productUpdateForm);
        return  ResponseEntity.ok().body(productDto);
    }

    @DeleteMapping("/{productName}")
    public  ResponseEntity<Void> deleteProduct (
            @PathVariable("productName") String productName
    ) {
        productService.deleteProduct(productName);
        return ResponseEntity.noContent().build();
    }
}
