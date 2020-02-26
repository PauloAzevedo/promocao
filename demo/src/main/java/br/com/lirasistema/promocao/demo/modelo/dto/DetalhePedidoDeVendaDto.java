
package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import br.com.lirasistema.promocao.demo.utilidades.Util;



public class DetalhePedidoDeVendaDto {
    private Long id;    
    private Integer numeroNota;    
    private String hashTexto;
    private String remetenteDestinatario;    
    private String situacaoNota;    
    private String dataEmissao;    
    private String dataAutorizacao;
    private String dataSaidaNota;
    private Double valorTotalNotaFiscal;
    private String informacoesInteressesFisco;
    private Double valorAcrescimos;
    private Double valorDesconto;
    private Double valorEntrada;
    private String vendedor;
    private String condicao;
    private String cpfCliente;
    
    public DetalhePedidoDeVendaDto(PedidoDeVenda pedido){
        this.id = pedido.getId();
        this.numeroNota = pedido.getNumeroNota();
        this.remetenteDestinatario = pedido.getRemetenteDestinatario().toString();
        this.situacaoNota = pedido.getSituacaoNota();
        this.dataEmissao = Util.calendarToString(pedido.getDataEmissao());
        this.valorTotalNotaFiscal = pedido.getValorTotalNotaFiscal();
        this.dataAutorizacao = Util.calendarToString(pedido.getDataAutorizacao());
        this.dataSaidaNota = Util.calendarToString(pedido.getDataSaidaNota());
        this.informacoesInteressesFisco = pedido.getInformacoesInteressesFisco();
        this.valorAcrescimos = pedido.getValorAcrescimos();
        this.valorDesconto = pedido.getValorDesconto();
        this.valorEntrada = pedido.getValorEntrada();
        this.hashTexto = pedido.getHashTexto();
        if (pedido.getVendedor() != null) {
            this.vendedor = pedido.getVendedor().toString();
        }
                
        this.condicao = pedido.getCondicao().toString();
        this.cpfCliente = pedido.getCpfCliente();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public String getRemetenteDestinatario() {
        return remetenteDestinatario;
    }

    public String getSituacaoNota() {
        return situacaoNota;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public String getDataAutorizacao() {
        return dataAutorizacao;
    }

    public String getDataSaidaNota() {
        return dataSaidaNota;
    }

    public Double getValorTotalNotaFiscal() {
        return valorTotalNotaFiscal;
    }

    public String getInformacoesInteressesFisco() {
        return informacoesInteressesFisco;
    }

    public Double getValorAcrescimos() {
        return valorAcrescimos;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public Double getValorEntrada() {
        return valorEntrada;
    }

    public String getVendedor() {
        return vendedor;
    }
    
    public String getCondicao() {
        return condicao;
    }   

    public String getHashTexto() {
        return hashTexto;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }
    
}
