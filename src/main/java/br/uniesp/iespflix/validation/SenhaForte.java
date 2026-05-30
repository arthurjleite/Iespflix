package br.uniesp.iespflix.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SenhaForteValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaForte {
    String message() default "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um número";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
