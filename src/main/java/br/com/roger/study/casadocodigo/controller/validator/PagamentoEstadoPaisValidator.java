package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.CompraCreateRequest;
import br.com.roger.study.casadocodigo.model.Estado;
import br.com.roger.study.casadocodigo.model.Pais;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PagamentoEstadoPaisValidator implements ConstraintValidator<PagamentoEstadoPaisValid, CompraCreateRequest> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(PagamentoEstadoPaisValid annotation) {
        //nada a fazer aqui
    }

    @Override
    public boolean isValid(CompraCreateRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }
        //Retornando que é valido quando documento é nulo para deixar a validação da existência para a anotação @NotBlank
        if (request.getIdPais() == null) {
            return true;
        }

        final Pais pais = em.find(Pais.class, request.getIdPais());
        if (pais == null) {
            return true;
        }

        if (!pais.getEstados().isEmpty()) {
            if (request.getIdEstado() == null) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.obrigatorio");
                return false;
            }
            final Estado estado = em.find(Estado.class, request.getIdEstado());
            if (estado == null) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.naocadastrado");
                return false;
            }
            if (!estado.getPais().getId().equals(request.getIdPais())) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.naoassociadopais");
                return false;
            }
        }

        return true;
    }

    private void changeMessageTemplate(ConstraintValidatorContext context, String messageTemplate) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation();
    }
}
