package com.meli.projetointegradorgroup1.entity;
import com.meli.projetointegradorgroup1.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "batch_number", nullable = false)
    private Long batchStockNumber;
    private double currentTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
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