package br.com.roger.study.casadocodigo.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

class CupomTest {

    @ParameterizedTest
    @CsvSource({
        "0, true",
        "1, true",
        "10, true",
        "-1, false",
        "-10, false"
        })
    void testValido(int dateOffset, boolean result) {
        Cupom cupom = new Cupom("12345", BigDecimal.TEN, LocalDate.now());
        ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().plusDays(dateOffset));
        Assertions.assertThat(cupom.valido() == result).isTrue();
    }
}
