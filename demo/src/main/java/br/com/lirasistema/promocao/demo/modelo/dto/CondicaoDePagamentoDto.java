package br.com.lirasistema.promocao.demo.modelo.dto;


import br.com.lirasistema.promocao.demo.modelo.CondicaoDePagamento;
import org.springframework.data.domain.Page;


public class CondicaoDePagamentoDto {
    private Integer id;
    private Integer idDaEmpresa;
    private String descricao;
    private Integer numeroParcelas;
    private Integer intervaloDias;
    private boolean vaiParaCaixa;
    private Double percentualJuros;
    private String tipoPagamento;
    private String tipoPagamentoEntrada;    
    private Boolean temEntrada;
    
    public CondicaoDePagamentoDto(CondicaoDePagamento c){
        this.id = c.getId();
        this.idDaEmpresa = c.getIdDaEmpresa();
        this.descricao = c.getDescricao();
        this.numeroParcelas = c.getNumeroParcelas();
        this.intervaloDias = c.getIntervaloDias();
        this.vaiParaCaixa = c.isVaiParaCaixa();
        this.percentualJuros = c.getPercentualJuros();
        this.tipoPagamento = c.getTipoPagamento().toString();
        this.tipoPagamentoEntrada = c.getTipoPagamentoEntrada().toString();
        this.temEntrada = c.isTemEntrada();        
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

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public Integer getIntervaloDias() {
        return intervaloDias;
    }

    public boolean isVaiParaCaixa() {
        return vaiParaCaixa;
    }

    public Double getPercentualJuros() {
        return percentualJuros;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public String getTipoPagamentoEntrada() {
        return tipoPagamentoEntrada;
    }

    public Boolean getTemEntrada() {
        return temEntrada;
    }
    
    public static Page<CondicaoDePagamentoDto> converter(Page<CondicaoDePagamento> condicoes) {
        return condicoes.map(CondicaoDePagamentoDto::new);
    }

    public String getTemEntradaString() {
        if(temEntrada){
            return "SIM";
        }
        return "NÃO";
    }

    public String getCondicaoString() {
        if(vaiParaCaixa){
            return "A VISTA";
        }
        return "A PRAZO";
    }
    
    
    
}
