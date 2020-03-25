
package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.ItemPedidoApp;
import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.ItemPedidoAppRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import br.com.lirasistema.promocao.demo.repository.PedidoAppRepository;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class ItemPedidoAppForm {
    
    @NotNull
    @Range(min = 0)
    private Double quantidade;
    
    @NotNull
    @Range(min = 0)
    private Double valorUnitario;
    
    @NotNull
    @Range(min = 1)
    private Long idItem;
    
    @NotNull
    @Range(min = 1)
    private Long idPedidoApp;

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

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdPedidoApp() {
        return idPedidoApp;
    }

    public void setIdPedidoApp(Long idPedidoApp) {
        this.idPedidoApp = idPedidoApp;
    }
    
    
    
    

    public ItemPedidoApp converter(UsuarioApi usuario, ItemRepository itemRepository, PedidoAppRepository pedidoAppRepository) {
        Optional<Item> itemOpt = itemRepository.findById(idItem);
        if(itemOpt.isPresent()){
            Optional<PedidoApp> pedidoOpt = pedidoAppRepository.findById(idPedidoApp);
            if(pedidoOpt.isPresent()){
                return new ItemPedidoApp(quantidade, valorUnitario, quantidade*valorUnitario, itemOpt.get(), pedidoOpt.get());
            }
        }
        return null;
    }

    public ItemPedidoApp atualizar(Long id, ItemPedidoAppRepository itemPedidoAppRepository) {
        Optional<ItemPedidoApp> opt = itemPedidoAppRepository.findById(id);
        if(opt.isPresent()){
            ItemPedidoApp itemAppE = opt.get();
            itemAppE.setQuantidade(quantidade);
            itemAppE.setValorUnitario(valorUnitario);
            itemAppE.setValorTotal(quantidade*valorUnitario);
            return itemAppE;
        }
        return null;
    }
    
    
}
