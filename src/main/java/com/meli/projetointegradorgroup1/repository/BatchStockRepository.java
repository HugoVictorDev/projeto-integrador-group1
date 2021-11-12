package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public interface BatchStockRepository  extends JpaRepository<BatchStock, Long> {
    Optional<BatchStock> deleteBybatchStockNumber(Long OrderNumber);
    Optional<BatchStock> findById(Long Id);
    List<BatchStock> findAll();


//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE batch_stock SET batchnumber=:batch_number, " +
//                                    "currentquality=:current_quality, " +
//                                    "currenttemperature=:current_temperature, " +
//                                    "duedate=:due_date, " +
//                                    "initialquality=:initial_quality, " +
//                                    "manufacturingtime=:manufacturing_time, " +
//                                    "minimumtemperature=:minimum_temperature, " +
//                                    "inboundorder_id=:inbound_order_id, " +
//                                    "sellerid=:seller_id, " +
//                                    "batchstock_item=:batch_stock_item" +
//                                    "volume=:volume, " +
//                                    "maximumtemperature=:maximum_temperature, " +
//                                    "quantity=:quantity WHERE id=:id")
//    int setSellerInfoById(@Param("batch_number") Long batchStockNumber,
//                          @Param("current_quality") Double currentTemperature,
//                          @Param("current_temperature") Double minimumTemperature,
//                          @Param("due_date") LocalDate dueDate,
//                          @Param("initial_quality") String initialQuality,
//                          @Param("manufacturing_time") String currentQuality,
//                          @Param("minimum_temperature") LocalDateTime manufacturingTime,
//                          @Param("inbound_order_id") InBoundOrder inboundOrder,
//                          @Param("seller_id") Seller seller,
//                          @Param("batch_stock_item") BatchStockItem batchStockItem,
//                          @Param("volume") double volume,
//                          @Param("maximum_temperature") Double maximumTemperature,
//                          @Param("quantity") int quatity,
//                          @Param("id") Long id);
}
