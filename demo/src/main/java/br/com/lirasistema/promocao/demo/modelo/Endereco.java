package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Endereco {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String tpLogradouro;
    private String bairro;
    private String numero;
    private String cep; 
    private String complemento;
    @ManyToOne
    private Cidade cidadeObjeto;    
    private String cidade;

    public Integer getId() {
        return id;
    }
    
    public Endereco(){
        
    }

    public Endereco(String logradouro, String tpLogradouro, String bairro, String numero, String cep, String complemento, Cidade cidadeObjeto, String cidade) {
        this.logradouro = logradouro;
        this.tpLogradouro = tpLogradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.cidadeObjeto = cidadeObjeto;
        this.cidade = cidade;
    }
    
    

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(String tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidadeObjeto() {
        return cidadeObjeto;
    }

    public void setCidadeObjeto(Cidade cidadeObjeto) {
        this.cidadeObjeto = cidadeObjeto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
}
