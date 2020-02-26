
package br.com.lirasistema.promocao.demo.modelo;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class OperacaoNotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String descricaoAuxiliar;
    private Integer tipo;  // 0 - Entrada, 1 - Saida, 2 - outras operações
    private Boolean movimentaEstoque;
    private Boolean movimentaFinanceiro;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataCadastro;
    
    private String tipoOperacao; //Compra/Venda, Outras Saidas, Devolucao
    
    private Integer idDaEmpresa;
    
    @ManyToOne
    private CentroCusto centroCusto;
    
    
    @ManyToOne
    private Empresa empresa;

    public OperacaoNotaFiscal() {
    }

    public OperacaoNotaFiscal(String descricao, String descricaoAuxiliar, Integer tipo, Boolean movimentaEstoque, Boolean movimentaFinanceiro, String tipoOperacao, Integer idDaEmpresa, CentroCusto centroCusto, Empresa empresa) {
        this.descricao = descricao;
        this.descricaoAuxiliar = descricaoAuxiliar;
        this.tipo = tipo;
        this.dataCadastro = Calendar.getInstance();
        this.movimentaEstoque = movimentaEstoque;
        this.movimentaFinanceiro = movimentaFinanceiro;
        this.tipoOperacao = tipoOperacao;
        this.idDaEmpresa = idDaEmpresa;
        this.centroCusto = centroCusto;
        this.empresa = empresa;
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

    public String getDescricaoAuxiliar() {
        return descricaoAuxiliar;
    }

    public void setDescricaoAuxiliar(String descricaoAuxiliar) {
        this.descricaoAuxiliar = descricaoAuxiliar;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Boolean getMovimentaEstoque() {
        return movimentaEstoque;
    }

    public void setMovimentaEstoque(Boolean movimentaEstoque) {
        this.movimentaEstoque = movimentaEstoque;
    }

    public Boolean getMovimentaFinanceiro() {
        return movimentaFinanceiro;
    }

    public void setMovimentaFinanceiro(Boolean movimentaFinanceiro) {
        this.movimentaFinanceiro = movimentaFinanceiro;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return idDaEmpresa + " - " + descricao;
    }
    
    
    
}
