package br.com.roger.study.casadocodigo.controller.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CPFCNPJ, String> {

    public static final String CPF_SEPARATORS_OREDERED = "..-";
    public static final String CNPJ_SEPARATORS_OREDERED = "../-";

    @Override
    public void initialize(CPFCNPJ constraintAnnotation) {
        //Nada a fazer aqui
    }

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {
        //Retornando que é valido quando documento é nulo para deixar a validação da existência
        //para a anotação @NotBlank
        if (document == null) {
            return true;
        }
        if (!isValidCpfCnpjDocumentLength(getOnlyDigits(document))) {
            return false;
        }
        if (!isCpfCnpjDocumentFormatted(document)) {
            changeMessageTemplate(context, "cdc.cpfcpnpj.naoformatado");
            return false;
        }

        final Validator<String> validator = DocumentValidatorFactory.create(getOnlyDigits(document));
        try {
            validator.assertValid(document);
            return true;
        } catch (InvalidStateException e) {
            defineErrorMessages(context, validator);
            return false;
        }
    }

    private boolean isValidCpfCnpjDocumentLength(String document) {
        return document.length() == 11 || document.length() == 14;
    }

    private boolean isCpfCnpjDocumentFormatted(String document) {
        String separadores = document.replaceAll("[0-9]+", "");
            return separadores.equals(CPF_SEPARATORS_OREDERED) || separadores.equals(CNPJ_SEPARATORS_OREDERED);
    }

    private String getOnlyDigits(String document) {
        return document
            .replaceAll("\\.", "")
            .replaceAll("/", "")
            .replaceAll("-", "");
    }

    private void defineErrorMessages(ConstraintValidatorContext context, Validator<String> validator) {
        String messageTemplate = validator instanceof CPFValidator
            ? "cdc.cpf.invalido" : "cdc.cnpj.invalido";
        changeMessageTemplate(context, messageTemplate);
    }

    private void changeMessageTemplate(ConstraintValidatorContext context, String messageTemplate) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation();
    }
}
