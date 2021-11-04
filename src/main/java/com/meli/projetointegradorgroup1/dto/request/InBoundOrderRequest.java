package com.meli.projetointegradorgroup1.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.services.StockService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InBoundOrderRequest {

        private Long orderNumber;
        private LocalDate orderDate;
      @JsonProperty(value =  "seller_id")
        private Long sellerId;
        private SectionDTOHugo sectionDTOHugo;
        @JsonProperty(value= "batchStockList")
        private List<BatchStockDTOhugo> batchStockDTOList;




        public InBoundOrder convertedto(StockService stockService){

            List<BatchStockItem> listaDeStockItemsDoSellerDaInboundOrder = stockService.list(Seller.builder().sellerId(sellerId).build());
            //TODO: o seller_id que vem no payload pode nao ter nenhum item de estoque. Neste caso
            // é necessario criar um item de estoque para o produto correspondente considerando o seller


            List<BatchStock> listaDeBatchStocksConvertidaParaEntity = null;
            try{
                listaDeBatchStocksConvertidaParaEntity = BatchStockDTOhugo.converte(batchStockDTOList, listaDeStockItemsDoSellerDaInboundOrder);
            }catch(Exception e){
               e.printStackTrace();
            }


            InBoundOrder inBoundOrder = new InBoundOrder()
                    .setOrderNumber(this.orderNumber)
                    .setOrderDate(this.orderDate)
                    .setSection(this.sectionDTOHugo.convertedto())
                    .setBatchStock(listaDeBatchStocksConvertidaParaEntity);
            return inBoundOrder;
        }





    }


