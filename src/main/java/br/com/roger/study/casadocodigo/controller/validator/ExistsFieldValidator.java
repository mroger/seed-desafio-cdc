package br.com.roger.study.casadocodigo.controller.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsFieldValidator implements ConstraintValidator<Exists, String> {

    @PersistenceContext
    private EntityManager em;
    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(Exists annotation) {
        this.clazz = annotation.clazz();
        this.fieldName = annotation.field();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        if (fieldValue == null) {
            return true;
        }
        final String queryStr = String.format("select e from %s e where e.%s = :%s", clazz.getSimpleName(), fieldName, fieldName);

        final List<?> resultList = em.createQuery(queryStr, clazz)
            .setParameter(fieldName, fieldValue)
            .getResultList();

        return !resultList.isEmpty();
    }
}
