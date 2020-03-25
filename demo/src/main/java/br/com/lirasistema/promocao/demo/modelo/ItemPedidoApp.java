
package br.com.lirasistema.promocao.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedidoApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double quantidade;
    
    private Double valorUnitario;
    
    private Double valorTotal;
    
    @ManyToOne
    private Item item;
    
    @ManyToOne
    private PedidoApp pedidoApp;

    public ItemPedidoApp() {
    }

    public ItemPedidoApp(Double quantidade, Double valorUnitario, Double valorTotal, Item item, PedidoApp pedido) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.item = item;
        this.pedidoApp = pedido;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PedidoApp getPedidoApp() {
        return pedidoApp;
    }

    public void setPedidoApp(PedidoApp pedidoApp) {
        this.pedidoApp = pedidoApp;
    }

   
    
    
    
}
