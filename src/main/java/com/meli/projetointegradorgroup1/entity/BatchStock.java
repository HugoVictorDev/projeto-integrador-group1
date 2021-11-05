
package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Accessors(chain = true)
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
    private double maximumTemperature; //Inseri hoje na entity, pois so tinha no DTO TODO
    private String initialQuality;
    private String currentQuality;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private int quantity; //Inseri hoje na entity, pois so tinha no DTO TODO
    private double volume; //Inseri hoje na entity, pois so tinha no DTO TODO

    @OneToOne(mappedBy =  "batchStock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatchStockItem batchStockItem;

    @OneToOne
    private Seller seller;

    @ManyToOne
    private InBoundOrder inboundOrder;


}