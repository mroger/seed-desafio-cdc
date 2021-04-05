package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.controller.request.PagamentoCreateRequest;
import br.com.roger.study.casadocodigo.model.Estado;
import br.com.roger.study.casadocodigo.model.Pais;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EstadoPaisValidator implements ConstraintValidator<EstadoPaisValid, PagamentoCreateRequest> {

    private String campoEstado;
    private String campoPais;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(EstadoPaisValid annotation) {
        this.campoEstado = annotation.campoEstado();
        this.campoPais = annotation.campoPais();
    }

    @Override
    public boolean isValid(PagamentoCreateRequest request, ConstraintValidatorContext context) {
        Long idPais = getRequestPropertyValue(request, campoPais);
        //Retornando que é valido quando documento é nulo para deixar a validação da existência para a anotação @NotBlank
        if (idPais == null) {
            return true;
        }

        final List<Pais> resultList = em.createQuery("from Pais p where p.id = :" + campoPais + " and p.estados is not empty ", Pais.class)
            .setParameter("idPais", idPais)
            .getResultList();

        if (!resultList.isEmpty()) {
            Long idEstado = getRequestPropertyValue(request, campoEstado);
            if (idEstado == null) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.obrigatorio");
                return false;
            }
            final List<Estado> estados = em.createQuery("select e from Estado e join e.pais p where e.id = :" + campoEstado + " and p.id = :" + campoPais, Estado.class)
                .setParameter(campoEstado, idEstado)
                .setParameter(campoPais, idPais)
                .getResultList();
            if (estados.isEmpty()) {
                changeMessageTemplate(context, "cdc.pagamento.idEstado.naoexiste");
                return false;
            }
        }

        return true;
    }

    private Long getRequestPropertyValue(PagamentoCreateRequest request, String campo) {
        try {
            String value = BeanUtils.getProperty(request, campo);
            return value == null ? null : Long.valueOf(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void changeMessageTemplate(ConstraintValidatorContext context, String messageTemplate) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation();
    }
}
