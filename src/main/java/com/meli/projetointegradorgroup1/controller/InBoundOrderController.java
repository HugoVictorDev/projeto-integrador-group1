package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.InboundOrderDtoJustBatchStocks;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

/**
 * @author Hugo Victor
 * @author Marco Siqueira
 */

@RestController
@RequestMapping("/inboundorder")
public class InBoundOrderController {

    @Autowired
    InBoundOrderService inBoundOrderService;
    @Autowired
    SectionServices sectionServices;
    @Autowired
    BatchStockService batchStockService;
    @Autowired
    ProductService productService;
    @Autowired
    RepresentanteServices representanteServices;
    @Autowired
    SellerService sellerService;
    @Autowired
    InBoundOrderRepository inBoundOrderRepository;

    public InBoundOrderController(InBoundOrderService inBoundOrderService) {
        this.inBoundOrderService = inBoundOrderService;
    }

    /**
     * @author Hugo Victor
     */
    @PostMapping("/create")
    public ResponseEntity<Object>create(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO, UriComponentsBuilder uriBuilder) {
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        return inBoundOrderService.registra(uriBuilder, inBoundOrderRequestDTO, (inBoundOrderRequestDTO.convertedto(representanteServices,
                sectionServices, productService, sellerService)));
    }

    /**
     * @author Hugo Victor
     */
    @PutMapping("/update")
    public ResponseEntity<Object>update(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO, UriComponentsBuilder uriBuilder) {
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        return inBoundOrderService.updateInbound(inBoundOrderRequestDTO, uriBuilder);
    }
    /**
     * @author Hugo Victor
     * Requisito 6 Individual
     *  busca a inboundOrder pelo ID do representante e retorna apenas a lista de BatchStocks.
     */

    @GetMapping("/representante/{id}")
    public InboundOrderDtoJustBatchStocks listBatchsStocksFromInbound(@PathVariable ("id") Long id){
        InBoundOrder inBoundOrder = inBoundOrderService.listInboundRepresentante(id);
        InboundOrderDtoJustBatchStocks inboundOrderDtoJustBatchStocks = inBoundOrderService.converteDto(inBoundOrder);
        return inboundOrderDtoJustBatchStocks;

    }
}
