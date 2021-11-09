package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {

    Product product = new Product(1l, "teste","cafe");
    ProductResponseDTO productDtoRes = new ProductResponseDTO("teste","cafe");
    ProductRequestDTO productDtoReq = new ProductRequestDTO("teste","cafe");

    List<ProductResponseDTO> listProductResp = new ArrayList();
    ProductService productService;


    @Test
    public void createProductDto(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.save(Mockito.any())).thenReturn(product);
        Mockito.when(productService.converte(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController (productService);
        productController.createProduct(productDtoReq);

        assert(product.getId() != null);
    }

    @Test
    public void listProductOk(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);
        Mockito.when(productService.listProductAll()).thenReturn(listProductResp);

        ProductController productController = new ProductController (productService);
        productController.listProduct();

        assert (productService.listProductAll().size() == 1);
    }

    @Test
    public void getByIdOK(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.converteToDto(Mockito.any())).thenReturn(productDtoRes);
        Mockito.when(productService.obtem(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController(productService);
        productController.getById(1l);

        assert (productDtoRes.getProductName() != null);
    }

    @Test
    public void getByNameOK(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.listProduct(Mockito.anyString())).thenReturn(listProductResp);

        ProductController productController = new ProductController (productService);
        productController.getByName("teste");

        assert (productService.listProduct("").size() == 1);
    }

    @Test
    public void updateProductOK(){
         productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.obtem(Mockito.anyLong())).thenReturn(product);
        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(product);
        Mockito.when(productService.save(Mockito.any())).thenReturn(product);
        Mockito.when(productService.converteToDto(Mockito.any())).thenReturn(productDtoRes);

        ProductController productController = new ProductController (productService);
        productController.updateProduct(1l, productDtoReq);

        assert (productDtoReq.getName().equals(product.getName()));
    }

    @Test
    public void deleteProductOK(){
        productService= Mockito.mock(ProductService.class);

        Mockito.when(productService.obtem(Mockito.any())).thenReturn(product);
        Mockito.doNothing().when(productService).deletaProduct(Mockito.anyLong());
        Mockito.when(productService.converteToDto(Mockito.any())).thenReturn(productDtoRes);

        ProductController productController = new ProductController (productService);
        productController.deleteProduct(1l);

        assert (product.getId() == 1);
    }
}
