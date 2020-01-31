package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Empresa;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.EmpresaRepository;
import br.com.lirasistema.promocao.demo.repository.UsuarioRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UsuarioApiForm {

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 100)
    private String login;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;

    @NotNull
    @NotEmpty
    @Length(min = 4)
    private String senha;

    private String hashTexto;

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

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public UsuarioApi converter(EmpresaRepository empresaRepository) {
        Optional<Empresa> emp = empresaRepository.findByHashTexto(this.hashTexto);
        if (emp.isPresent()) {
            return new UsuarioApi(login, nome, senha, emp.get());
        } else {
            return new UsuarioApi(login, nome, senha);
        }
    }

    public UsuarioApi atualizar(Long id, UsuarioRepository usuarioRepository) {
        UsuarioApi usuarioE = usuarioRepository.getOne(id);
        usuarioE.setLogin(this.login);
        usuarioE.setNome(this.nome);
        return usuarioE;
    }
}
