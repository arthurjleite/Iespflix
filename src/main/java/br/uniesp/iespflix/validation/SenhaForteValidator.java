package br.uniesp.iespflix.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaForteValidator implements ConstraintValidator<SenhaForte, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        boolean temMaiuscula = value.matches(".*[A-Z].*");
        boolean temMinuscula = value.matches(".*[a-z].*");
        boolean temNumero = value.matches(".*[0-9].*");

        return temMaiuscula && temMinuscula && temNumero;
    }
}
