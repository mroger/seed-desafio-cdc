package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.time.api.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class CupomControllerTest {

    @Autowired
    private CupomControllerApiTest apiTest;

    @Property(tries = 10)
    @Label("Fluxo de criacao de cupom")
    @DirtiesContext
    void test(
            @ForAll @StringLength(max = 255)  String codigo,
            @ForAll @BigRange(min = "0.01", max = "99999999999999999") BigDecimal desconto,
            @ForAll("futureDate")  LocalDate validade) throws Exception {

        apiTest.test(codigo, desconto, validade);
    }

    @Provide
    Arbitrary<LocalDate> futureDate() {
        return Dates.dates().atTheEarliest(LocalDate.now().plusDays(1L));
    }
}
