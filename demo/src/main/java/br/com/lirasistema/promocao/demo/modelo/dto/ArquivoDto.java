package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Arquivo;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

public class ArquivoDto {

    private Long id;
    private LocalDateTime dataCriacao;
    private String empresa;
    private Integer idEmpresa;
    private String item;
    private Long idItem;
    private Integer tipo;
    private String caminho;

    public ArquivoDto(Arquivo arquivo) {
        this.id = arquivo.getId();
        this.dataCriacao = arquivo.getDataCriacao();
        if (arquivo.getEmpresa() != null) {
            this.empresa = arquivo.getEmpresa().toString();
            this.idEmpresa = arquivo.getEmpresa().getId();
        }
        if(arquivo.getItem() != null) {
            this.item = arquivo.getItem().toString();
            this.idItem = arquivo.getItem().getId();
        }
        this.tipo = arquivo.getTipo();
        this.caminho = arquivo.getCaminho();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getItem() {
        return item;
    }

    public Long getIdItem() {
        return idItem;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getCaminho() {
        return caminho;
    }
    
    
        
    public static Page<ArquivoDto> converter(Page<Arquivo> arquivos) {
        return arquivos.map(ArquivoDto::new);
    }
}
