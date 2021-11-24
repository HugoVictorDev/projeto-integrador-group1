package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import javax.transaction.Transactional;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class InBoundOrderService {

    @Autowired
    private InBoundOrderRepository inBoundOrderRepository;
    @Autowired
    private final WarehouseServices warehouseServices;
    @Autowired
    private  SectionServices sectionServices;
    @Autowired
    RepresentanteServices representanteServices;
    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;
    @Autowired
    BatchStockService batchStockService;
    @Autowired
    BatchStockItemService batchStockItemService;

    
    @Autowired
    public InBoundOrderService(InBoundOrderRepository inBoundOrderRepository, WarehouseServices warehouseServices,
                               RepresentanteServices representanteServices, ProductService productService,
                               SellerService sellerService, SectionServices sectionServices, BatchStockItemService batchStockItemService, BatchStockService batchStockService){
        this.inBoundOrderRepository = inBoundOrderRepository;
        this.warehouseServices = warehouseServices;
        this.representanteServices = representanteServices;
        this.productService = productService;
        this.sellerService= sellerService;
        this.batchStockService = batchStockService;
        this.batchStockItemService = batchStockItemService;
        this.sectionServices = sectionServices;
    }


    public ResponseEntity<Object> registra(UriComponentsBuilder uriBuilder, InBoundOrderRequestDTO inBoundOrderRequestDTO, InBoundOrder inBoundOrder) {

        List<BatchStock> batchStocks = inBoundOrder.getBatchStock();
        batchStocks.forEach(b -> {
            validaDate(inBoundOrderRequestDTO.getOrderDate(),b.getManufacturingTime(),b.getDueDate(), b.getBatchStockNumber());
            b.getBatchStockItem().setBatchStock(b);

        });
        InBoundOrder byOrderNumber = inBoundOrderRepository.findByOrderNumber(inBoundOrder.getOrderNumber());
        if (byOrderNumber != null){
            throw new RuntimeException("Já contém uma inboundorder com essa ordernumber, utilize o método de atualizar");
        }
        try {
            this.inBoundOrderRepository.save(inBoundOrder);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RuntimeException("Erro ao Registrar InboundOrder"));
        }
        URI uri = uriBuilder.path("/inBoundOrder/{id}").buildAndExpand(inBoundOrder.getId()).toUri();
        return ResponseEntity
                .created(uri).body(inBoundOrderRequestDTO);
    }



    //acoplamento de todas validocoes da inboundOrder, usada no controller.
    public InBoundOrderRequestDTO validInboundOrder(InBoundOrderRequestDTO inb){
        this.warehouseServices.obterWarhouseByCode(inb.getSectionForInboundDTO().getWarehouseCode());
        this.sectionServices.obterSectionByCode(inb.getSectionForInboundDTO().getCode());
        this.representanteServices.obterRepresentanteById(inb.getRepresentanteId());
        this.representanteIsPresenteWarehouse(inb.getRepresentanteId());
        this.sectionMatchStockType(inb.getSectionForInboundDTO().getCode());
        this.sectionHasCapacity(inb);
        this.sellerService.obtem(inb.getSellerId());
        if (!validProductInboud(inb)){
            throw new RuntimeException("Produto nao encontrado");
        }
        return inb;
    }

    //Faz a validacao se o representante pertence a warehouse
    public boolean  representanteIsPresenteWarehouse(Long id){
        for (Section sec : sectionServices.listaSection()){
            if (sec.getWarehouse().getRepresentante().getId() == id){
                Representante representante = sec.getWarehouse().getRepresentante();
                return representante != null; // return true TODO
            }else throw new RuntimeException("representante não pertence ao armazem");
        }
        return false;
    }

    //Faz a validacao se a section cadastrada corresponde ao StockType(Enum).
    private boolean sectionMatchStockType(Long code) {
        StockType stockType = sectionServices.obtemTypeStockSection(code);
        List<Section> listsec = sectionServices.listaSection();
        for (Section section : listsec ) {
            if (section.getStockType().equals(stockType)) {
                return true;
            }
        }
        throw new RuntimeException("section  não corresponde ao tipo de produto");
    }

    //Faz a validacao se o produto existe, entao ele entra na lista de batchstock
    // e pega o id de cada batchstockitem da lista de batchstock e verifica se o produto esta cadastrado.
    private boolean validProductInboud(InBoundOrderRequestDTO inb){

        List<Long> listBtcItemID = inb.getBatchStockDTOList().stream()
                .map(batchStockRequestDTO -> batchStockRequestDTO.getBatchStockItem()).collect(Collectors.toList());

        List<Long> listProductID = productService.listProductId();

        if (listProductID.containsAll(listBtcItemID)) {
            return true;
        }

        return false;
    }



    private boolean sectionHasCapacity(InBoundOrderRequestDTO inb){
        int capacitySection = sectionServices.obtemQuantidadeDoSection(inb.getSectionForInboundDTO().getCode());
        int sumOfProductQuantity = inb.getBatchStockDTOList()
                .stream().mapToInt(value -> value.getQuantity()).sum();

        if (capacitySection >= sumOfProductQuantity){
            return true;
        }
        throw new RuntimeException("Este setor contém uma capacidade de " + capacitySection +
                " lotes e voce está inserindo: " + sumOfProductQuantity + "  lotes");
    }


    @Transactional
    public ResponseEntity<Object> updateInbound(InBoundOrderRequestDTO dto, UriComponentsBuilder uriBuilder) {
        InBoundOrder existingInboundOrder = inBoundOrderRepository.findByOrderNumber(dto.getOrderNumber());
        if (existingInboundOrder != null) {
            InBoundOrder io = atualiza(existingInboundOrder, dto);
            try {
                this.inBoundOrderRepository.save(io);
            } catch (RuntimeException e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new RuntimeException("Erro ao Ayualizar InboundOrder"));
            }
            URI uri = uriBuilder.path("/inBoundOrder/{id}").buildAndExpand(io.getId()).toUri();
            return ResponseEntity
                    .created(uri).body(dto);
        }
        throw new RuntimeException("Inbound nao encontrada");
    }


    private InBoundOrder atualiza(InBoundOrder inBoundOrder, InBoundOrderRequestDTO dto){
        Section section = sectionServices.obterSectionByCode(dto.getSectionForInboundDTO().getCode());
        Optional<InBoundOrder> op = this.inBoundOrderRepository.findById(inBoundOrder.getId());
        InBoundOrder io = op.get();
        io.setOrderDate(dto.getOrderDate());
        io.setSection(section);
        io.setRepresentante(representanteServices.obterRepresentanteById(dto.getRepresentanteId()));
        List<BatchStock> listaExistentes = new ArrayList<>();
        List<BatchStock> listaNovos = new ArrayList<>();
        identificaBatchStocks(dto, listaExistentes, listaNovos);
        for (BatchStock bs : listaNovos) {
            bs.getBatchStockItem().setBatchStock(bs);
        }
        io.getBatchStock().addAll(io.getBatchStock().size()-1, listaNovos);
        return io;
    }

    private void identificaBatchStocks(InBoundOrderRequestDTO inboundOrderDTO, List<BatchStock> listaExistentes, List<BatchStock> listaNovos){
        try{
            inboundOrderDTO.getBatchStockDTOList().forEach(bsDTO -> {
                BatchStock bs = this.batchStockService.findBatchNumber(bsDTO.getBatchStockNumber());
                if(bs !=null){
                    bs = atualizaValoresBatchStockExistente(inboundOrderDTO, bsDTO, bs);
                    listaExistentes.add(bs);
                }else{
                    listaNovos.add(toEntity(inboundOrderDTO, bsDTO));
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public BatchStock atualizaValoresBatchStockExistente(InBoundOrderRequestDTO inboundOrderDTO, BatchStockRequestDTO dto, BatchStock bs) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        bs.setDueDate(dto.getDueDate());
        bs.setManufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(),fmt));
        bs.setCurrentQuality(dto.getCurrentQuality());
        bs.setInitialQuality(dto.getInitialQuality());
        bs.setMinimumTemperature(dto.getMinimumTemperature());
        bs.setMaximumTemperature(dto.getMaximumTemperature());
        bs.setCurrentTemperature(dto.getCurrentTemperature());
        bs.setSeller(sellerService.obtem(inboundOrderDTO.getSellerId()));
        bs.setQuantity(dto.getQuantity());
        bs.setVolume(dto.getVolume());
        bs.getBatchStockItem().setQuantity(dto.getQuantity());
        bs.getBatchStockItem().setVolume(dto.getVolume());
        bs.getBatchStockItem().setMaximumTemperature(dto.getMaximumTemperature());
        bs.getBatchStockItem().setMinimumTemperature(dto.getMinimumTemperature());
        bs.getBatchStockItem().setProduct(this.productService.obtem(dto.getBatchStockItem()));
        return bs;
    }

    private BatchStock toEntity(InBoundOrderRequestDTO inboundOrderDTO, BatchStockRequestDTO btc){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BatchStock batchStock = BatchStock.builder()
                .batchStockNumber(btc.getBatchStockNumber())
                .dueDate(btc.getDueDate())
                .manufacturingTime(LocalDateTime.parse(btc.getManufacturingTime(),fmt))
                .currentQuality(btc.getCurrentQuality())
                .initialQuality(btc.getInitialQuality())
                .minimumTemperature(btc.getMinimumTemperature())
                .maximumTemperature(btc.getMaximumTemperature())
                .currentTemperature(btc.getMaximumTemperature())
                .seller(sellerService.obtem(inboundOrderDTO.getSellerId()))
                .quantity(btc.getQuantity())
                .volume(btc.getVolume())
                .batchStockItem(
                        BatchStockItem.builder()
                                .maximumTemperature(btc.getMaximumTemperature())
                                .quantity(btc.getQuantity())
                                .volume(btc.getVolume())
                                .product(productService.obtem(btc.getBatchStockItem()))
                                .minimumTemperature(btc.getMinimumTemperature())
                                .build()
                )
                .build();
        return batchStock;
    }

    private void validaDate(LocalDate orderDate, LocalDateTime manufacturingTime, LocalDate dueDate, Long batchStockNumber) {

        LocalDate localDate = manufacturingTime.toLocalDate();
        String dataAtual = orderDate.toString();
        String dataVencimento = dueDate.toString();
        String dataFabricacao = localDate.toString();

        if(dataVencimento.compareTo(dataAtual) <= 0){
            throw new RuntimeException("batchStockNumber: "+batchStockNumber+", Data de validade expirada: DueDate deve ser maior que a data de hoje");
        }

        if(dataFabricacao.compareTo(dataAtual) > 0){
            throw new RuntimeException("batchStockNumber: "+batchStockNumber+", Data de fabricação invalida: manufatureDate deve ser menor que a data de hoje");
        }
    }

}

