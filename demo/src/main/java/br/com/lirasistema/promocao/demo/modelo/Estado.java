
package br.com.lirasistema.promocao.demo.modelo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Paulo
 */
@Entity
public class Estado {
    @Id
    private Integer idEstado;
    private String sigla;
    private String estado;    
    private String cUF;
    @OneToOne()    
    private Pais pais;

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais cod_pais) {
        this.pais = cod_pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String nom_estado) {
        this.estado = nom_estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sgl_estado) {
        this.sigla = sgl_estado;
    }

    @Override
    public String toString() {
        return sigla + " " + estado;
    }

    public String getcUF() {
        return cUF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }
    
    
    
}
