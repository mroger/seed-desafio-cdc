package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= CupomValidoValidator.class)
public @interface CupomValido {

    String message() default "cdc.cupom.invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
