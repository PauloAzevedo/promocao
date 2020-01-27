package br.com.lirasistema.promocao.demo.modelo;

import br.com.lirasistema.promocao.demo.utilidades.Util;
import java.time.LocalDateTime;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCriacao = LocalDateTime.now();    
    private String titulo;
    private String descricao;
    private Double valor;
    private String hashTexto;
    @ManyToOne
    private UsuarioApi autor;
    
    private Integer situacao; //0 = Ativo; 1 = Inativo
    
    public Promocao(){
        this.hashTexto = Util.md5(String.valueOf(Calendar.getInstance().getTimeInMillis() + Util.gerarNumeroAleatorio(99999)));        
        this.situacao = 0;
    }
    
    public Promocao(String titulo, String descricao, Double valor, UsuarioApi autor){
        this.hashTexto = Util.md5(String.valueOf(Calendar.getInstance().getTimeInMillis() + Util.gerarNumeroAleatorio(99999)));        
        this.situacao = 0;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public UsuarioApi getAutor() {
        return autor;
    }

    public void setAutor(UsuarioApi autor) {
        this.autor = autor;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
    
    
    
}
