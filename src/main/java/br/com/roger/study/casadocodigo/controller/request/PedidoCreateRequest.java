package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.PagamentoCarrinhoTotalValid;
import br.com.roger.study.casadocodigo.model.Compra;
import br.com.roger.study.casadocodigo.model.ItemPedido;
import br.com.roger.study.casadocodigo.model.Pedido;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@PagamentoCarrinhoTotalValid
public class PedidoCreateRequest {

    @NotNull(message = "cdc.pagamento.carrinho.total.obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "cdc.pagamento.carrinho.total.greaterthanzero")
    private BigDecimal total;

    @NotEmpty(message = "cdc.pagamento.carrinho.itens.obrigatorio")
    @Valid
    private List<ItemPedidoCreateRequest> itens;

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemPedidoCreateRequest> getItens() {
        return itens;
    }

    public Function<Compra, Pedido> toModel(EntityManager em) {

        final List<ItemPedido> itensPedido = itens.stream()
            .map(item -> item.toModel(em))
            .collect(Collectors.toList());

        return (compra) -> {
            final Pedido pedido = new Pedido(itensPedido, compra);
            Assert.isTrue(pedido.totalEquals(total), "O total não coincide com o que está no carrinho");
            return pedido;
        };
    }
}
