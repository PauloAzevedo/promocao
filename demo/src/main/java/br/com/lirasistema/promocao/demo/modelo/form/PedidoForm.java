package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;

public class PedidoForm {
    
    public PedidoDeVenda converter(UsuarioApi usuario){
        return new PedidoDeVenda();
    }
}
