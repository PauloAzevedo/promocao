package br.com.lirasistema.promocao.demo.modelo;

import br.com.lirasistema.promocao.demo.utilidades.Util;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    private String fantasia;    
    private String razaosocial;     
    private String telefone;    
    private String celular;    
    @Column(columnDefinition = "text")
    private String caminhoLogo;    
    private Integer ramo;  // 0 - Alimentação    
    private Integer lojaAtiva;        
    private String hashTexto;    
    private String cnpj;
    
    @ManyToOne
    private Endereco endereco;
    
    private Double valorFrete;
    
    
    public Empresa(){
        
    }

    public Empresa(String fantasia, String razaosocial, String telefone, String celular, String caminhoLogo, Integer ramo, String cnpj, Endereco endereco) {
        this.fantasia = fantasia;
        this.razaosocial = razaosocial;
        this.telefone = telefone;
        this.celular = celular;
        this.caminhoLogo = caminhoLogo;
        this.ramo = ramo;
        this.lojaAtiva = 1;
        this.hashTexto = Util.md5(String.valueOf(Calendar.getInstance().getTimeInMillis() + Util.gerarNumeroAleatorio(99999)));        
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.valorFrete = 0.0;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCaminhoLogo() {
        return caminhoLogo;
    }

    public void setCaminhoLogo(String caminhoLogo) {
        this.caminhoLogo = caminhoLogo;
    }

    public Integer getRamo() {
        return ramo;
    }

    public void setRamo(Integer ramo) {
        this.ramo = ramo;
    }

    public Integer getLojaAtiva() {
        return lojaAtiva;
    }

    public void setLojaAtiva(Integer lojaAtiva) {
        this.lojaAtiva = lojaAtiva;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return id + " - " + fantasia;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }
        
        
}
