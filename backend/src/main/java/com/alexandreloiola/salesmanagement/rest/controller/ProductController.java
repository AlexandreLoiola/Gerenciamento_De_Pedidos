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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById (
            @PathVariable("id") String id
    ) {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insertProduct (
            @Valid @RequestBody ProductForm productForm
    ) {
        ProductDto productDto = productService.insertProduct(productForm);
        return ResponseEntity.ok().body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct (
            @PathVariable("id") String id,
            @Valid @RequestBody ProductUpdateForm productUpdateForm
    ) {
        ProductDto productDto = productService.updateProduct(id, productUpdateForm);
        return  ResponseEntity.ok().body(productDto);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteProduct (
            @PathVariable("id") String id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
