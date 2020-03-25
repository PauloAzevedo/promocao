package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.CondicaoDePagamento;
import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.CondicaoDePagamentoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import br.com.lirasistema.promocao.demo.repository.PedidoAppRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public class AtualizarPedidoAppForm {

    @NotNull
    private Double totalValorAcrescimos;

    @NotNull
    private Double totalValorDescontos;
    
    @NotNull
    private Double totalValorProdutos;

    @NotNull
    private Integer situacao;
    
    private Integer condicaoDePagamento;

    public Double getTotalValorAcrescimos() {
        return totalValorAcrescimos;
    }

    public void setTotalValorAcrescimos(Double totalValorAcrescimos) {
        this.totalValorAcrescimos = totalValorAcrescimos;
    }

    public Double getTotalValorDescontos() {
        return totalValorDescontos;
    }

    public void setTotalValorDescontos(Double totalValorDescontos) {
        this.totalValorDescontos = totalValorDescontos;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getCondicaoDePagamento() {
        return condicaoDePagamento;
    }

    public void setCondicaoDePagamento(Integer condicaoDePagamento) {
        this.condicaoDePagamento = condicaoDePagamento;
    }

    public Double getTotalValorProdutos() {
        return totalValorProdutos;
    }

    public void setTotalValorProdutos(Double totalValorProdutos) {
        this.totalValorProdutos = totalValorProdutos;
    }
    
       

    public PedidoApp atualizar(Long id, UsuarioApi usuario, ItemRepository itemRepository, PedidoAppRepository pedidoAppRepository, CondicaoDePagamentoRepository condicaoDePagamentoRepository) {
        PedidoApp pedidoE = pedidoAppRepository.getOne(id);
        pedidoE.setTotalValorAcrescimos(totalValorAcrescimos);
        pedidoE.setTotalValorDescontos(totalValorDescontos);
        pedidoE.setTotalValorProdutos(totalValorProdutos);
        Double totalPedido = pedidoE.getTotalValorProdutos() + pedidoE.getTotalValorAcrescimos() - pedidoE.getTotalValorDescontos();
        pedidoE.setTotalValorPedido(totalPedido);
        if(situacao==1){
            pedidoE.setSituacao(situacao);
            pedidoE.setDataAceite(LocalDateTime.now());
        } else if (situacao==2) {
            pedidoE.setSituacao(situacao);
            pedidoE.setDataEntrega(LocalDateTime.now());
        } else {
            pedidoE.setSituacao(situacao);
        }
        if(condicaoDePagamento!=null){
            Optional<CondicaoDePagamento> cond = condicaoDePagamentoRepository.procurarPorIdDaEmpresaEEmpresa(condicaoDePagamento, pedidoE.getEmpresa().getId());
            if(cond.isPresent()){
                pedidoE.setCondicaoDePagamento(cond.get());
            }
        }
        return pedidoE;
    }

}
