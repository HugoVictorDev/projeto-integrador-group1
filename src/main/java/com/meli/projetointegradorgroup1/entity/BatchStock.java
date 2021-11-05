package com.meli.projetointegradorgroup1.entity;
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
//conjunto de lote
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

    @ManyToOne
    private InBoundOrder inboundOrder;


}