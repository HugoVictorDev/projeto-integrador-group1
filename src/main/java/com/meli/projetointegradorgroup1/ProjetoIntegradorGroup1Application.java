package com.meli.projetointegradorgroup1;


import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Inicia o spring boot
@SpringBootApplication
public class ProjetoIntegradorGroup1Application  implements CommandLineRunner {

    // instanciando o produto e a interface productRepository
    @Autowired
    ProductRepository productRepository;

    Product produto1 = new Product("produto1", "produto2");


    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradorGroup1Application.class, args);
    }

    //RUNNN
    @Override
    public void run(String... args) throws Exception {

        //salvando produto1 no banco de dados
        productRepository.save(produto1);

    }
}

