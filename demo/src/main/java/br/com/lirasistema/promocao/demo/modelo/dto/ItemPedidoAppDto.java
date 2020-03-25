package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.ItemPedidoApp;
import org.springframework.data.domain.Page;


public class ItemPedidoAppDto {
    private Long id;
    private Double quantidade;
    
    private Double valorUnitario;
    
    private Double valorTotal;
    
    private String item;
    
    private Long pedido;

    public ItemPedidoAppDto(ItemPedidoApp itemApp) {
        this.id = itemApp.getId();
        this.quantidade = itemApp.getQuantidade();
        this.valorUnitario = itemApp.getValorUnitario();
        this.valorTotal = itemApp.getValorTotal();
        this.item = itemApp.getItem().toString();
        this.pedido = itemApp.getPedidoApp().getId();
    }   
    

    public Long getId() {
        return id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public String getItem() {
        return item;
    }
    
    public Long getPedido() {
        return pedido;
    }
    
    public static Page<ItemPedidoAppDto> converter(Page<ItemPedidoApp> itemApp){
        return itemApp.map(ItemPedidoAppDto::new);
    }
    
    
}
