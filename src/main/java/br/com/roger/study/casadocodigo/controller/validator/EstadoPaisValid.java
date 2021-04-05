package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EstadoPaisValidator.class)
public @interface EstadoPaisValid {

    String message() default "cdc.campo.obrigatorio";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campoEstado();

    String campoPais();
}
