package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.ItemPedido;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ItemPedidoResponse {

    private Long id;

    private LivroResponse livro;

    private Integer quantidade;

    private BigDecimal precoNaCompra;

    public static ItemPedidoResponse fromModel(ItemPedido itemPedido) {
        ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse();
        BeanUtils.copyProperties(itemPedido, itemPedidoResponse, "livro");
        itemPedidoResponse.setLivro(LivroResponse.fromModel(itemPedido.getLivro()));
        return itemPedidoResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivroResponse getLivro() {
        return livro;
    }

    public void setLivro(LivroResponse livro) {
        this.livro = livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoNaCompra() {
        return precoNaCompra;
    }

    public void setPrecoNaCompra(BigDecimal precoNaCompra) {
        this.precoNaCompra = precoNaCompra;
    }
}
