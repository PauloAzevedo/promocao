package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import br.com.lirasistema.promocao.demo.repository.PedidoAppRepository;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public class PedidoAppForm {

    @NotNull
    private Double totalValorAcrescimos;

    @NotNull
    private Double totalValorDescontos;
    
    @NotNull
    private Long idItemGlobal;

    
    public void setTotalValorAcrescimos(Double totalValorAcrescimos) {
        this.totalValorAcrescimos = totalValorAcrescimos;
    }

    public void setTotalValorDescontos(Double totalValorDescontos) {
        this.totalValorDescontos = totalValorDescontos;
    }

    public void setIdItemGlobal(Long idItemGlobal) {
        this.idItemGlobal = idItemGlobal;
    }
    
    
    public PedidoApp converter(UsuarioApi usuario, ItemRepository itemRepository, PedidoAppRepository pedidoAppRepository) {        
        Optional<Item> itemObj = itemRepository.findById(idItemGlobal);
        if (itemObj.isPresent() && usuario.getCliente()!=null) {
            Integer totalPedidosDoCliente = 0;
            try {
                totalPedidosDoCliente = pedidoAppRepository.maiorCadastro(usuario.getCliente().getId());
                totalPedidosDoCliente += 1;
            } catch (Exception ex) {
                totalPedidosDoCliente = 1;
            }
            return new PedidoApp(usuario.getCliente(),itemObj.get().getEmpresa(),totalPedidosDoCliente,-1, itemObj.get().getValor(), 0.0, 0.0,itemObj.get().getValor());
        }
        return null;
    }

}
