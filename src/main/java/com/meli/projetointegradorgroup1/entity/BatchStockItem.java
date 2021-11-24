package com.meli.projetointegradorgroup1.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
/**
 *
 * @author Hugo Victor
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BatchStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double volume;
    private double maximumTemperature;
    private double minimumTemperature;

    @ManyToOne
    private Product product;

    @OneToOne
    @JoinColumn(name= "batch_stock_id")
    private BatchStock batchStock;

}


