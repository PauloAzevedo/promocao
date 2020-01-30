package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Endereco;



/**
 *
 * @author paulo
 */
public class EnderecoDto {
    private Integer id;
    private String logradouro;
    private String tpLogradouro;
    private String bairro;
    private String numero;
    private String cep; 
    private String complemento;    
    private String cidadeObjeto;    
    private String cidade;
    
    public EnderecoDto(Endereco end){
        this.id = end.getId();
        this.logradouro = end.getLogradouro();
        this.tpLogradouro = end.getTpLogradouro();
        this.bairro = end.getBairro();
        this.numero = end.getNumero();
        this.cep = end.getCep();
        this.complemento = end.getComplemento();
        this.cidadeObjeto = end.getCidadeObjeto().toString();
        this.cidade = end.getCidade();
    }

    public Integer getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidadeObjeto() {
        return cidadeObjeto;
    }

    public String getCidade() {
        return cidade;
    }
    
    
}
