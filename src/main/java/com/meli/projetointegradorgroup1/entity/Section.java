package com.meli.projetointegradorgroup1.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
/**
 *
 * @author Marco Siqueira
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    @Enumerated(value = EnumType.STRING)
    private StockType stockType;
    @Column(name = "minimum_temperature")
    private String minimumTemperature;
    private Long capacity;

    @ManyToOne
    private Warehouse warehouse;


}
