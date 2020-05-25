package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Empresa;
import org.springframework.data.domain.Page;


public class EmpresaDto {
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
    private Double valorFrete;
    
    public EmpresaDto(Empresa emp) {
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
        this.valorFrete = emp.getValorFrete();
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

    public Double getValorFrete() {
        return valorFrete;
    }
    
      
    public static Page<EmpresaDto> converter(Page<Empresa> empresas) {
        return empresas.map(EmpresaDto::new);
    }
    
}
