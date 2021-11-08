package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDTO {

    private String name;
    private String cpf;
    private String email;


//    private List<Product> productList;

public SellerResponseDTO(Seller seller){
    this.name = seller.getName();
    this.cpf = seller.getCpf();
    this.email = seller.getEmail();
//    this.productList = seller.getProductList();
}


}
