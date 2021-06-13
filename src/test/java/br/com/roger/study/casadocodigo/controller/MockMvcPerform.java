package br.com.roger.study.casadocodigo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class MockMvcPerform {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ResultActions post(String url, Map<String, ?> payloadMap) throws Exception {

        final String payload = objectMapper.writeValueAsString(payloadMap);

        return mockMvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload));
    }

    public ResultActions get(String url, long idLivro) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get(url, idLivro)
            .contentType(MediaType.APPLICATION_JSON));
    }

    public static String string(BigDecimal value) {
        return value.toString();
    }

    public static String string(Integer value) {
        return value.toString();
    }

    public static String string(LocalDate value) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(value);
    }

    public static String string(Long value) {
        return value.toString();
    }
}
