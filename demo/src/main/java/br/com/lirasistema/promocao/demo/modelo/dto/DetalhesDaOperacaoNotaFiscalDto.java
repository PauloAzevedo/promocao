package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.OperacaoNotaFiscal;
import br.com.lirasistema.promocao.demo.utilidades.Util;


public class DetalhesDaOperacaoNotaFiscalDto {

    private Integer id;
    private String descricao;
    private String descricaoAuxiliar;
    private Integer tipo;  // 0 - Entrada, 1 - Saida, 2 - outras operações
    private Boolean movimentaEstoque;
    private Boolean movimentaFinanceiro;
    private String dataCadastro;
    private String tipoOperacao;
    private Integer idDaEmpresa;
    private String centroCusto;
    private String empresa;

    public DetalhesDaOperacaoNotaFiscalDto(OperacaoNotaFiscal operacao) {
        this.id = operacao.getId();
        this.idDaEmpresa = operacao.getIdDaEmpresa();
        this.descricao = operacao.getDescricao();
        this.descricaoAuxiliar = operacao.getDescricaoAuxiliar();
        this.tipo = operacao.getTipo();
        this.tipoOperacao = operacao.getTipoOperacao();
        this.movimentaEstoque = operacao.getMovimentaEstoque();
        this.movimentaFinanceiro = operacao.getMovimentaFinanceiro();
        this.dataCadastro = Util.calendarToStringWeb(operacao.getDataCadastro());
        if (operacao.getCentroCusto() != null) {
            this.centroCusto = operacao.getCentroCusto().toString();
        }
        this.empresa = operacao.getEmpresa().toString();
    }

    public Integer getId() {
        return id;
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

    public Boolean getMovimentaEstoque() {
        return movimentaEstoque;
    }

    public Boolean getMovimentaFinanceiro() {
        return movimentaFinanceiro;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public String getEmpresa() {
        return empresa;
    }
    
    
}
