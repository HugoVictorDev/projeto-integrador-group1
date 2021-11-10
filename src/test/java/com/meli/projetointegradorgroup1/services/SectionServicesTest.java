package com.meli.projetointegradorgroup1.services;

public class SectionServicesTest {
    /*
    Section section = new Section(1l,"1","2","3",null);
    SectionDTO sectionDTO = new SectionDTO(null,"5","6","7","4");
    SectionDTO sectionConvert = new SectionDTO(1l,"1","2","3","4");

    SectionServices sectionServices;
    SectionRepository sectionRepository;
    List<Section> listSection = new ArrayList();
    List<com.meli.projetointegradorgroup1.dto.response.SectionDTO> listSectionDto = new ArrayList();

    WarehouseServices warehouseServices;
    Warehouse warehouse = new Warehouse(1l, "Miguel", "Rua: Hum", "3",null);

    String message = "";



    @Test
    public void listaSectionOk(){
        listSection.add(section);

        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(null, sectionRepository);
        sectionServices.listaSection();

        assert (listSection.size() == 1);
    }

    @Test
    public void listaSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(null, sectionRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.listaSection();});
        message = "Não existem Sessões cadastradas";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterSectionOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findBysectionId(Mockito.anyLong())).thenReturn(section);

        sectionServices = new SectionServices(null, sectionRepository);
        sectionServices.obterSection(1l);

        assert (section.getId().equals(1l));
    }

    @Test
    public void obterSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findBysectionId(Mockito.anyLong())).thenReturn(null);

        sectionServices = new SectionServices(null, sectionRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.obterSection(1l);});
        message = "Sessão não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        sectionServices = Mockito.mock(SectionServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);
        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);

        sectionServices = new SectionServices(warehouseServices,null);
        sectionServices.validaUpdate(java.util.Optional.ofNullable(section)S);

        assert (section.getStockType().equals(sectionDTO.getStockType()));
    }

    @Test
    public void validaUpdateNok(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);

        sectionServices = new SectionServices(null,null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.validaUpdate(java.util.Optional.ofNullable(null),sectionDTO);});
        message = "Sessão não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void converteOk(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convert(Mockito.any())).thenReturn(section);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convert(sectionConvert);

        assert (section.getStockType().equals(sectionConvert.getStockType()));
    }

    @Test
    public void converteToDtoOk(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionConvert);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertToDto(section);

        assert (section.getStockType().equals(sectionConvert.getStockType()));
    }

    @Test
    public void converteListOk(){
        listSectionDto.add(sectionConvert);
        listSection.add(section);
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertList(Mockito.any())).thenReturn(listSectionDto);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertList(listSection);

        assert (section.getStockType().equals(sectionConvert.getStockType()));
    }
    @Test
    public void deletaSectioOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.doNothing().when(sectionRepository).deleteById(Mockito.anyLong());
        SectionServices sectionServices = new SectionServices(null,sectionRepository);
        sectionServices.deletaSection(1l);

        assert (section.getId() == 1);
    }*/
}
