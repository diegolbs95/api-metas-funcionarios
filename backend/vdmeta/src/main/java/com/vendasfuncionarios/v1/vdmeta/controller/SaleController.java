package com.vendasfuncionarios.v1.vdmeta.controller;

import com.vendasfuncionarios.v1.vdmeta.entities.Sale;
import com.vendasfuncionarios.v1.vdmeta.service.SaleService;
import com.vendasfuncionarios.v1.vdmeta.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@Slf4j
public class SaleController {

    @Autowired
    private SaleService service;
    @Autowired
    private SmsService smsService;

    @GetMapping
    public Page<Sale> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                @RequestParam(value = "maxDate", defaultValue = "")String maxDate,
                                Pageable pageable){
        log.info("Acionando endpoint buscar lista de sales.");
        return service.listarSales(minDate, maxDate, pageable);
    }

    @GetMapping("/nome")
    public List<Sale> findSalesByName(@RequestBody String nome){
        log.info("Acionando endpoint buscar lista de sales por nome.");
        return service.listarSalePorNome(nome);
    }

    @GetMapping("/{id}/notificar")
    public void notificar(@PathVariable Long id) {
        smsService.sendSms(id);
    }
}
