package br.com.roger.study.casadocodigo.model;

import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cupom_aplicado")
public class CupomAplicado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupom_aplicado_generator")
    @SequenceGenerator(name="cupom_aplicado_generator", sequenceName = "cupom_aplicado_sequence")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cupom")
    private Cupom cupom;

    @NotNull
    private BigDecimal desconto;

    @NotNull
    private LocalDate validade;

    @Deprecated
    public CupomAplicado() {
    }

    public CupomAplicado(Cupom cupom) {
        Assert.notNull(cupom, "O cupom aplicado deve existir");
        Assert.notNull(cupom.getDesconto(), "O desconto deve estar definido");
        Assert.notNull(cupom.getValidade(), "A validade deve estar definida");

        this.cupom = cupom;
        this.desconto = cupom.getDesconto();
        this.validade = cupom.getValidade();
        cupom.getCupomAplicados().add(this);
    }

    public BigDecimal getDesconto() {
        return desconto;
    }
}
