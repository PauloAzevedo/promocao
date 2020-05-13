package br.com.lirasistema.promocao.demo.modelo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    private String caminho;
        
    @ManyToOne
    private Empresa empresa;
    
    @ManyToOne
    private Item item;
     
    private Integer tipo; //0 - logo; 1 - Item

    public Arquivo() {
    }

    public Arquivo(String caminho, Empresa empresa, Item item, Integer tipo) {
        this.caminho = caminho;
        this.empresa = empresa;
        this.item = item;
        this.tipo = tipo;
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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    
     
}
