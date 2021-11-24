package com.meli.projetointegradorgroup1.services;


import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Marco Siqueiraa
 */

public class ProductServiceTest {
    Product product = new Product(1l, "teste","cafe", StockType.FRESH);
    ProductResponseDTO productDtoRes = new ProductResponseDTO("teste","cafe", StockType.FRESH );
    ProductRequestDTO productDtoReq = new ProductRequestDTO("teste","cafe", StockType.FRESH);
    Product productUpdate = new Product(null, "teste","cafe",StockType.FRESH);

    List<Product> listProduct = new ArrayList();
    ProductService productService = Mockito.mock(ProductService.class);
    ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    Product productNull = new Product();

    String message = null;
    String uri = "http//Mock";

    @Test
    public void listProducAllOk(){
        listProduct.add(product);
        Mockito.when(productRepository.findAll()).thenReturn(listProduct);
        ProductService productService = new ProductService(productRepository);
        productService.listProductAll();
        assert (listProduct.size() != 0);
    }

    @Test
    public void listProducAllNok(){
        Mockito.when(productRepository.findAll()).thenReturn(listProduct);
        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            productService.listProductAll();});
        message = "Não existem produtos cadastrados";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void listProductIdOk(){
        listProduct.add(product);
        Mockito.when(productRepository.findByNameContaining(Mockito.anyString())).thenReturn(listProduct);
        ProductService productService = new ProductService(productRepository);
        productService.listProduct("carne");
        assert (listProduct.size() == 1);
    }

    @Test
    public void listProductIdNok(){
        Mockito.when(productRepository.findByNameContaining(Mockito.anyString())).thenReturn(listProduct);
        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            productService.listProduct("carne");});
        message = "Produto não encontrado";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void validaNok() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.valida(1l);});
        message = "Produto não cadastrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(productUpdate);
        ProductService productService = new ProductService(productRepository);
        productService.validaUpdate(product, productDtoReq);
        assert (productUpdate.getName().equals(productDtoRes.getName()));
    }

    @Test
    public void validaUpdateNok(){
        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            productService.validaUpdate(productNull, productDtoReq);});
        message = "Produto não encontrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void saveOk(){
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        ProductService productService = new ProductService(productRepository);
        ResponseEntity<Object> sevaReturn = productService.save(product, uriBuilder);
        Assert.assertTrue(sevaReturn.getStatusCodeValue() == 201 );

    }

    @Test
    public void saveNok(){
        Mockito.when(productRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        ProductService productService = new ProductService(productRepository);
        ResponseEntity<Object> sevaReturn = productService.save(product, null);
        Assert.assertTrue(sevaReturn.getStatusCodeValue() == 400 );
    }

    @Test
    public void converteOk(){
        ProductService productService = new ProductService(productRepository);
        productService.convert(productDtoReq);
        assert (productService.convert(productDtoReq).getName().equals(productDtoReq.getName()));
    }

    @Test
    public void converteToDto(){
        ProductService productService = new ProductService( null);
        assert (productService.convertToDto(product).getName().equals(product.getName()));
    }

    @Test
    public void deletaByid() {
        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());
        ProductService productService = new ProductService(productRepository);
        productService.deletaProduct(1l);
        assert (product.getId() == 1);
    }

    @Test
    public void obtemOk() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(product));
        ProductService productService = new ProductService(productRepository);
        productService.obtem(1l);
        assert (product.getId() == 1);
    }

    @Test
    public void obtemNok() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(null);
        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            productService.obtem(1l);});
        message = "Produto não cadastrado";
        assert (exception.getMessage().contains(message));
    }
}

