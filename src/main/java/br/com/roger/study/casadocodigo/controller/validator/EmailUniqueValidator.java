package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.AutorRequest;
import br.com.roger.study.casadocodigo.model.Autor;
import br.com.roger.study.casadocodigo.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

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
        return AutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AutorRequest request = (AutorRequest) target;

        final Optional<Autor> autorByEmail = autorRepository.findByEmail(request.getEmail());

        autorByEmail
            .ifPresent(autor -> {
                errors.reject("cdc.request.email.duplicado", "cdc.request.email.duplicado");
            });
    }
}
