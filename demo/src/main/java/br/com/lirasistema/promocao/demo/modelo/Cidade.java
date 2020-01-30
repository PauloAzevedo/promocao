
package br.com.lirasistema.promocao.demo.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author a1028367
 */
@Entity
public class Cidade  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCidade;
    private String cidade;   
    private String codigoIBGE;
    
    @OneToOne
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Integer id) {
        this.idCidade = id;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setId(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setNome(String nome) {
        this.cidade = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }  

    public String getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(String codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoIBGE + " - " + cidade + " - " + estado.getSigla();
    }
    
    
    public String codigoComCidadeWeb(){
        return codigoIBGE + " - " + cidade + " - " + estado.getSigla();
    }
    
    
    
}
