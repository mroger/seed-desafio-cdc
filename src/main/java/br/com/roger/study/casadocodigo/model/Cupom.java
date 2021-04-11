package br.com.roger.study.casadocodigo.model;

import br.com.roger.study.casadocodigo.controller.validator.Unique;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Carga: 0
 */
@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_generator")
    @SequenceGenerator(name="estado_generator", sequenceName = "estado_sequence")
    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private BigDecimal desconto;

    @NotNull
    private LocalDate validade;

    @Deprecated
    public Cupom() { }

    public Cupom(@NotNull @Unique(clazz = Cupom.class, field = "codigo") String codigo,
            @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal desconto, @NotNull @Future LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
    }

    public Long getId() {
        return id;
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