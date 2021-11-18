package com.meli.projetointegradorgroup1.services;


import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    public InBoundOrderService(InBoundOrderRepository inBoundOrderRepository, WarehouseServices warehouseServices,
                               RepresentanteServices representanteServices, ProductService productService, SellerService sellerService){
        this.inBoundOrderRepository = inBoundOrderRepository;
        this.warehouseServices = warehouseServices;
        this.representanteServices = representanteServices;
        this.productService = productService;
        this.sellerService= sellerService;
    }


    public void registra(InBoundOrder inBoundOrder){
        List<BatchStock> batchStocks = inBoundOrder.getBatchStock();

        batchStocks.forEach(b -> {
            b.setInboundOrder(inBoundOrder);
            b.getBatchStockItem().setBatchStock(b);
        });
        try{

                this.inBoundOrderRepository.save(inBoundOrder);

        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException( "nao salvou a inboundOrder");
        }
    }

    public InBoundOrderRequestDTO validInboundOrder(InBoundOrderRequestDTO inb){

        this.warehouseServices.obterWarhouseByCode(inb.getSectionForInboundDTO().getWarehouseCode());
        this.sectionServices.obterSectionByCode(inb.getSectionForInboundDTO().getCode());
        this.representanteServices.obterRepresentanteById(inb.getRepresentanteId());
        this.representanteIsPresenteWarehouse(inb.getRepresentanteId());
        this.sectionMatchStockType(inb.getSectionForInboundDTO().getCode());
        this.sectionHasCapacity(inb);
        this.sellerService.obter(inb.getSellerId());
        this.validOrderNumber(inb.getOrderNumber());
        if (!validProductInboud(inb)){
            throw new RuntimeException("Produto nao encontrado");
         }
        return inb;
    }

    public boolean  representanteIsPresenteWarehouse(Long id){
        for (Section sec : sectionServices.listaSection()){
            if (sec.getWarehouse().getRepresentante().getId() == id){
                Representante representante = sec.getWarehouse().getRepresentante();
                return representante != null; // return true TODO
            }else throw new RuntimeException("representante não pertence ao armazem");
        }
        return false;
    }

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


    private boolean validProductInboud(InBoundOrderRequestDTO inb){

        List<Long> listBtcItemID = inb.getBatchStockDTOList().stream()
                .map(batchStockRequestDTO -> batchStockRequestDTO.getBatchStockItem()).collect(Collectors.toList());

        List<Long> listProductID = productService.listProductId();

        if (listProductID.containsAll(listBtcItemID)) {
            return true;
        }

        return false;
    }


    private boolean validOrderNumber(Long id){
        InBoundOrder byOrderNumber = inBoundOrderRepository.findByOrderNumber(id);
        if (byOrderNumber != null){
            throw new RuntimeException("Já contém uma inboundorder com essa ordernumber, utilize o método de atualizar");
        }
        return true;
    }

    private InBoundOrder obterInbound(Long batchnumber){
       return inBoundOrderRepository.findByOrderNumber(batchnumber);
    }

    private boolean sectionHasCapacity(InBoundOrderRequestDTO inb){
        //capacidade da section by code
        int capacitySection = sectionServices.obtemQuantidadeDoSection(inb.getSectionForInboundDTO().getCode());

        //soma de valores da lista de batchstock
        int sumOfProductQuantity = inb.getBatchStockDTOList()
                .stream().mapToInt(value -> value.getQuantity()).sum();

        if (capacitySection >= sumOfProductQuantity){
            return true;
        }
        throw new RuntimeException("Este setor contém uma capacidade de " + capacitySection +
                " lotes e voce está inserindo: " + sumOfProductQuantity + "  lotes");
    }


    //TODO parei aqui PUT inmbound

    public InBoundOrder validaUpdate( InBoundOrder inBoundOrder) {
        if (inBoundOrder != null){
            InBoundOrder inBoundOrderUpdated = obterInbound(inBoundOrder.getOrderNumber());

        }

return null;
    }


}

