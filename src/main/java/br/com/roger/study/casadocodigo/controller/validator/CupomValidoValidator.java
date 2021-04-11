package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.model.Cupom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CupomValidoValidator implements ConstraintValidator<CupomValido, String> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(CupomValido annotation) {
        //nada a fazer aqui
    }

    @Override
    public boolean isValid(String codigoCupom, ConstraintValidatorContext context) {
        if (codigoCupom == null) {
            return true;
        }
        try {
            final Cupom cupom = em.createQuery("from Cupom c where codigo = :codigoCupom", Cupom.class)
                .setParameter("codigoCupom", codigoCupom)
                .getSingleResult();

            return cupom.getValidade().equals(LocalDate.now()) ||
                cupom.getValidade().isAfter(LocalDate.now());
        } catch (NoResultException e) {
            changeMessageTemplate(context);
            return false;
        }
    }

    private void changeMessageTemplate(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("cdc.cupom.naoencontrado")
            .addConstraintViolation();
    }
}
