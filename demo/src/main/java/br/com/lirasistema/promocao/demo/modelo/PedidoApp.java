
package br.com.lirasistema.promocao.demo.modelo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer numero;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Empresa empresa;  
    
    private Integer situacao; // -1 = Cliente gerando; 0 = Em Aberto; 1 - Aceite da Empresa; 2 - Finalizado/Entrega; 3 - Cancelado
    
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    private LocalDateTime dataAceite;
    
    private LocalDateTime dataEntrega;
    
    private Double totalValorProdutos;
    
    private Double totalValorAcrescimos;
    
    private Double totalValorDescontos;
    
    private Double totalValorPedido;
    
    @ManyToOne
    private CondicaoDePagamento condicaoDePagamento;
    
    public PedidoApp(){
        
    }

    public PedidoApp(Cliente cliente, Empresa empresa, Integer situacao, Double totalValorProdutos, Double totalValorAcrescimos, Double totalValorDescontos, Double totalValorPedido, CondicaoDePagamento condicaoDePagamento) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.situacao = situacao;
        this.totalValorProdutos = totalValorProdutos;
        this.totalValorAcrescimos = totalValorAcrescimos;
        this.totalValorDescontos = totalValorDescontos;
        this.totalValorPedido = totalValorPedido;
        this.condicaoDePagamento = condicaoDePagamento;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
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

    public CondicaoDePagamento getCondicaoDePagamento() {
        return condicaoDePagamento;
    }

    public void setCondicaoDePagamento(CondicaoDePagamento condicaoDePagamento) {
        this.condicaoDePagamento = condicaoDePagamento;
    }
    
    
    
}
