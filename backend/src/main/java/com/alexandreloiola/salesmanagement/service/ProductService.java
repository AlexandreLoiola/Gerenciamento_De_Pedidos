package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.ProductModel;
import com.alexandreloiola.salesmanagement.repository.ProductRepository;
import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.form.ProductForm;
import com.alexandreloiola.salesmanagement.rest.form.ProductUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.ProductAlreadyExistsException;
import com.alexandreloiola.salesmanagement.service.exceptions.ProductInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.ProductNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.ProductUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        return convertListToDto(productModelList);
    }

    private ProductModel findProductModelByName(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("O produto %s não foi encontrado", productName))
                );
    }

    public ProductDto getProductByName(String productName) {
        ProductModel productModel = findProductModelByName(productName);
        return convertModelToDto(productModel);
    }

    @Transactional
    public ProductDto insertProduct(ProductForm productForm) {
        if (productRepository.findByName(productForm.getName()).isPresent()) {
            throw new ProductAlreadyExistsException(
                    String.format("O produto %s já está cadastrado", productForm.getName())
            );
        }
        try {
            ProductModel newProduct = convertFormToModel(productForm);
            newProduct.setIsActive(true);
            newProduct = productRepository.save(newProduct);
            return convertModelToDto(newProduct);
        } catch (DataIntegrityViolationException err) {
            throw new ProductInsertException(
                    String.format("Falha ao cadastrar o produto %s. Verifique se os dados estão corretos", productForm.getName())
            );
        }
    }

    @Transactional
    public ProductDto updateProduct(String productName, ProductUpdateForm productUpdateForm) {
        ProductModel productUpdated = findProductModelByName(productName);
        productUpdated.setName(productUpdateForm.getName());
        productUpdated.setDescription(productUpdateForm.getDescription());
        productUpdated.setUnitPrice(productUpdateForm.getUnitPrice());
        productUpdated.setStockQuantity(productUpdateForm.getStockQuantity());
        productUpdated.setIsActive(productUpdateForm.getIsActive());
        try {
            productUpdated = productRepository.save(productUpdated);
            return convertModelToDto(productUpdated);
        } catch (DataIntegrityViolationException err) {
            throw new ProductUpdateException(
                    String.format("Falha ao atualizar o produto %s. Verifique se os dados estão corretos", productName)
            );
        }
    }

    @Transactional
    public void deleteProduct(String productName) {
        ProductModel productModel = findProductModelByName(productName);
        Long productId = productModel.getId();
        productRepository.deleteById(productId);
    }

    private ProductModel convertFormToModel(ProductForm productForm) {
        ProductModel productModel = new ProductModel();
        productModel.setName(productForm.getName());
        productModel.setDescription(productForm.getDescription());
        productModel.setUnitPrice(productForm.getUnitPrice());
        productModel.setStockQuantity(productForm.getStockQuantity());
        return productModel;
    }

    private ProductDto convertModelToDto(ProductModel productModel) {
        ProductDto productDto = new ProductDto();
        productDto.setName(productModel.getName());
        productDto.setDescription(productModel.getDescription());
        productDto.setUnitPrice(productModel.getUnitPrice());
        productDto.setStockQuantity(productModel.getStockQuantity());
        productDto.setIsActive(productModel.getIsActive());
        return productDto;
    }

    private List<ProductDto> convertListToDto(List<ProductModel> list) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductModel productModel : list) {
            ProductDto productDto = this.convertModelToDto(productModel);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }
}