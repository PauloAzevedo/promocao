package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.CentroCusto;
import org.springframework.data.domain.Page;


public class CentroCustoDto {
    private Integer idDaEmpresa;
    private String descricao;
    private String empresa;
    
    public CentroCustoDto(CentroCusto centro){
        this.idDaEmpresa = centro.getIdDaEmpresa();
        this.descricao = centro.getDescricao();
        this.empresa = centro.getEmpresa().toString();
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmpresa() {
        return empresa;
    }
    
    public static Page<CentroCustoDto> converter(Page<CentroCusto> centros) {
        return centros.map(CentroCustoDto::new);
    }
}
