package br.com.roger.study.casadocodigo.controller.validator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.Validator;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class DocumentValidatorFactory {

    public static final int CPF_LENGTH = 11;
    public static final int CNPJ_LENGHT = 14;

    /**
     * Factory para validador de documentos
     * @param document documento contendo apenas números
     * @return Validador de acordo com o tipo de documento
     */
    public static Validator<String> create(@NotBlank String document) {
        Assert.hasLength(document, "O documento não pode ser nulo ou vazio");
        Assert.isTrue(document.matches("[0-9]+"), "O documento deve conter apenas números");

        switch(document.length()) {
            case CPF_LENGTH:
                return new CPFValidator(true);
            case CNPJ_LENGHT:
                return new CNPJValidator(true);
            default:
                throw new IllegalArgumentException("O documento deve conter comprimento de 11 ou 14 caracteres");
        }
    }
}
