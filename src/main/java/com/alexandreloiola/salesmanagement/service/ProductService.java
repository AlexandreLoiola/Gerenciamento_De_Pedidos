package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.ProductModel;
import com.alexandreloiola.salesmanagement.model.SellerModel;
import com.alexandreloiola.salesmanagement.repository.ProductRepository;
import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.dto.SellerDto;
import com.alexandreloiola.salesmanagement.rest.form.ProductForm;
import com.alexandreloiola.salesmanagement.rest.form.ProductUpdateForm;
import com.alexandreloiola.salesmanagement.rest.form.SellerUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        return convertListToDto(productModelList);
    }

    public ProductDto getProductById(Long id) {
        ProductModel productModel = productRepository.findById(id).get();
        return convertModelToDto(productModel);
    }

    public ProductDto insertProduct(ProductForm productForm) {
        ProductModel newProduct = convertFormToModel(productForm);
        newProduct = productRepository.save(newProduct);

        return convertModelToDto(newProduct);
    }

    public ProductDto updateProduct(Long id, ProductUpdateForm productUpdateForm) {
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isPresent()) {
            ProductModel productUpdated = productModel.get();
            productUpdated.setName(productUpdateForm.getName());
            productUpdated.setDescription(productUpdated.getDescription());
            productUpdated.setUnitPrice(productUpdateForm.getUnitPrice());
            productUpdated.setIsActive(productUpdateForm.getIsActive());

            productRepository.save(productUpdated);
            return convertModelToDto(productUpdated);
        } else {
            throw new DataIntegrityViolationException("O vendedor não pode ser atualizado");
        }
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("O produto não pode ser deletado");
        }
    }

    private ProductModel convertFormToModel(ProductForm productForm) {
        ProductModel productModel = new ProductModel();

        productModel.setName(productForm.getName());
        productModel.setDescription(productForm.getDescription());
        productModel.setUnitPrice(productForm.getUnitPrice());
        productModel.setIsActive(productForm.getIsActive());

        return productModel;
    }

    private ProductDto convertModelToDto(ProductModel productModel) {
        ProductDto productDto = new ProductDto();

        productDto.setName(productModel.getName());
        productDto.setDescription(productModel.getDescription());
        productDto.setUnitPrice(productModel.getUnitPrice());
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
