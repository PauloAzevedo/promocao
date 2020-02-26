package br.com.lirasistema.promocao.demo.modelo.dto;


import br.com.lirasistema.promocao.demo.modelo.OperacaoNotaFiscal;
import org.springframework.data.domain.Page;


public class OperacaoNotaFiscalDto {
    
    private Integer id;
    private Integer idDaEmpresa;
    private String descricao;
    private String descricaoAuxiliar;
    private Integer tipo;
    private String tipoOperacao;
    private String tipoString; //entrada / saida
    
    public OperacaoNotaFiscalDto(OperacaoNotaFiscal operacao){
        this.id = operacao.getId();
        this.idDaEmpresa = operacao.getIdDaEmpresa();
        this.descricao = operacao.getDescricao();
        this.descricaoAuxiliar = operacao.getDescricaoAuxiliar();
        this.tipo = operacao.getTipo();
        this.tipoOperacao = operacao.getTipoOperacao();
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDescricaoAuxiliar() {
        return descricaoAuxiliar;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }
    
    public static Page<OperacaoNotaFiscalDto> converter(Page<OperacaoNotaFiscal> ops) {
        return ops.map(OperacaoNotaFiscalDto::new);
    }

    public String getTipoString() {
        if(tipo==0){
            return "ENTRADA";
        }
        return "SAIDA";
    }
    
    
    
}
