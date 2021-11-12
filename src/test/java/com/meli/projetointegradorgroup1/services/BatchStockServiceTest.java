package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BatchStockServiceTest {

    // -- MOCK DA REPOSITORY
    BatchStockRepository repositoryMock = Mockito.mock(BatchStockRepository.class);

    // -- MASSA PARA OS TESTES
    BatchStock batchStock1 = BatchStock.builder()
                                        .batchStockNumber(1l)
                                        .currentTemperature(0D)
                                        .minimumTemperature(-15D)
                                        .maximumTemperature(0D)
                                        .initialQuality("PRD")
                                        .currentQuality("PRD")
                                        .manufacturingTime(LocalDateTime.now())
                                        .dueDate(LocalDate.now())
                                        .quantity(12)
                                        .volume(12d).build();



    Long batchStockNumberOK = 1L;
    Long batchStockNumberNOK = 2L;

    // - DTOS
    BatchStockRequestDTO batchStockRequestDTO = BatchStockRequestDTO.builder()
                                                                    .batchStockNumber(1l)
                                                                    .currentTemperature(0D)
                                                                    .minimumTemperature(-15D)
                                                                    .maximumTemperature(0D)
                                                                    .initialQuality("PRD")
                                                                    .currentQuality("PRD")
                                                                    .manufacturingTime("15:00:00")
                                                                    .dueDate(LocalDate.now())
                                                                    .quantity(12)
                                                                    .volume(12d).build();
    BatchStockResponseDTO batchStockResponseDTO = BatchStockResponseDTO.builder()
                                                                        .batchStockNumber(1l)
                                                                        .currentTemperature(0D)
                                                                        .minimumTemperature(-15D)
                                                                        .maximumTemperature(0D)
                                                                        .initialQuality("PRD")
                                                                        .currentQuality("PRD")
                                                                        .manufacturingTime(LocalDateTime.now())
                                                                        .dueDate(LocalDate.now())
                                                                        .quantity(12)
                                                                        .volume(12d).build();;


    ArrayList<Seller> sellers = new ArrayList();


    @BeforeEach
    void setUp() {
    }

    @Test
    void saveBS() {
    }

    @Test
    void deleteBS() {
    }

    @Test
    void updateIBO() {
    }

    @Test
    void valida() {
    }

    @Test
    void save() {
    }

    @Test
    void findBatchSotck() {
    }
}