package br.com.roger.study.casadocodigo.controller.validator;

import br.com.roger.study.casadocodigo.model.Cupom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CupomValidoValidatorTest {

    private ConstraintValidatorContext context;

    @Mock
    private EntityManager em;

    @InjectMocks
    private CupomValidoValidator validator;

    @BeforeEach
    void setUp() {
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void shouldBeValid_whenCupomIsNull() {
        final boolean isValid = validator.isValid(null, context);

        Assertions.assertThat(isValid).isTrue();
    }

    @Test
    void shouldBeValid_whenCupomIsDueToday() {
        givenCupomDueIn(today());

        final boolean isValid = validator.isValid("1234", context);

        Assertions.assertThat(isValid).isTrue();
    }

    @Test
    void shouldBeValid_whenCupomIsDueTomorrow() {
        givenCupomDueIn(tomorrow());

        final boolean isValid = validator.isValid("1234", context);

        Assertions.assertThat(isValid).isTrue();
    }

    @Test
    void shouldNotBeValid_whenCupomIsDueYesterday() {
        givenCupomDueIn(yesterday());

        final boolean isValid = validator.isValid("1234", context);

        Assertions.assertThat(isValid).isFalse();
    }

    @Test
    void shouldNotBeValid_whenCupomDoesNotExist() {
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(builder.addConstraintViolation()).thenReturn(null);
        when(context.buildConstraintViolationWithTemplate("cdc.cupom.naoencontrado"))
            .thenReturn(builder);
        givenNoCupomExist();

        final boolean isValid = validator.isValid("1234", context);

        Assertions.assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("cdc.cupom.naoencontrado");
    }

    private void givenCupomDueIn(LocalDate dueDate) {
        TypedQuery<Cupom> typedQuery = mock(TypedQuery.class);
        when(typedQuery.setParameter("codigoCupom", "1234")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(createCupomDueIn(dueDate));
        when(em.createQuery("from Cupom c where codigo = :codigoCupom", Cupom.class))
            .thenReturn(typedQuery);
    }

    private void givenNoCupomExist() {
        TypedQuery<Cupom> typedQuery = mock(TypedQuery.class);
        when(typedQuery.setParameter("codigoCupom", "1234")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);
        when(em.createQuery("from Cupom c where codigo = :codigoCupom", Cupom.class))
            .thenReturn(typedQuery);
    }

    private LocalDate tomorrow() {
        return today().plusDays(1);
    }

    private LocalDate yesterday() {
        return today().minusDays(1);
    }

    private LocalDate today() {
        return LocalDate.now();
    }

    private Cupom createCupomDueIn(LocalDate dueDate) {
        final Cupom cupom = new Cupom("1234", BigDecimal.ONE, LocalDate.now());
        ReflectionTestUtils.setField(cupom, "validade", dueDate);
        return cupom;
    }
}
