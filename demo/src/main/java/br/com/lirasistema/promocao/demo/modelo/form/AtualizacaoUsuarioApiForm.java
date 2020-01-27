/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.UsuarioRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author paulo
 */
public class AtualizacaoUsuarioApiForm {
     @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String login;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String nome;
    

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

     
    
    public UsuarioApi atualizar(Long id, UsuarioRepository usuarioRepository) {
        UsuarioApi usuarioE = usuarioRepository.getOne(id);
        usuarioE.setLogin(this.login);
        usuarioE.setNome(this.nome);
        return usuarioE;
    }
}
