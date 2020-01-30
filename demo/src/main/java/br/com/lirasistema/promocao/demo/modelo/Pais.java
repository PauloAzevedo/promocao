/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Paulo
 */
@Entity
public class Pais {
    @Id
    private Integer idPais; 
    private String sigla;
    private String pais;
    private String codigoPais;

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    

    public String getPais() {
        return pais;
    }

    public void setPais(String nom_pais) {
        this.pais = nom_pais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sgl_pais) {
        this.sigla = sgl_pais;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
    
    @Override
    public String toString() {
        return idPais + " - " + pais + " - " + sigla;
    }
    
    
}
