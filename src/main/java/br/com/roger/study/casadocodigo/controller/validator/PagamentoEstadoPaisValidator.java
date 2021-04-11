package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.CompraCreateRequest;
import br.com.roger.study.casadocodigo.model.Estado;
import br.com.roger.study.casadocodigo.model.Pais;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Carga: 10
 */
public class PagamentoEstadoPaisValidator implements ConstraintValidator<PagamentoEstadoPaisValid, CompraCreateRequest> {

    @PersistenceContext
    private EntityManager em;

    //1
    @Override
    public void initialize(PagamentoEstadoPaisValid annotation) {
        //nada a fazer aqui
    }

    @Override
    public boolean isValid(CompraCreateRequest request, ConstraintValidatorContext context) {
        //Retornando que é valido para deixar a validação da existência para a anotação @NotBlank
        //1
        if (request == null || request.getIdPais() == null) {
            return true;
        }

        //1
        final Pais pais = em.find(Pais.class, request.getIdPais());
        if (pais == null) {
            return true;
        }

        //1
        if (request.getIdEstado() != null) {
            //1
            final Estado estado = em.find(Estado.class, request.getIdEstado());
            if (estado == null) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.naocadastrado");
                return false;
            }
        }

        //1
        if (!pais.getEstados().isEmpty()) {
            //1
            if (request.getIdEstado() == null) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.obrigatorio");
                return false;
            }
            final Estado estado = em.find(Estado.class, request.getIdEstado());
            //1
            if (!estado.getPais().getId().equals(request.getIdPais())) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.naoassociadopais");
                return false;
            }
        //1
        } else {
            //1
            if (request.getIdEstado() != null) {
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
