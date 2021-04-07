package br.com.roger.study.casadocodigo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_generator")
    @SequenceGenerator(name="compra_generator", sequenceName = "compra_sequence")
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @Deprecated
    public Compra() {
    }

    public Compra(String email, String nome, String sobrenome, String documento, String endereco, String complemento,
                  String cidade, String telefone, String cep, Pais pais, Estado estado, Function<Compra, Pedido> fabricaPedidos) {

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.pedido = fabricaPedidos.apply(this);
    }

    public static class CompraBuilder {

        private String email;
        private String nome;
        private String sobrenome;
        private String documento;
        private String endereco;
        private String complemento;
        private String cidade;
        private String telefone;
        private String cep;
        private Pais pais;
        private Estado estado;
        private Function<Compra, Pedido> fabricaPedidos;

        public CompraBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CompraBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public CompraBuilder withSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
            return this;
        }

        public CompraBuilder withDocumento(String documento) {
            this.documento = documento;
            return this;
        }

        public CompraBuilder withEndereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public CompraBuilder withComplemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public CompraBuilder withCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public CompraBuilder withTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public CompraBuilder withCep(String cep) {
            this.cep = cep;
            return this;
        }

        public CompraBuilder withPais(Pais pais) {
            this.pais = pais;
            return this;
        }

        public CompraBuilder withEstado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public CompraBuilder withPedido(Function<Compra, Pedido> fabricaPedidos) {
            this.fabricaPedidos = fabricaPedidos;
            return this;
        }

        public Compra build() {
            return new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, telefone, cep,
                pais, estado, fabricaPedidos);
        }
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

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pedido getCompra() {
        return pedido;
    }
}