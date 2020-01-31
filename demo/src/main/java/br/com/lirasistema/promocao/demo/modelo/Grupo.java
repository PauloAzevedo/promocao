package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    private String descricao;
    
    private Integer situacao; // 1 - Ativo; 0 - Inativo
    
    @ManyToOne
    private Empresa empresa;

    public Integer getId() {
        return id;
    }
    
    public Grupo(){
        
    }
    
    public Grupo(String descricao, Empresa empresa){
        this.descricao = descricao;
        this.empresa = empresa;
        this.situacao = 1;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
        
    
}
