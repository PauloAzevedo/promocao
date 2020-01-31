package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Integer idDaEmpresa;
    private String descricao;    
    private String referencia;    
    private String composicao;
    private Double valor;
    private String unidadeComercial;
    private String caminhoImagem;    
    private String hashTexto;    
    private Integer situacao; // 1 - Ativo e 0 - Inativo
    
    @ManyToOne
    private Empresa empresa;    
    @ManyToOne
    private Grupo grupo;
    
    public Item(){
        
    }

    public Item(Integer idDaEmpresa, String descricao, String referencia, String composicao, Double valor, String unidadeComercial, String caminhoImagem, String hashTexto, Integer situacao, Empresa empresa, Grupo grupo) {
        this.idDaEmpresa = idDaEmpresa;
        this.descricao = descricao;
        this.referencia = referencia;
        this.composicao = composicao;
        this.valor = valor;
        this.unidadeComercial = unidadeComercial;
        this.caminhoImagem = caminhoImagem;
        this.hashTexto = hashTexto;
        this.situacao = situacao;
        this.empresa = empresa;
        this.grupo = grupo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getUnidadeComercial() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(String unidadeComercial) {
        this.unidadeComercial = unidadeComercial;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    
}
