package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.service.ProductService;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Service
@Data
public class ProductDTO {

    private Long productId;

    @NotBlank
    @NotNull
    @NotEmpty (message = "Campo Product Name é obrigatório")
    private String productName;

    //data de producao
    @NotBlank
    @NotNull
    @NotEmpty(message = "Campo Manufacturing Date é obrigatório")
    private String manufacturingDate;

    //horario da producao
    @NotBlank
    @NotNull
    @NotEmpty(message = "Campo Manufacturing Time é obrigatório")
    private String manufacturingTime;

    //data de vencimento
    @NotBlank
    @NotNull
    @NotEmpty(message = "Campo Due Date é obrigatório")
    private String dueDate;

    @NotEmpty(message = "sellerId obrigatório")
    private String sellerId;

    @NotEmpty(message = "batchstockitemId obrigatório")
    private String batchstockitemId;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String productName, String manufacturingDate, String manufacturingTime, String dueDate) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
    }

    public ProductDTO(Long productId, String productName, String manufacturingDate, String manufacturingTime, String dueDate, String sellerId, String batchstockitemId) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
        this.sellerId = sellerId;
        this.batchstockitemId = batchstockitemId;
    }

    public static Product converte(ProductDTO productDTO){
        return new Product().withProductName(productDTO.getProductName())
                .withManufacturingDate(ProductService.maskmanufacturingDate(productDTO.getManufacturingDate()))
                .withManufacturingTime(productDTO.getManufacturingTime())
                .withDueDate(productDTO.getDueDate())
                .SellerId(Long.parseLong(productDTO.getSellerId()))
                .BatchStockItemId(Long.parseLong(productDTO.getBatchstockitemId()));
    }

    public static ProductDTO converte(Product product){
        return new ProductDTO(product.getProductId(),
                product.getProductName(),
                product.getManufacturingDate(),
                product.getManufacturingTime(),
                product.getDueDate(),
                Long.toString(product.getSeller().getSellerId()),
                Long.toString(product.getBatchstockitem().getId()));
    }

}
