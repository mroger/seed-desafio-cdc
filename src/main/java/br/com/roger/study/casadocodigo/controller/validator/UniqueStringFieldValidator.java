package br.com.roger.study.casadocodigo.controller.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Carga: 1
 */
public class UniqueStringFieldValidator implements ConstraintValidator<Unique, String> {

    @PersistenceContext
    private EntityManager em;
    private Class<?> clazz;
    private String fieldName;

    //1
    @Override
    public void initialize(Unique annotation) {
        this.clazz = annotation.clazz();
        this.fieldName = annotation.field();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        final String queryStr = String.format("select e from %s e where e.%s = :%s", clazz.getSimpleName(), fieldName, fieldName);

        final List<?> resultList = em.createQuery(queryStr, clazz)
            .setParameter(fieldName, fieldValue)
            .getResultList();

        final String stateMessage = String.format("Foi encontrado mais de um %s com o mesmo %s no banco", clazz.getSimpleName(), fieldName);
        Assert.state(resultList.size() <= 1, stateMessage);

        return resultList.isEmpty();
    }
}
