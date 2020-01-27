package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Promocao;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.PromocaoRepository;
import br.com.lirasistema.promocao.demo.repository.UsuarioRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class UsuarioApiForm {
    @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String login;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String nome;
    
    @NotNull @NotEmpty @Length(min = 4)
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public UsuarioApi converter() {      
        return new UsuarioApi(login, nome, senha);
    }
    
    public UsuarioApi atualizar(Long id, UsuarioRepository usuarioRepository) {
        UsuarioApi usuarioE = usuarioRepository.getOne(id);
        usuarioE.setLogin(this.login);
        usuarioE.setNome(this.nome);
        return usuarioE;
    }
}
