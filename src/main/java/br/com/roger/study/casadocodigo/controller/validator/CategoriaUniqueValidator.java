package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.NovaCategoriaRequest;
import br.com.roger.study.casadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoriaUniqueValidator implements Validator {

    private CategoriaRepository repository;

    public CategoriaUniqueValidator(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovaCategoriaRequest request = (NovaCategoriaRequest) target;

        repository
            .findByNome(request.getNome())
            .ifPresent(c -> {
                errors.reject("cdc.categoria.nome.duplicado", null, "cdc.categoria.nome.duplicado");
            });
    }
}
