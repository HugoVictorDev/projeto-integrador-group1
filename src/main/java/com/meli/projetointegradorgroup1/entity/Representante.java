package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cpf;
}