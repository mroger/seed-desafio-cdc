package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.CPFCNPJ;
import br.com.roger.study.casadocodigo.controller.validator.PagamentoEstadoPaisValid;
import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.model.Pais;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PagamentoEstadoPaisValid
public class PagamentoCreateRequest {

    @NotEmpty(message = "cdc.pagamento.email.obrigatorio")
    @Email(message = "cdc.pagamento.email.invalido")
    private String email;
    @NotEmpty(message = "cdc.pagamento.nome.obrigatorio")
    private String nome;
    @NotEmpty(message = "cdc.pagamento.sobrenome.obrigatorio")
    private String sobrenome;
    @NotEmpty(message = "cdc.pagamento.documento.obrigatorio")
    @CPFCNPJ
    private String documento;
    @NotEmpty(message = "cdc.pagamento.endereco.obrigatorio")
    private String endereco;
    @NotEmpty(message = "cdc.pagamento.complemento.obrigatorio")
    private String complemento;
    @NotEmpty(message = "cdc.pagamento.cidade.obrigatorio")
    private String cidade;
    @NotNull(message = "cdc.pagamento.idPais.obrigatorio")
    @ExistsId(clazz = Pais.class)
    private Long idPais;
    private Long idEstado;
    @NotEmpty(message = "cdc.pagamento.telefone.obrigatorio")
    private String telefone;
    @NotEmpty(message = "cdc.pagamento.cep.obrigatorio")
    private String cep;

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
}
