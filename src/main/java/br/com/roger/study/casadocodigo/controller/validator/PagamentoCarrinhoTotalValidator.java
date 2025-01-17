package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.PedidoCreateRequest;
import br.com.roger.study.casadocodigo.model.Livro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Carga: 6
 */
public class PagamentoCarrinhoTotalValidator implements ConstraintValidator<PagamentoCarrinhoTotalValid, PedidoCreateRequest> {

    @PersistenceContext
    private EntityManager em;

    //1
    @Override
    public void initialize(PagamentoCarrinhoTotalValid constraintAnnotation) {
        //nada a fazer aqui
    }

    //1
    @Override
    public boolean isValid(PedidoCreateRequest request, ConstraintValidatorContext context) {
        //1
        if (request == null) {
            return true;
        }
        //1
        if (request.getTotal() == null || request.getItens() == null || request.getItens().isEmpty()) {
            return true;
        }
        //1
        final BigDecimal totalSum = request.getItens().stream()
            .map(item -> {
                //1
                final Livro livro = em.find(Livro.class, item.getIdLivro());
                return livro.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSum.equals(request.getTotal());
    }
}
