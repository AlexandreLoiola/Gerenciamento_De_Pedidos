package com.alexandreloiola.salesmanagement.Steps;

import com.alexandreloiola.salesmanagement.model.ProductModel;
import com.alexandreloiola.salesmanagement.repository.ProductRepository;
import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.form.ProductForm;
import com.alexandreloiola.salesmanagement.rest.form.ProductUpdateForm;
import com.alexandreloiola.salesmanagement.service.ProductService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java")
public class ProductServiceSteps {

    private ProductService productService;
    private ProductRepository productRepository;
    private List<ProductModel> productModels;
    private List<ProductDto> productDtos;
    private List<ProductForm> newProducts;
    private List<ProductUpdateForm> updatedProducts;

    @Before
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService();
    }

    @Given("^que o repositório de produtos possui os seguintes produtos:$")
    public void theProductRepositoryHasTheFollowingProducts(List<ProductModel> productModels) {
        this.productModels = productModels;
        Mockito.when(productRepository.findAll()).thenReturn(productModels);
    }

    @When("^o cliente solicita todos os produtos$")
    public void theClientRequestsAllProducts() {
        productDtos = productService.getAllProducts();
    }

    @Then("^a resposta deve conter os seguintes produtos:$")
    public void theResponseShouldContainTheFollowingProducts(List<ProductDto> expectedProductDtos) {
        Assert.assertEquals(expectedProductDtos, productDtos);
    }

    @When("^o cliente solicita o produto com o nome: \"(.*?)\"$")
    public void theClientRequestsTheProductWithName(String name) {
        ProductModel productModel = productModels.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (productModel != null) {
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.of(productModel));
        } else {
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.empty());
        }
    }

    @Then("^a resposta deve conter o produto com o nome: \"(.*?)\"$")
    public void theResponseShouldContainTheProductWithName(String name) {
        ProductDto productDto = productService.getProductById(name);
        Assert.assertEquals(name, productDto.getName());
    }

    @When("^o cliente insere um novo produto com os seguintes detalhes:$")
    public void theClientInsertsANewProductWithTheFollowingDetails(List<ProductForm> newProducts) {
        ProductForm productForm = newProducts.get(0);
        ProductModel newProduct = productService.convertFormToModel(productForm);
        Mockito.when(productRepository.findByName(productForm.getName())).thenReturn(Optional.empty());
        Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);
    }

    @Then("^a resposta deve conter um novo produto com o nome: \"(.*?)\"$")
    public void theResponseShouldContainTheNewProductWithName(String name) {
        ProductForm productForm = newProducts.get(0);
        ProductDto productDto = productService.insertProduct(productForm);
        Assert.assertEquals(name, productDto.getName());
    }

    @When("^o cliente atualiza o produto com o nome: \"(.*?)\" com os seguintes detalhes:$")
    public void theClientUpdatesTheProductWithName(String name, List<ProductUpdateForm> updatedProducts) {
        ProductUpdateForm productUpdateForm = updatedProducts.get(0);
        ProductModel productModel = productModels.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (productModel != null) {
            productModel.setDescription(productUpdateForm.getDescription());
            productModel.setUnitPrice(productUpdateForm.getUnitPrice());
            productModel.setStockQuantity(productUpdateForm.getStockQuantity());
            productModel.setIsActive(productUpdateForm.getIsActive());
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.of(productModel));
            Mockito.when(productRepository.save(productModel)).thenReturn(productModel);
        } else {
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.empty());
        }
    }

    @Then("^a resposta deve conter o produto com o nome: \"(.*?)\"$")
    public void theResponseShouldContainTheUpdatedProductWithName(String name) {
        ProductUpdateForm productUpdateForm = updatedProducts.get(0);
        ProductDto productDto = productService.updateProduct(name, productUpdateForm);
        Assert.assertEquals(name, productDto.getName());
        Assert.assertEquals(productUpdateForm.getDescription(), productDto.getDescription());
        Assert.assertEquals(productUpdateForm.getUnitPrice(), productDto.getUnitPrice());
        Assert.assertEquals(productUpdateForm.getStockQuantity(), productDto.getStockQuantity());
        Assert.assertEquals(productUpdateForm.getIsActive(), productDto.getIsActive());
    }

    @When("^o cliente deleta o produto com o nome: \"(.*?)\"$")
    public void theClientDeletesTheProductWithName(String name) {
        ProductModel productModel = productModels.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (productModel != null) {
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.of(productModel));
            Mockito.doNothing().when(productRepository).deleteById(productModel.getId());
        } else {
            Mockito.when(productRepository.findByName(name)).thenReturn(Optional.empty());
        }
    }

    @Then("^o produto com o nome: \"(.*?)\" deve ser deletado do repositório$")
    public void theProductWithNameShouldBeDeletedFromTheRepository(String name) {
        productService.deleteProduct(name);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
