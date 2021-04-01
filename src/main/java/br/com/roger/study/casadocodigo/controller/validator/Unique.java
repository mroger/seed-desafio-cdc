package br.com.roger.study.casadocodigo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= UniqueStringFieldValidator.class)
public @interface Unique {

    String message() default "cdc.atributo.duplicado";

    Class<?> clazz();

    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
