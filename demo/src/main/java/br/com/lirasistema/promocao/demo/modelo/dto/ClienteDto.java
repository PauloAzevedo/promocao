package br.com.lirasistema.promocao.demo.modelo.dto;


import br.com.lirasistema.promocao.demo.modelo.Cliente;
import org.springframework.data.domain.Page;

/**
 *
 * @author paulo
 */
public class ClienteDto {

    //private Integer id;
    private Integer idDaEmpresa;
    private String nome_RazaoSocial;
    private String fantasia;
    private String telefone;
    private String celular;
    private String rg_InscricaoEstadual;
    private String cpf_Cnpj;
    private String cidade;
    private String tipo;

    public ClienteDto(Cliente cliente) {
        //this.id = cliente.getId();
        this.idDaEmpresa = cliente.getIdDaEmpresa();
        this.nome_RazaoSocial = cliente.getNome_RazaoSocial();
        this.fantasia = cliente.getFantasia();
        this.telefone = cliente.getTelefone();
        this.celular = cliente.getCelular();
        this.rg_InscricaoEstadual = cliente.getRg_InscricaoEstadual();
        this.cpf_Cnpj = cliente.getCpf_Cnpj();
        this.cidade = cliente.getEndereco().getCidadeObjeto().toString();
        this.tipo = cliente.getTipoWeb();
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

    public String getRg_InscricaoEstadual() {
        return rg_InscricaoEstadual;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public String getCidade() {
        return cidade;
    }
    
    public static Page<ClienteDto> converter(Page<Cliente> itens) {
        return itens.map(ClienteDto::new);
    }

    public String getTipo() {
        return tipo;
    }
    
    

}
