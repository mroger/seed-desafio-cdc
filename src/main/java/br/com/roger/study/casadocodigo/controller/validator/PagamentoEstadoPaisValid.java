package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PagamentoEstadoPaisValidator.class)
public @interface PagamentoEstadoPaisValid {

    String message() default "cdc.campo.obrigatorio";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
