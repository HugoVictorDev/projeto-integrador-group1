package com.meli.projetointegradorgroup1.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "batch_number", nullable = false)
    private Long batchStockNumber;
    private Double currentTemperature;
    private Double minimumTemperature;
    private Double maximumTemperature;
    private String initialQuality;
    private String currentQuality;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private int quantity;
    private double volume;

    @OneToOne(mappedBy =  "batchStock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatchStockItem batchStockItem;

    @OneToOne
    private Seller seller;

    @Override
    public boolean equals(Object o) {
        BatchStock bs = (BatchStock) o;
        return bs.batchStockNumber.equals(this.batchStockNumber);
    }



}