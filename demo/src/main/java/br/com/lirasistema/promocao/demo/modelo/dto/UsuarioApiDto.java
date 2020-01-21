package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import org.springframework.data.domain.Page;


public class UsuarioApiDto {
    private Long id;    
    private String login;
    private String nome;

    public UsuarioApiDto(UsuarioApi user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.nome = user.getNome();
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
    
    public static Page<UsuarioApiDto> converter(Page<UsuarioApi> usuarios){
        return usuarios.map(UsuarioApiDto::new);
    }


    
    
}
