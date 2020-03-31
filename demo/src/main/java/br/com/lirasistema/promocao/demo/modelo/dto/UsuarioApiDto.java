package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.IDDatatypeValidator;
import org.springframework.data.domain.Page;


public class UsuarioApiDto {
    private Long id;    
    private String login;
    private String nome;
    private String empresa;
    private Integer idEmpresa;
    private String cliente;
    private Integer idCliente;
    

    public UsuarioApiDto(UsuarioApi user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.nome = user.getNome();
        if(user.getEmpresa()!=null){
            this.empresa = user.getEmpresa().toString();
            this.idEmpresa = user.getEmpresa().getId();
        }
        if(user.getCliente()!=null){
            this.cliente = user.getCliente().toString();
            this.idCliente = user.getCliente().getId();
        }
    } 
    
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }
    
    public static Page<UsuarioApiDto> converter(Page<UsuarioApi> usuarios){
        return usuarios.map(UsuarioApiDto::new);
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getCliente() {
        return cliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }
    
    
    

    
    
}
