package com.meli.projetointegradorgroup1.controller;
package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.InbounderOrder;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/inbounder")
public class InbounderOrderController {

    @Autowired
    private InbounderOrder inbounderOrder;

    @GetMapping
    public Iterable<inbounderOrder> list(){
        return productRepository.findAll();
    }
}
