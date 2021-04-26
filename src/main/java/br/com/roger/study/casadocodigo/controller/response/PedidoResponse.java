package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Pedido;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoResponse {

    private Long id;

    private List<ItemPedidoResponse> itens;

    public static PedidoResponse fromModel(Pedido pedido) {
        PedidoResponse pedidoResponse = new PedidoResponse();
        BeanUtils.copyProperties(pedido, pedidoResponse, "itens", "compra");
        copyItens(pedido, pedidoResponse);
        return pedidoResponse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<ItemPedidoResponse> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponse> itens) {
        this.itens = itens;
    }

    private static void copyItens(Pedido pedido, PedidoResponse pedidoResponse) {
        final List<ItemPedidoResponse> itemPedidoResponses = pedido.getItens().stream()
            .map(ItemPedidoResponse::fromModel)
            .collect(Collectors.toList());
        pedidoResponse.setItens(itemPedidoResponses);
    }
}
