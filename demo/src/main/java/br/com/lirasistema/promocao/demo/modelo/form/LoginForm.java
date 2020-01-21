package br.com.lirasistema.promocao.demo.modelo.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class LoginForm {
    
    private String login;
    
    private String senha;
    
    private String cnpj;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    public String getSenha() {
        return senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
       
        
}
