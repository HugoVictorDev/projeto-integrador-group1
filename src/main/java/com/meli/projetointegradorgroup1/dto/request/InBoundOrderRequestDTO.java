package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InBoundOrderRequestDTO {

        private Long orderNumber;
        private LocalDate orderDate;
        private Section section;
        private List<BatchStock> batchStock;

        public InBoundOrder build(){
            InBoundOrder inBoundOrder = new InBoundOrder()
                    .setOrderNumber(this.orderNumber)
                    .setOrderDate(this.orderDate)
                    .setSection(this.section)
                    .setBatchStock(this.batchStock);
            return inBoundOrder;
        }

    }


