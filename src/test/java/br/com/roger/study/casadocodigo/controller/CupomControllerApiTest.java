package br.com.roger.study.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static br.com.roger.study.casadocodigo.controller.MockMvcPerform.string;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class CupomControllerApiTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    public void test(String codigo, BigDecimal desconto, LocalDate validade) throws Exception {

        mockMvcPerform.post("/cupons", Map.of("codigo", codigo, "desconto", desconto, "validade", string(validade)))
            .andExpect(status().isCreated());

        mockMvcPerform.post("/cupons", Map.of("codigo", codigo, "desconto", desconto, "validade", string(validade)))
            .andExpect(status().isBadRequest());
    }
}
