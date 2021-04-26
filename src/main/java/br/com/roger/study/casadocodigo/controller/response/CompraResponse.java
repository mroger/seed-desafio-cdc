package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Compra;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class CompraResponse {

    private Long id;

    private String email;

    private String nome;

    private String sobrenome;

    private String documento;

    private String endereco;

    private String complemento;

    private String cidade;

    private String telefone;

    private String cep;

    private PaisResponse pais;

    private EstadoResponse estado;

    private PedidoResponse pedido;

    private BigDecimal valorOriginal;

    private BigDecimal valorFinal;

    private Boolean isCupomAplicado = Boolean.FALSE;

    public static CompraResponse fromModel(Compra compra) {
        CompraResponse compraResponse = new CompraResponse();
        BeanUtils.copyProperties(compra, compraResponse, "pais", "estado", "cupomAplicado", "pedido");

        compraResponse.setPaisResponse(PaisResponse.fromModel(compra.getPais()));
        compraResponse.setEstadoResponse(EstadoResponse.fromModel(compra.getEstado()));
        compraResponse.setPedidoResponse(PedidoResponse.fromModel(compra.getPedido()));
        compraResponse.setValorOriginal(compra.getValorOriginal());
        if (compra.getCupomAplicado() != null) {
            compraResponse.setIsCupomAplicado(true);
            compraResponse.setValorFinal(compra.getValorFinal());
        }
        return compraResponse;
    }

    private void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    private void setPedidoResponse(PedidoResponse pedido) {
        this.pedido = pedido;
    }

    private void setIsCupomAplicado(boolean cupomAplicado) {
        this.isCupomAplicado = cupomAplicado;
    }

    private void setEstadoResponse(EstadoResponse estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
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

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public PaisResponse getPais() {
        return pais;
    }

    public EstadoResponse getEstado() {
        return estado;
    }

    public PedidoResponse getPedido() {
        return pedido;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public Boolean getCupomAplicado() {
        return isCupomAplicado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setPaisResponse(PaisResponse pais) {
        this.pais = pais;
    }

    public void setPedido(PedidoResponse pedido) {
        this.pedido = pedido;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }
}
