package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.NovoAutorRequest;
import br.com.roger.study.casadocodigo.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Carga intrinseca: 3
 */
@Component
public class EmailUniqueValidator implements Validator {

    private AutorRepository autorRepository;

    public EmailUniqueValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovoAutorRequest request = (NovoAutorRequest) target;

        autorRepository
            .findByEmail(request.getEmail())
            .ifPresent(autor -> {
                errors.reject("cdc.request.email.duplicado", "cdc.request.email.duplicado");
            });
    }
}
