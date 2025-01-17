package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Cupom;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Carga: 1
 */

public class CupomDescontoCreateRequest {

    @NotNull(message = "cdc.cupom.codigo.obrigatorio")
    @Unique(clazz = Cupom.class, field = "codigo")
    private String codigo;

    @NotNull(message = "cdc.cupom.desconto.obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "cdc.cupom.desconto.minimo")
    private BigDecimal desconto;

    @NotNull(message = "cdc.cupom.validade.obrigatorio")
    @FutureOrPresent(message = "cdc.cupom.validade.futuro")
    private LocalDate validade;

    //1
    public Cupom toModel() {
        //Checar pre-condicao

        return new Cupom(codigo, desconto, validade);
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
