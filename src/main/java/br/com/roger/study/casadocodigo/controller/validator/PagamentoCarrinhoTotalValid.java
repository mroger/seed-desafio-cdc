package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PagamentoCarrinhoTotalValidator.class)
public @interface PagamentoCarrinhoTotalValid {

    String message() default "cdc.pagamento.carrinho.total.diferente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
