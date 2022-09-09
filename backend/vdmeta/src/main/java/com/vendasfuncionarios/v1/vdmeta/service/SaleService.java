package com.vendasfuncionarios.v1.vdmeta.service;

import com.vendasfuncionarios.v1.vdmeta.entities.Sale;
import com.vendasfuncionarios.v1.vdmeta.repository.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Page<Sale> listarSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                  @RequestParam(value = "maxDate", defaultValue = "")String maxDate,
                                  Pageable pageable){

        try {

            var diaAtual = LocalDate.now();

            var min = minDate.equals("") ? diaAtual.minusMonths(6) : LocalDate.parse(minDate);
            var max = maxDate.equals("") ? diaAtual : LocalDate.parse(maxDate);
            var listaSales = repository.findSales(min, max, pageable);

            return listaSales;

        }catch (Exception e){
            log.error(String.format("Lista de Sales não localizada error: %s", e.getMessage()));
        }
        return null;
    }

    public List<Sale> listarSalePorNome (String nome){
        try {
            var buscarSalesPorNome = repository.findBySellerName(nome);
            return buscarSalesPorNome;
        }catch (Exception e){
            log.error(String.format("Nome nao localizado na base error: %s", e.getMessage()));
        }
        return null;
    }
}
