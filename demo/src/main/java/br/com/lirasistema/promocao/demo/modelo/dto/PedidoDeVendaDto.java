package br.com.lirasistema.promocao.demo.modelo.dto;


import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import br.com.lirasistema.promocao.demo.utilidades.Util;
import org.springframework.data.domain.Page;


public class PedidoDeVendaDto {
    private Long id;
    private Integer numeroNota;
    private String hashTexto;
    private String remetenteDestinatario;
    private String tipoPagamento;
    private String dataEmissao;
    private Double valorTotalNotaFiscal;
    private String situacaoWeb;
    private String situacaoNota;
    
    public PedidoDeVendaDto(PedidoDeVenda pedido){
        this.id = pedido.getId();
        this.numeroNota = pedido.getNumeroNota();
        this.hashTexto = pedido.getHashTexto();
        this.remetenteDestinatario = pedido.getRemetenteDestinatario().toString();
        this.tipoPagamento = pedido.getCondicao().getTipoPagamento().toString() ;
        this.dataEmissao = Util.calendarToString(pedido.getDataEmissao());
        this.valorTotalNotaFiscal = pedido.getValorTotalNotaFiscal();
        this.situacaoWeb = pedido.getSituacaoWeb();
        this.situacaoNota = pedido.getSituacaoNota();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public String getRemetenteDestinatario() {
        return remetenteDestinatario;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

      
    public String getDataEmissao() {
        return dataEmissao;
    }

    public Double getValorTotalNotaFiscal() {
        return valorTotalNotaFiscal;
    }

    public String getSituacaoWeb() {
        return situacaoWeb;
    }

    public String getSituacaoNota() {
        return situacaoNota;
    }

    public static Page<PedidoDeVendaDto> converter(Page<PedidoDeVenda> pedidos) {
        return pedidos.map(PedidoDeVendaDto::new);
    }
      
    
}
