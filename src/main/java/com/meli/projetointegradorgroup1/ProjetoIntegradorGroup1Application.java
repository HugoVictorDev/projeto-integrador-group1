package com.meli.projetointegradorgroup1;


import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Inicia o spring boot
@SpringBootApplication
public class ProjetoIntegradorGroup1Application  {



    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradorGroup1Application.class, args);
    }


}

