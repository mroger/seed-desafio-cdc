package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.CPFCNPJ;
import br.com.roger.study.casadocodigo.controller.validator.CupomValido;
import br.com.roger.study.casadocodigo.controller.validator.Exists;
import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.controller.validator.PagamentoEstadoPaisValid;
import br.com.roger.study.casadocodigo.model.Compra;
import br.com.roger.study.casadocodigo.model.Cupom;
import br.com.roger.study.casadocodigo.model.Estado;
import br.com.roger.study.casadocodigo.model.Pais;
import br.com.roger.study.casadocodigo.model.Pedido;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.function.Function;

@PagamentoEstadoPaisValid
public class CompraCreateRequest {

    @NotBlank(message = "cdc.pagamento.email.obrigatorio")
    @Email(message = "cdc.pagamento.email.invalido")
    private String email;
    @NotBlank(message = "cdc.pagamento.nome.obrigatorio")
    private String nome;
    @NotBlank(message = "cdc.pagamento.sobrenome.obrigatorio")
    private String sobrenome;
    @NotBlank(message = "cdc.pagamento.documento.obrigatorio")
    @CPFCNPJ
    private String documento;
    @NotBlank(message = "cdc.pagamento.endereco.obrigatorio")
    private String endereco;
    @NotBlank(message = "cdc.pagamento.complemento.obrigatorio")
    private String complemento;
    @NotBlank(message = "cdc.pagamento.cidade.obrigatorio")
    private String cidade;
    @NotNull(message = "cdc.pagamento.idPais.obrigatorio")
    @ExistsId(clazz = Pais.class)
    private Long idPais;
    private Long idEstado;
    @NotBlank(message = "cdc.pagamento.telefone.obrigatorio")
    private String telefone;
    @NotBlank(message = "cdc.pagamento.cep.obrigatorio")
    private String cep;
    @Exists(clazz = Cupom.class, field = "codigo")
    @CupomValido
    private String codigoCupom;
    @NotNull(message = "cdc.pagamento.carrinho.obrigatorio")
    @Valid
    private PedidoCreateRequest pedido;

    public Compra toModel(EntityManager em) {
        @NotNull Pais pais = em.find(Pais.class, idPais);
        Assert.state(pais != null, "Não foi encontrado país para associar à Compra: " + idPais);

        @NotNull final Function<Compra, Pedido> fabricaPedidos = this.pedido.toModel(em);
        Assert.state(pedido != null, "Não foi possível criar a conta");

        Compra.CompraBuilder builder = new Compra.CompraBuilder()
            .withEmail(email)
            .withNome(nome)
            .withSobrenome(sobrenome)
            .withDocumento(documento)
            .withEndereco(endereco)
            .withComplemento(complemento)
            .withCidade(cidade)
            .withTelefone(telefone)
            .withCep(cep)
            .withPais(pais)
            .withPedido(fabricaPedidos);

        if (idEstado != null) {
            Assert.isTrue(pais.possuiEstados(), "O Estado não pertence ao País");

            Estado estado = em.find(Estado.class, idEstado);
            Assert.state((idEstado != null && estado != null), "Não foi encontrado estado para associar à Compra: " + idEstado);
            //Ficaria melhor dentro do builder, mas aí vai depender da ordem da montagem: pais tem que ser atribuido antes do estado
            Assert.isTrue(estado.pertenceA(pais), "O Estado não pertence ao País");

            builder.withEstado(estado);
        }

        if (codigoCupom != null) {
            Cupom cupom = em.createQuery("from Cupom c where c.codigo = :codigo", Cupom.class)
                .setParameter("codigo", codigoCupom)
                .getSingleResult();
            Assert.notNull(cupom, "O Cupom referente ao codigo não existe: " + codigoCupom);
            Assert.isTrue(cupom.getValidade().equals(LocalDate.now()) ||
                cupom.getValidade().isAfter(LocalDate.now()), "cdc.cupom.invalido");
            builder.withCupom(cupom);
        }

        return builder.build();
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public PedidoCreateRequest getPedido() {
        return pedido;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }
}
