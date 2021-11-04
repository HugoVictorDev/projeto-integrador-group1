package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDto;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {
    Product product = new Product(1l, "teste","cafe");
    ProductResponseDto productDtoRes = new ProductResponseDto("teste","cafe");
    ProductRequestDto productDtoReq = new ProductRequestDto("teste","cafe");
    Product productUpdate = new Product(null, "teste","cafe");


    List<Product> listProduct = new ArrayList();
    ProductService productService;
    ProductRepository productRepository;

    String message = null;

    @Test
    public void listProductOK(){
    listProduct.add(product);

    productRepository = Mockito.mock(ProductRepository.class);

    Mockito.when(productRepository.findAll()).thenReturn(listProduct);

    ProductService productService = new ProductService(productRepository);
    productService.listProductDto("teste");

    assert (listProduct.size() != 0);
    }

    @Test
    public void listProductIdOk(){
        listProduct.add(product);

        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findByProductNameContaining(Mockito.anyString())).thenReturn(listProduct);

        ProductService productService = new ProductService(productRepository);
        productService.listProductDto();

        assert (listProduct.size() != 0);
    }


    @Test
    public void validaOk(){
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findByProductId(Mockito.anyLong())).thenReturn(null);

        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        productService.valida(1l);});
        message = "Produto não cadastrado";

        assert (message.contains(exception.getMessage()));

    }
    @Test
    public void productDtoByIdOk(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.productDtoById(Mockito.any())).thenReturn(productDtoRes);
        ProductService productService = new ProductService(null);
        productService.productDtoById(product);

        assert (product.getProductName().equals(productDtoRes.getProductName()));
    }
    @Test
    public void validaUpdateOk(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(productUpdate);
        ProductService productService = new ProductService(productRepository);
        productService.validaUpdate(java.util.Optional.ofNullable(product), productDtoReq);

        assert (productUpdate.getProductName().equals(productDtoRes.getProductName()));
    }

    @Test
    public void validaUpdateNok(){
        productService = Mockito.mock(ProductService.class);

        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        productService.validaUpdate(null, productDtoReq);});
        message = "Produto não encontrado";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void convertEntityToDtoRequestOk(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.convertEntityToDtoRequest(Mockito.any())).thenReturn(productDtoReq);
        ProductService productService = new ProductService(null);
        productService.convertEntityToDtoRequest(product);

        assert (product.getProductName().equals(productDtoReq.getProductName()));
    }
}
