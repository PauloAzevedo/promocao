package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author paulo
 */
@Entity
public class CentroCusto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idDaEmpresa;
    private String descricao;
    
    @ManyToOne
    private Empresa empresa;

    public CentroCusto() {
    }

    public CentroCusto(Integer idDaEmpresa, String descricao, Empresa empresa) {
        this.idDaEmpresa = idDaEmpresa;
        this.descricao = descricao;
        this.empresa = empresa;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return  idDaEmpresa + " - " + descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    
    
    
}
