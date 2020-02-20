package br.com.lirasistema.promocao.demo.modelo;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author paulo
 */
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome_RazaoSocial;
    private String fantasia;
    private String telefone;
    private String celular;
    private String email;
    private String rg_InscricaoEstadual;
    private String cpf_Cnpj;
    private String sexo;
    private Boolean situacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataNascimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataCadastro;
    private String responsavel;
    private String telefoneResponsavel; 
    @ManyToOne(fetch=FetchType.LAZY)
    private Endereco endereco; 
    @Column(columnDefinition = "text")
    private String observacao;
    
    private Integer tipo;
    private Integer pessoa;
    private String cpfResponsavel;
    
    @ManyToOne
    private Empresa empresa;
    
    private Integer idDaEmpresa;
    
    private Integer excluido;
    
    //@ManyToOne
    //private CentroCusto centroCusto;

    public Cliente(String nome_RazaoSocial, String fantasia, String telefone, String celular, String email, String rg_InscricaoEstadual, String cpf_Cnpj, String responsavel, String telefoneResponsavel, String observacao, Integer tipo, Integer pessoa, String cpfResponsavel, Endereco endereco, Empresa empresa, Integer idDaEmpresa) {
        this.nome_RazaoSocial = nome_RazaoSocial;
        this.fantasia = fantasia;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.rg_InscricaoEstadual = rg_InscricaoEstadual;
        this.cpf_Cnpj = cpf_Cnpj;
        this.responsavel = responsavel;
        this.telefoneResponsavel = telefoneResponsavel;        
        this.observacao = observacao;
        this.tipo = tipo;
        this.pessoa = pessoa;
        this.cpfResponsavel = cpfResponsavel;
        this.empresa = empresa;
        this.endereco = endereco;
        this.excluido = 0;
        this.situacao = true;
        this.dataCadastro = Calendar.getInstance();
        this.idDaEmpresa = idDaEmpresa;
    }
    
    public Cliente(){        
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_RazaoSocial() {
        return nome_RazaoSocial;
    }

    public void setNome_RazaoSocial(String nome_RazaoSocial) {
        this.nome_RazaoSocial = nome_RazaoSocial;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg_InscricaoEstadual() {
        return rg_InscricaoEstadual;
    }

    public void setRg_InscricaoEstadual(String rg_InscricaoEstadual) {
        this.rg_InscricaoEstadual = rg_InscricaoEstadual;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = cpf_Cnpj;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }

    public Integer getExcluido() {
        return excluido;
    }

    public void setExcluido(Integer excluido) {
        this.excluido = excluido;
    }

//    public CentroCusto getCentroCusto() {
//        return centroCusto;
//    }
//
//    public void setCentroCusto(CentroCusto centroCusto) {
//        this.centroCusto = centroCusto;
//    }

    @Override
    public String toString() {
        return idDaEmpresa + " - " + nome_RazaoSocial;
    }
    
    public String getTipoWeb(){
        if(tipo==0){
            return "Cliente";
        } else if (tipo==1){
            return "Fornecedor";
        } else {
            return "Transportadora";
        }
    }
    
    
}
