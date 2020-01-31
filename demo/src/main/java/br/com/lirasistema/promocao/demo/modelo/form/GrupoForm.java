package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.GrupoRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class GrupoForm {
        
    @NotNull @NotEmpty @Length(min = 3)
    private String descricao;
    
    @NotNull
    private Integer situacao;
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
        
    public Grupo converter(UsuarioApi usuario){
        return new Grupo(this.descricao, usuario.getEmpresa());
    }
    
    public Grupo atualizar(Integer id, GrupoRepository grupoRepository){
        Grupo grupoEditado = grupoRepository.getOne(id);
        grupoEditado.setDescricao(this.descricao);
        return grupoEditado;
    }

    public Grupo alterarSituacao(Integer id, GrupoRepository grupoRepository) {
        Grupo grupoEditado = grupoRepository.getOne(id);
        grupoEditado.setSituacao(this.situacao);
        return grupoEditado;
    }
}
