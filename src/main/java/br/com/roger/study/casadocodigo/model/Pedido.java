package br.com.roger.study.casadocodigo.model;

import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_generator")
    @SequenceGenerator(name="pedido_generator", sequenceName = "pedido_sequence")
    private Long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pedido")
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @Deprecated
    public Pedido() {
    }

    public Pedido(List<ItemPedido> itens, Compra compra) {
        Assert.notEmpty(itens, "Um pedido precisa ter itens");

        this.itens = itens;
        this.compra = compra;
        itens.forEach(itemPedido -> itemPedido.setPedido(this));
    }

    public boolean totalEquals(BigDecimal total) {
        return valorTotalItens().equals(total);
    }

    private BigDecimal valorTotalItens() {
        return itens.stream()
            .map(ItemPedido::valorTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
