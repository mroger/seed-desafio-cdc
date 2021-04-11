package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.model.ItemPedido;
import br.com.roger.study.casadocodigo.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Carga: 2
 */

public class ItemPedidoCreateRequest {

    @NotNull(message = "cdc.pagamento.carrinho.itens.livro.obrigatorio")
    @ExistsId(clazz = Livro.class, message = "cdc.pagamento.carrinho.itens.livro.naocadastrado")
    private Long idLivro;

    @NotNull(message = "cdc.pagamento.carrinho.itens.livro.quantidade.obrigatorio")
    @Min(value = 1, message = "cdc.pagamento.carrinho.itens.livro.quantidade.minimo")
    private Integer quantidade;

    public Long getIdLivro() {
        return idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    //1
    public ItemPedido toModel(EntityManager em) {
        //1
        @NotNull final Livro livro = em.find(Livro.class, idLivro);
        Assert.state(livro != null, "O livro colocado no carrinho não está cadastrado: " + idLivro);

        return new ItemPedido(livro, quantidade);
    }
}
