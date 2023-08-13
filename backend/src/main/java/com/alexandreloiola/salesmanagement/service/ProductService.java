package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.ProductModel;
import com.alexandreloiola.salesmanagement.repository.ProductRepository;
import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.form.ProductForm;
import com.alexandreloiola.salesmanagement.rest.form.ProductUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        return convertListToDto(productModelList);
    }

    public ProductDto getProductById(String name) {
        try {
            ProductModel productModel = productRepository.findByName(name).get();
            return convertModelToDto(productModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Produto não encontrado!");
        }
    }

    public ProductDto insertProduct(ProductForm productForm) {
        try {
            Optional<ProductModel> byName = productRepository.findByName(productForm.getName());
            if (byName.isPresent()) {
                throw new DataIntegrityException("Esse nome já foi cadastrado em um produto");
            }

            ProductModel newProduct = convertFormToModel(productForm);
            newProduct = productRepository.save(newProduct);

            return convertModelToDto(newProduct);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do produto não foi(foram) devidamente preenchido(s).");
        }
    }

    public ProductDto updateProduct(String id, ProductUpdateForm productUpdateForm) {
        try {
            Optional<ProductModel> productModel = productRepository.findByName(id);
            if (productModel.isPresent()) {
                ProductModel productUpdated = productModel.get();
                productUpdated.setName(productUpdateForm.getName());
                productUpdated.setDescription(productUpdated.getDescription());
                productUpdated.setUnitPrice(productUpdateForm.getUnitPrice());
                productUpdated.setStockQuantity(productUpdateForm.getStockQuantity());
                productUpdated.setIsActive(productUpdateForm.getIsActive());

                productUpdated = productRepository.save(productUpdated);
                return convertModelToDto(productUpdated);
            } else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do produto não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Esse produto não está cadastrado");
        }
    }

    public void deleteProduct(String id) {
        try {
            Long byId = productRepository.findByName(id).get().getId();
            if (productRepository.existsById(byId)) {
                productRepository.deleteById(byId);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o produto");
        }
    }

    public ProductModel convertFormToModel(ProductForm productForm) {
        ProductModel productModel = new ProductModel();

        productModel.setName(productForm.getName());
        productModel.setDescription(productForm.getDescription());
        productModel.setUnitPrice(productForm.getUnitPrice());
        productModel.setStockQuantity(productForm.getStockQuantity());
        productModel.setIsActive(productForm.getIsActive());

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
