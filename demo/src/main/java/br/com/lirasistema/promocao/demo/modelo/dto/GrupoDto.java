package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import org.springframework.data.domain.Page;

public class GrupoDto {
    private Integer id;
    private String descricao;
    private String empresa;
    private Integer situacao;

    public GrupoDto(Grupo grupo) {
        this.id = grupo.getId();
        this.descricao = grupo.getDescricao();
        this.empresa = grupo.getEmpresa().toString();
        this.situacao = grupo.getSituacao();
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Page<GrupoDto> converter(Page<Grupo> grupos){
        return grupos.map(GrupoDto::new);
    }

    public String getEmpresa() {
        return empresa;
    }

    public Integer getSituacao() {
        return situacao;
    }
          
    
}
