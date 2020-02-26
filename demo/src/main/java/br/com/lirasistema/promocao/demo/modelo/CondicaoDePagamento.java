package br.com.lirasistema.promocao.demo.modelo;

import br.com.lirasistema.promocao.demo.controller.TipoPagamentoFiscal;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Paulo
 */
@Entity
public class CondicaoDePagamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idDaEmpresa;
    private String descricao;
    private Integer numeroParcelas;
    private Integer intervaloDias;
    private boolean vaiParaCaixa;
    private Double percentualJuros;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataCadastro;
    
    private TipoPagamentoFiscal tipoPagamento;
    private TipoPagamentoFiscal tipoPagamentoEntrada;
    
    private Boolean temEntrada;
    
    @ManyToOne
    private Empresa empresa;
    
    private Integer diaFixo;

    public CondicaoDePagamento() {
    }

//    public CondicaoDePagamento(String descricao, Integer numeroParcelas, Integer intervaloDias, boolean vaiParaCaixa, Double percentualJuros, Calendar dataCadastro, String tipoPagamento) {
//        this.descricao = descricao;
//        this.numeroParcelas = numeroParcelas;
//        this.intervaloDias = intervaloDias;
//        this.vaiParaCaixa = vaiParaCaixa;
//        this.percentualJuros = percentualJuros;
//        this.dataCadastro = dataCadastro;
//        this.tipoPagamento = tipoPagamento;
//    }
//    
//    public CondicaoDePagamento(String descricao, Integer numeroParcelas, Integer intervaloDias, boolean vaiParaCaixa, Double percentualJuros, Calendar dataCadastro, String tipoPagamento, Boolean temEntrada) {
//        this.descricao = descricao;
//        this.numeroParcelas = numeroParcelas;
//        this.intervaloDias = intervaloDias;
//        this.vaiParaCaixa = vaiParaCaixa;
//        this.percentualJuros = percentualJuros;
//        this.dataCadastro = dataCadastro;
//        this.tipoPagamento = tipoPagamento;
//        this.temEntrada = temEntrada;
//    }

    public CondicaoDePagamento(Integer idDaEmpresa, String descricao, Integer numeroParcelas, Integer intervaloDias, boolean vaiParaCaixa, Double percentualJuros, TipoPagamentoFiscal tipoPagamento, TipoPagamentoFiscal tipoPagamentoEntrada, Boolean temEntrada, Empresa empresa, Integer diaFixo) {
        this.idDaEmpresa = idDaEmpresa;
        this.descricao = descricao;
        this.numeroParcelas = numeroParcelas;
        this.intervaloDias = intervaloDias;
        this.vaiParaCaixa = vaiParaCaixa;
        this.percentualJuros = percentualJuros;
        this.dataCadastro = Calendar.getInstance();
        this.tipoPagamento = tipoPagamento;
        this.tipoPagamentoEntrada = tipoPagamentoEntrada;
        this.temEntrada = temEntrada;
        this.empresa = empresa;
        this.diaFixo = diaFixo;
    }
    
    
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

//    public String getTipoPagamento() {
//        return tipoPagamento;
//    }
//    
//    public String getTipoPagamentoString() {
//        switch (tipoPagamento) {
//            case "01":
//                return "DINHEIRO";
//            case "02":
//                return "CHEQUE";
//            case "03":
//                return "CARTAO CREDITO";
//            case "04":
//                return "CARTAO DEBITO";
//            case "05":
//                return "CARTAO_LOJA";
//            case "10":
//                return "VALE ALIMENTACAO";
//            case "11":
//                return "VALE REFEICAO";
//            case "12":
//                return "VALE PRESENTE";
//            case "13":
//                return "VALE COMBUSTIVEL";
//            case "15":
//                return "BOLETO BANCARIO";
//            case "90":
//                return "SEM PAGAMENTO";
//            default:
//                return "OUTRO";
//        }
//    }

    

    public boolean isTemEntrada() {
        return temEntrada;
    }

    public void setTemEntrada(boolean temEntrada) {
        this.temEntrada = temEntrada;
    }

    public Boolean getTemEntrada() {
        return temEntrada;
    }

    public void setTemEntrada(Boolean temEntrada) {
        this.temEntrada = temEntrada;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }

    @Override
    public String toString() {
        return  idDaEmpresa + " - " + descricao;
    }

    public TipoPagamentoFiscal getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamentoFiscal tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public TipoPagamentoFiscal getTipoPagamentoEntrada() {
        return tipoPagamentoEntrada;
    }

    public void setTipoPagamentoEntrada(TipoPagamentoFiscal tipoPagamentoEntrada) {
        this.tipoPagamentoEntrada = tipoPagamentoEntrada;
    }

    

    public Integer getDiaFixo() {
        return diaFixo;
    }

    public void setDiaFixo(Integer diaFixo) {
        this.diaFixo = diaFixo;
    }
    
    
    
    
        
}
