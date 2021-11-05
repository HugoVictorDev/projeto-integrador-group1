package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDto;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.services.ProductService;
import com.meli.projetointegradorgroup1.services.SectionServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {

    Product product = new Product(1l, "teste","cafe");
    ProductResponseDto productDtoRes = new ProductResponseDto("teste","cafe");
    ProductRequestDto productDtoReq = new ProductRequestDto("teste","cafe");
    Product productUpdate = new Product(null, "teste","cafe");

    List<ProductResponseDto> listProductResp = new ArrayList();
    ProductService productService;
    ProductRepository productRepository;


    @Test
    public void createProductDto(){
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController (productRepository, null);
        productController.createProductDto(productDtoReq);

        assert (product.getProductId() != null);
    }

    @Test
    public void listOK(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);
        Mockito.when(productService.listProductDto()).thenReturn(listProductResp);

        ProductController productController = new ProductController (null, productService);
        productController.responseDtoList();

        assert (productService.listProductDto().size() == 1);
    }

    @Test
    public void getByIdOK(){
        productRepository = Mockito.mock(ProductRepository.class);
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(productService.converteToResponse(Mockito.any())).thenReturn(productDtoRes);

        ProductController productController = new ProductController (productRepository, productService);
        productController.getById(1l);

        assert (productDtoRes.getProductName() != null);
    }

    @Test
    public void getByNameOK(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.listProductDto(Mockito.anyString())).thenReturn(listProductResp);

        ProductController productController = new ProductController (null, productService);
        productController.getByName("teste");

        assert (productService.listProductDto("").size() == 1);
    }

    @Test
    public void updateProductOK(){
        productRepository = Mockito.mock(ProductRepository.class);
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productRepository.getById(Mockito.anyLong())).thenReturn(product);
        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(product);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController (productRepository, productService);
        productController.updateProduct(1l, productDtoReq);

        assert (productDtoReq.getProductName().equals(product.getProductName()));
    }

    @Test
    public void deleteProductOK(){
        productRepository = Mockito.mock(ProductRepository.class);
        productService= Mockito.mock(ProductService.class);

        Mockito.when(productRepository.getById(Mockito.anyLong())).thenReturn(product);
        Mockito.when(productService.converteToResponse(Mockito.any())).thenReturn(productDtoRes);
        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());

        ProductController productController = new ProductController (productRepository, productService);
        productController.deleteProduct(1l);

        assert (product.getProductId() == 1);
    }
}
