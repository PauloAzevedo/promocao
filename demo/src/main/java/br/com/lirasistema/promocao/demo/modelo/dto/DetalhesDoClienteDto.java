package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Cliente;
import br.com.lirasistema.promocao.demo.utilidades.Util;

public class DetalhesDoClienteDto {

    //private Integer id;
    private String nome_RazaoSocial;
    private String fantasia;
    private String telefone;
    private String celular;
    private String email;
    private String rg_InscricaoEstadual;
    private String cpf_Cnpj;
    private String sexo;
    private Boolean situacao;
    private String dataNascimento;
    private String dataCadastro;
    private String responsavel;
    private String telefoneResponsavel;
    private EnderecoDto endereco;
    private String observacao;
    private Integer tipo;
    private Integer pessoa;
    private String cpfResponsavel;
    private Integer idDaEmpresa;
    private Integer excluido;

    //private String centroCusto;

    public DetalhesDoClienteDto(Cliente cliente) {
        //this.id = cliente.getId();
        this.idDaEmpresa = cliente.getIdDaEmpresa();
        this.nome_RazaoSocial = cliente.getNome_RazaoSocial();
        this.fantasia = cliente.getFantasia();
        this.telefone = cliente.getTelefone();
        this.celular = cliente.getCelular();
        this.email = cliente.getEmail();
        this.rg_InscricaoEstadual = cliente.getRg_InscricaoEstadual();
        this.cpf_Cnpj = cliente.getCpf_Cnpj();
        this.sexo = cliente.getSexo();
        this.situacao = cliente.getSituacao();
        this.dataNascimento = Util.calendarToString(cliente.getDataNascimento());
        this.dataCadastro = Util.calendarToString(cliente.getDataCadastro());
        this.responsavel = cliente.getResponsavel();
        this.telefoneResponsavel = cliente.getTelefoneResponsavel();
        if (cliente.getEndereco() != null) {
            this.endereco = new EnderecoDto(cliente.getEndereco());
        }
        this.observacao = cliente.getObservacao();
        this.tipo = cliente.getTipo();
        this.pessoa = cliente.getPessoa();
        this.cpfResponsavel = cliente.getCpfResponsavel();        
        this.excluido = cliente.getExcluido();
        //if (cliente.getCentroCusto() != null) {
        //    this.centroCusto = cliente.getCentroCusto().toString();
        //}
    }

//    public Integer getId() {
//        return id;
//    }
    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }
    
    public String getNome_RazaoSocial() {
        return nome_RazaoSocial;
    }

    public String getFantasia() {
        return fantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getRg_InscricaoEstadual() {
        return rg_InscricaoEstadual;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public String getSexo() {
        return sexo;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public Integer getExcluido() {
        return excluido;
    }

//    public String getCentroCusto() {
//        return centroCusto;
//    }

}
