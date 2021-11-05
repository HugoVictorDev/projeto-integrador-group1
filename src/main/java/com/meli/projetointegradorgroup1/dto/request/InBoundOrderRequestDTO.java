package com.meli.projetointegradorgroup1.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.services.ProductService;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import com.meli.projetointegradorgroup1.services.SectionServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InBoundOrderRequestDTO {

        private Long orderNumber;
        private LocalDate orderDate;
        @JsonProperty(value =  "seller_id")
        private Long sellerId; // falta buildar TODO
        @JsonProperty(value = "section")
        private SectionForInboundDTO sectionForInboundDTO;
        @JsonProperty(value= "batchStockList")
        private List<BatchStockRequestDTO> batchStockDTOList;

        private Long representanteId;



        public InBoundOrder convertedto(RepresentativeServices representativeServices, SectionServices sectionServices, ProductService productService){
            try{
                InBoundOrder inboundOrder = null;
                inboundOrder = InBoundOrder.builder()
                        .orderDate(this.orderDate)
                        .representative(representativeServices.obter(this.representanteId))
                        .orderNumber(this.orderNumber)
                        .section(sectionServices.obterSection(this.sectionForInboundDTO.getSectionId()))
                        .batchStock(converte(batchStockDTOList, productService)).build();


                return inboundOrder;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

        }



    public List<BatchStock> converte(List<BatchStockRequestDTO> dtos, ProductService productService){
        List<BatchStock> resultList = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss").parse(dto.getManufacturingTime()).getTime()
        for (BatchStockRequestDTO dto: dtos) {
            BatchStock batchStock = null;
            batchStock = BatchStock.builder()
                    .batchStockNumber(dto.getBatchStockNumber())
                    .dueDate(dto.getDueDate())
                    .manufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(),fmt))
                    .currentQuality(dto.getCurrentQuality())
                    .initialQuality(dto.getInitialQuality())
                    .minimumTemperature(dto.getMinimumTemperature())
                    .currentTemperature(dto.getMaximumTemperature())
                    //falta biuldar o seller do batchStock TODO
                    .batchStockItem(
                            BatchStockItem.builder()
                                    .quantity(dto.getQuantity())
                                    .volume(dto.getVolume())
                                    .product(productService.obtem(dto.getBatchStockItem()))
                                    .maximumTemperature(dto.getMinimumTemperature())
                                    .batchStock(batchStock)
                                    .build()
                    )
                    .build();
            resultList.add(batchStock);
        }
        return resultList;
    }

    }


