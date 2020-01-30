package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Empresa;


public class DetalhesEmpresaDto {
    private Integer id;    
    private String fantasia;    
    private String razaosocial;     
    private String telefone;    
    private String celular;    
    private String caminhoLogo;    
    private Integer ramo;  // 0 - Alimentação    
    private Integer lojaAtiva; 
    private String hashTexto;    
    private String cnpj;    
    private EnderecoDto endereco;
    
    public DetalhesEmpresaDto(Empresa emp) {
        this.id = emp.getId();
        this.fantasia = emp.getFantasia();
        this.razaosocial = emp.getRazaosocial();
        this.telefone = emp.getTelefone();
        this.celular = emp.getCelular();
        this.caminhoLogo = emp.getCaminhoLogo();
        this.ramo = emp.getRamo();
        this.lojaAtiva = emp.getLojaAtiva();
        this.hashTexto = emp.getHashTexto();
        this.cnpj = emp.getCnpj();
        this.endereco = new EnderecoDto(emp.getEndereco());
    }

    public Integer getId() {
        return id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }

    public String getCaminhoLogo() {
        return caminhoLogo;
    }

    public Integer getRamo() {
        return ramo;
    }

    public Integer getLojaAtiva() {
        return lojaAtiva;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public String getCnpj() {
        return cnpj;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

       
    
}
