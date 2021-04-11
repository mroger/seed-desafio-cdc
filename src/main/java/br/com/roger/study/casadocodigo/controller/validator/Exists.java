package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ExistsFieldValidator.class)
public @interface Exists {

    String message() default "cdc.relacionamento.naoencontrado";

    Class<?> clazz();

    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
