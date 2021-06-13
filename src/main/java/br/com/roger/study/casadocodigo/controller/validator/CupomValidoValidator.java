package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.model.Cupom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Carga: 5
 */
public class CupomValidoValidator implements ConstraintValidator<CupomValido, String> {

    @PersistenceContext
    private EntityManager em;

    //1
    @Override
    public void initialize(CupomValido annotation) {
        //nada a fazer aqui
    }

    @Override
    public boolean isValid(String codigoCupom, ConstraintValidatorContext context) {
        //1
        if (codigoCupom == null) {
            return true;
        }
        //1
        try {
            //1
            final Cupom cupom = em.createQuery("from Cupom c where codigo = :codigoCupom", Cupom.class)
                .setParameter("codigoCupom", codigoCupom)
                .getSingleResult();

            return cupom.valido();
        //1
        } catch (NoResultException e) {
            changeMessageTemplate(context, "cdc.cupom.naoencontrado");
            return false;
        }
    }

    private void changeMessageTemplate(ConstraintValidatorContext context, String template) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(template)
            .addConstraintViolation();
    }
}
