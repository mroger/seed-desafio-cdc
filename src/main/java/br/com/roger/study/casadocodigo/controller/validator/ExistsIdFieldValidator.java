package br.com.roger.study.casadocodigo.controller.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdFieldValidator implements ConstraintValidator<ExistsId, Long> {

    @PersistenceContext
    private EntityManager em;
    private Class<?> clazz;

    @Override
    public void initialize(ExistsId annotation) {
        this.clazz = annotation.clazz();
    }

    @Override
    public boolean isValid(Long fieldValue, ConstraintValidatorContext context) {
        final String queryStr = String.format("select e from %s e where e.id = :id", clazz.getSimpleName());

        final List<?> resultList = em.createQuery(queryStr, clazz)
            .setParameter("id", fieldValue)
            .getResultList();

        return !resultList.isEmpty();
    }
}
