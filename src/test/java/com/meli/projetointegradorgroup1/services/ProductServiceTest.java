package com.meli.projetointegradorgroup1.services;


import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {
    Product product = new Product(1l, "teste","cafe", StockType.FRESH);
    ProductResponseDTO productDtoRes = new ProductResponseDTO("teste","cafe", StockType.FRESH );
    ProductRequestDTO productDtoReq = new ProductRequestDTO("teste","cafe", StockType.FRESH);
    Product productUpdate = new Product(null, "teste","cafe",StockType.FRESH);

    List<Product> listProduct = new ArrayList();
    ProductService productService;
    ProductRepository productRepository;

    Product productNull = new Product();

    String message = null;

    @Test
    public void listProducAllOk(){
        listProduct.add(product);
        productRepository = Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findAll()).thenReturn(listProduct);

        ProductService productService = new ProductService(productRepository);
        productService.listProductAll();

        assert (listProduct.size() != 0);
    }

    @Test
    public void listProducAllNok(){
        productRepository = Mockito.mock(ProductRepository.class);
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
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findByNameContaining(Mockito.anyString())).thenReturn(listProduct);

        ProductService productService = new ProductService(productRepository);
        productService.listProduct("carne");

        assert (listProduct.size() == 1);
    }

    @Test
    public void listProductIdNok(){
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findByNameContaining(Mockito.anyString())).thenReturn(listProduct);

        ProductService productService = new ProductService(productRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        productService.listProduct("carne");});
        message = "Produto não encontrado";

        assert (exception.getMessage().contains(message));
    }


    @Test
    public void validaNok() {
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(null));

        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
        productService.valida(1l);
        });
        message = "Produto não cadastrado";

        assert (message.contains(exception.getMessage()));
    }


    @Test
    public void validaUpdateOk(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(productUpdate);

        ProductService productService = new ProductService(productRepository);
        productService.validaUpdate(product, productDtoReq);

        assert (productUpdate.getName().equals(productDtoRes.getName()));
    }

    @Test
    public void validaUpdateNok(){
        productService = Mockito.mock(ProductService.class);

        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        productService.validaUpdate(productNull, productDtoReq);});
        message = "Produto não encontrado";

        assert (message.contains(exception.getMessage()));
    }
    @Test
    public void saveOk(){
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        ProductService productService = new ProductService(productRepository);

        assert (productService.save(product).getId() == 1);
    }

    @Test
    public void saveNok(){
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        productService.save(null);});
        message = "Erro na gravação do produto:";

        assert (exception.getMessage().contains(message));
    }

    @Test
    public void converteOk(){
        productService = Mockito.mock(ProductService.class);
        ProductService productService = new ProductService(productRepository);
        productService.convert(productDtoReq);
        assert (productService.convert(productDtoReq).getName().equals(productDtoReq.getName()));
    }

    @Test
    public void converteToDto(){
        productService = Mockito.mock(ProductService.class);
        ProductService productService = new ProductService( null);
        assert (productService.convertToDto(product).getName().equals(product.getName()));
    }

    @Test
    public void deletaByid() {
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());
        ProductService productService = new ProductService(productRepository);
        productService.deletaProduct(1l);

        assert (product.getId() == 1);
        }

    @Test
    public void obtemOk() {
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(product));
        ProductService productService = new ProductService(productRepository);
        productService.obtem(1l);

        assert (product.getId() == 1);
    }

    @Test
    public void obtemNok() {
        productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(null);
        ProductService productService = new ProductService(productRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            productService.obtem(1l);});
        message = "Produto não cadastrado";

        assert (exception.getMessage().contains(message));
    }
}

