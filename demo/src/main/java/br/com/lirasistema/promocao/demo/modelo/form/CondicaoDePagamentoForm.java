package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.controller.TipoPagamentoFiscal;
import br.com.lirasistema.promocao.demo.modelo.CondicaoDePagamento;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.CondicaoDePagamentoRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class CondicaoDePagamentoForm {
    
    @NotNull @NotEmpty @Length(min = 3)
    private String descricao;
    
    @NotNull
    private Integer numeroParcelas;
    
    @NotNull
    private Integer intervaloDias;
    
    @NotNull
    private boolean vaiParaCaixa;
    
    @NotNull
    private Double percentualJuros;
    
    @NotNull @NotEmpty @Length(min = 2)
    private String tipoPagamento;
    
    @NotNull
    private Boolean temEntrada;
    
    @NotNull @NotEmpty @Length(min = 2)
    private String tipoPagamentoEntrada;
            
    @NotNull
    private Integer diaFixo;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getIntervaloDias() {
        return intervaloDias;
    }

    public void setIntervaloDias(Integer intervaloDias) {
        this.intervaloDias = intervaloDias;
    }

    public boolean isVaiParaCaixa() {
        return vaiParaCaixa;
    }

    public void setVaiParaCaixa(boolean vaiParaCaixa) {
        this.vaiParaCaixa = vaiParaCaixa;
    }

    public Double getPercentualJuros() {
        return percentualJuros;
    }

    public void setPercentualJuros(Double percentualJuros) {
        this.percentualJuros = percentualJuros;
    }

    

    public Boolean getTemEntrada() {
        return temEntrada;
    }

    public void setTemEntrada(Boolean temEntrada) {
        this.temEntrada = temEntrada;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getTipoPagamentoEntrada() {
        return tipoPagamentoEntrada;
    }

    public void setTipoPagamentoEntrada(String tipoPagamentoEntrada) {
        this.tipoPagamentoEntrada = tipoPagamentoEntrada;
    }

   

    

    public Integer getDiaFixo() {
        return diaFixo;
    }

    public void setDiaFixo(Integer diaFixo) {
        this.diaFixo = diaFixo;
    }
    
    
    public CondicaoDePagamento converter(UsuarioApi usuario, CondicaoDePagamentoRepository condicaoDePagamentoRepository) { 
        
        Integer totalCondicoesDoCliente = 0;
        try {
            totalCondicoesDoCliente = condicaoDePagamentoRepository.maiorCadastro(usuario.getEmpresa().getId());
            totalCondicoesDoCliente += 1;
            //System.out.println(totalCondicoesDoCliente);
        } catch (Exception ex){
            totalCondicoesDoCliente = 1;
        }        
        return new CondicaoDePagamento(totalCondicoesDoCliente, descricao, numeroParcelas, intervaloDias, true, percentualJuros, TipoPagamentoFiscal.valueOfCodigo(tipoPagamento), TipoPagamentoFiscal.valueOfCodigo(tipoPagamentoEntrada), temEntrada, usuario.getEmpresa(), diaFixo);
    }
    
     public CondicaoDePagamento atualizar(Integer id, CondicaoDePagamentoRepository condicaoDePagamentoRepository) {
        CondicaoDePagamento condicaoDePagamento = condicaoDePagamentoRepository.getOne(id);
        condicaoDePagamento.setDescricao(descricao);
        condicaoDePagamento.setDiaFixo(diaFixo);
        condicaoDePagamento.setIntervaloDias(intervaloDias);
        condicaoDePagamento.setNumeroParcelas(numeroParcelas);
        condicaoDePagamento.setPercentualJuros(percentualJuros);
        condicaoDePagamento.setTemEntrada(temEntrada);
        condicaoDePagamento.setTipoPagamento(TipoPagamentoFiscal.valueOfCodigo(tipoPagamento));
        condicaoDePagamento.setTipoPagamentoEntrada(TipoPagamentoFiscal.valueOfCodigo(tipoPagamentoEntrada));
        condicaoDePagamento.setVaiParaCaixa(vaiParaCaixa);        
        return condicaoDePagamento;
    }
    
}
