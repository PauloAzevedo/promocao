package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.CentroCusto;
import br.com.lirasistema.promocao.demo.modelo.OperacaoNotaFiscal;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.CentroCustoRepository;
import br.com.lirasistema.promocao.demo.repository.OperacaoNotaFiscalRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class OperacaoNotaFiscalForm {

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String descricao;
    private String descricaoAuxiliar;
    @NotNull
    private Integer tipoEntradaSaida;
    @NotNull
    private Boolean movimentaEstoque;
    @NotNull
    private Boolean movimentaFinanceiro;

    @NotNull
    private Integer tipoOperacao;

    @NotNull
    private Integer centroCusto;

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

    public Integer getTipoEntradaSaida() {
        return tipoEntradaSaida;
    }

    public void setTipoEntradaSaida(Integer tipoEntradaSaida) {
        this.tipoEntradaSaida = tipoEntradaSaida;
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

    public Integer getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Integer tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Integer getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(Integer centroCusto) {
        this.centroCusto = centroCusto;
    }

    public OperacaoNotaFiscal converter(OperacaoNotaFiscalRepository operacaoNotaFiscalRepository, UsuarioApi usuario, CentroCustoRepository centroCustoRepository) {
        Optional<CentroCusto> centroCustoObj = centroCustoRepository.procurarPorIdDaEmpresaEEmpresa(centroCusto, usuario.getEmpresa().getId());
        Integer totalOperacoes = 0;
        try {
            totalOperacoes = operacaoNotaFiscalRepository.maiorCadastro(usuario.getEmpresa().getId());
            totalOperacoes += 1;
            //System.out.println(totalOperacoes);
        } catch (Exception ex){
            totalOperacoes = 1;
        }   
        if (centroCustoObj.isPresent()) {
            return new OperacaoNotaFiscal(descricao, descricaoAuxiliar, tipoEntradaSaida, movimentaEstoque, movimentaFinanceiro, tipoOperacao.toString(), totalOperacoes, centroCustoObj.get(), usuario.getEmpresa());
        } else {
            return new OperacaoNotaFiscal(descricao, descricaoAuxiliar, tipoEntradaSaida, movimentaEstoque, movimentaFinanceiro, tipoOperacao.toString(), totalOperacoes, null, usuario.getEmpresa());
        }
    }

    public OperacaoNotaFiscal atualizar(Integer id, OperacaoNotaFiscalRepository operacaoNotaFiscalRepository, UsuarioApi usuario, CentroCustoRepository centroCustoRepository) {
        Optional<CentroCusto> centroCustoObj = centroCustoRepository.procurarPorIdDaEmpresaEEmpresa(centroCusto, usuario.getEmpresa().getId());
        Optional<OperacaoNotaFiscal> operacaoOpt = operacaoNotaFiscalRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
        if(operacaoOpt.isPresent()){
            OperacaoNotaFiscal operacaoObj = operacaoOpt.get();
            operacaoObj.setDescricao(descricao);
            operacaoObj.setDescricaoAuxiliar(descricaoAuxiliar);
            operacaoObj.setTipo(tipoEntradaSaida);
            operacaoObj.setTipoOperacao(tipoOperacao.toString());
            operacaoObj.setMovimentaEstoque(movimentaEstoque);
            operacaoObj.setMovimentaFinanceiro(movimentaFinanceiro);            
            if(centroCustoObj.isPresent()){
                operacaoObj.setCentroCusto(centroCustoObj.get());
            }
            return operacaoObj;
        }
        return null;
    }

}
