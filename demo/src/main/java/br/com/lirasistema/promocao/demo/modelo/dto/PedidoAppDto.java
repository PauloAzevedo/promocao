package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

public class PedidoAppDto {

    private Long id;
    private Integer numero;
    private String cliente;
    private String empresa;
    private Integer situacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAceite;
    private LocalDateTime dataEntrega;
    private Double totalValorProdutos;
    private Double totalValorAcrescimos;
    private Double totalValorDescontos;
    private Double totalValorPedido;
    private String condicaoDePagamento;

    public PedidoAppDto(PedidoApp pedido) {
        this.id = pedido.getId();
        this.numero = pedido.getNumero();
        this.cliente = pedido.getCliente().toString();
        this.empresa = pedido.getEmpresa().toString();
        this.situacao = pedido.getSituacao();
        this.dataCriacao = pedido.getDataCriacao();
        this.dataAceite = pedido.getDataAceite();
        this.dataEntrega = pedido.getDataEntrega();
        this.totalValorProdutos = pedido.getTotalValorProdutos();
        this.totalValorAcrescimos = pedido.getTotalValorAcrescimos();
        this.totalValorDescontos = pedido.getTotalValorDescontos();
        this.totalValorPedido = pedido.getTotalValorPedido();
        this.condicaoDePagamento = pedido.getCondicaoDePagamento().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAceite() {
        return dataAceite;
    }

    public void setDataAceite(LocalDateTime dataAceite) {
        this.dataAceite = dataAceite;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Double getTotalValorProdutos() {
        return totalValorProdutos;
    }

    public void setTotalValorProdutos(Double totalValorProdutos) {
        this.totalValorProdutos = totalValorProdutos;
    }

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

    public Double getTotalValorPedido() {
        return totalValorPedido;
    }

    public void setTotalValorPedido(Double totalValorPedido) {
        this.totalValorPedido = totalValorPedido;
    }

    public String getCondicaoDePagamento() {
        return condicaoDePagamento;
    }

    public void setCondicaoDePagamento(String condicaoDePagamento) {
        this.condicaoDePagamento = condicaoDePagamento;
    }

    public static Page<PedidoAppDto> converter(Page<PedidoApp> pedidos) {
        return pedidos.map(PedidoAppDto::new);
    }

}
