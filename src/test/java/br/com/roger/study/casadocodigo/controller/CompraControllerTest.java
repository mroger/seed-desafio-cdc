package br.com.roger.study.casadocodigo.controller;

import br.com.caelum.stella.DigitoPara;
import br.com.roger.study.casadocodigo.controller.request.ItemPedidoCreateRequest;
import br.com.roger.study.casadocodigo.controller.request.PedidoCreateRequest;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Tuple;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.lifecycle.BeforeTry;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.web.api.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static br.com.roger.study.casadocodigo.controller.MockMvcPerform.string;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class CompraControllerTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    @BeforeTry
    void setup() throws Exception {
        mockMvcPerform.post("/categorias", Map.of("nome", "nome"));
        mockMvcPerform.post("/autores", Map.of("nome", "nome", "email", "email@host.com", "descricao", "descricao"));
        mockMvcPerform.post("/paises", Map.of("nome", "nome"));
        mockMvcPerform.post("/estados", Map.of("nome", "nome", "idPais", "1"));
    }

    @Property(tries = 10)
    @Label("Teste de fluxo de compra")
    @DirtiesContext
    void test(
            @ForAll @StringLength(max = 255) @NotBlank @Email String email,
            @ForAll @StringLength(max = 255) @NotBlank String nome,
            @ForAll @StringLength(max = 255) @NotBlank String sobrenome,
            @ForAll("cpfcnpj") @NotBlank String documento,
            @ForAll @StringLength(max = 255) @NotBlank String endereco,
            @ForAll @StringLength(max = 255) @NotBlank String complemento,
            @ForAll @StringLength(max = 255) @NotBlank String cidade,
            @ForAll @StringLength(max = 255) @NotBlank @NumericChars String telefone,
            @ForAll @StringLength(max = 8) @NotBlank @NumericChars String cep,
            @ForAll @StringLength(max = 255) @NotBlank @AlphaChars String codigoCupom,
            @ForAll("validPedido") Tuple.Tuple3<Integer, BigDecimal, BigDecimal> dadosPedido) throws Exception {

        createLivro(dadosPedido.get2(), mockMvcPerform);

        final ItemPedidoCreateRequest item = createItem(dadosPedido.get1());
        List<ItemPedidoCreateRequest> itens = new ArrayList<>();
        itens.add(item);
        final PedidoCreateRequest pedido = new PedidoCreateRequest(dadosPedido.get3(), itens);

        mockMvcPerform.post("/cupons", Map.of("codigo", codigoCupom, "desconto", "0.1",
            "validade", string(LocalDate.now().plusDays(1))));

        mockMvcPerform.post("/compras", map(email, nome, sobrenome, documento, endereco, complemento, cidade, "1", "1", telefone, cep, codigoCupom, pedido))
            .andExpect(status().isCreated());
    }

    private Map<String, Object> map(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, String idPais, String idEstado, String telefone, String cep, String codigoCupom, PedidoCreateRequest pedido) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("nome", nome);
        map.put("sobrenome", sobrenome);
        map.put("documento", documento);
        map.put("endereco", endereco);
        map.put("complemento", complemento);
        map.put("cidade", cidade);
        map.put("idPais", idPais);
        map.put("idEstado", idEstado);
        map.put("telefone", telefone);
        map.put("cep", cep);
        map.put("codigoCupom", codigoCupom);
        map.put("pedido", pedido);
        return map;
    }

    @Provide
    Arbitrary<String> cpfcnpj() {
        return Arbitraries.create(() -> {
            Random random = new Random();
            String document = random.nextInt(2) == 0 ?
                Document.generateCPF() :
                Document.generateCNPJ();
            return document;
        });
    }

    @Provide
    Arbitrary<Tuple.Tuple3<Integer, BigDecimal, BigDecimal>> validPedido() {
        Arbitrary<Integer> quantidades = Arbitraries.integers().greaterOrEqual(1);
        Arbitrary<BigDecimal> precos = Arbitraries.bigDecimals().greaterOrEqual(BigDecimal.valueOf(20L)).lessOrEqual(BigDecimal.valueOf(11602713925812700L));
        return Combinators.combine(quantidades, precos)
            .as((quantidade, preco) -> {
                BigDecimal total = preco.multiply(new BigDecimal(quantidade));
                return Tuple.of(quantidade, preco, total);
            });
    }

    private ItemPedidoCreateRequest createItem(Integer quantidade) {
        return new ItemPedidoCreateRequest(1L, quantidade);
    }

    private void createLivro(BigDecimal preco, MockMvcPerform mockMvcPerform) throws Exception {
        mockMvcPerform.post("/livros", Map.of("titulo", "titulo", "resumo", "resumo", "sumario", "sumario",
            "preco", string(preco), "numeroPaginas", "1000", "isbn", "isbn", "dataPublicacao",
            string(LocalDate.now().plusDays(1L)), "idCategoria", "1", "idAutor", "1"));
    }

    static class Document {

        public static String generateCPF() {
            Random random = new Random();
            int cpf = Math.abs(random.nextInt(999999999));
            String cpfStr = String.valueOf(cpf);
            cpfStr = new String("0").repeat(9) + cpfStr;
            cpfStr = cpfStr.substring(cpfStr.length()-9, cpfStr.length());
            return cpfStr + calculateCpfDigits(cpfStr);
        }

        public static String generateCNPJ() {
            Random random = new Random();
            long cnpj = Math.abs(random.nextLong());
            String cnpjStr = String.valueOf(cnpj);
            cnpjStr = new String("0").repeat(12) + cnpjStr;
            cnpjStr = cnpjStr.substring(cnpjStr.length()-12, cnpjStr.length());
            return cnpjStr + calculateCnpjDigits(cnpjStr);
        }

        private static String calculateCpfDigits(String cpf) {
            return calculateDigits(cpf, () -> new DigitoPara(cpf)
                .comMultiplicadoresDeAte(2, 11)
                .complementarAoModulo()
                .trocandoPorSeEncontrar("0",10,11)
                .mod(11));
        }

        private static String calculateCnpjDigits(String cnpj) {
            return calculateDigits(cnpj, () -> new DigitoPara(cnpj)
                .complementarAoModulo()
                .trocandoPorSeEncontrar("0", 10, 11).mod(11)
                .mod(11));
        }

        private static String calculateDigits(String documento, Supplier<DigitoPara> digitoSup) {
            DigitoPara digitoPara = digitoSup.get();

            String digito1 = digitoPara.calcula();
            digitoPara.addDigito(digito1);
            String digito2 = digitoPara.calcula();

            return digito1 + digito2;
        }
    }
}
