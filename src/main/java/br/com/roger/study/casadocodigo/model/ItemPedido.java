package br.com.roger.study.casadocodigo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

// Value Object do DDD

/**
 * Carga: 2
 */
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_pedido_generator")
    @SequenceGenerator(name="item_pedido_generator", sequenceName = "item_pedido_sequence")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @NotNull
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @NotNull
    @Positive
    @Column(name = "preco_na_compra")
    private BigDecimal precoNaCompra;

    @Deprecated
    public ItemPedido() {
    }

    public ItemPedido(Livro livro, Integer quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        //Nos protegendo caso o preco no livro mude
        this.precoNaCompra = livro.getPreco();
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal valorTotal() {
        return livro.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}
