package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.ItemPedidoApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemPedidoAppRepository extends JpaRepository<ItemPedidoApp, Long> {
    
    public Page<ItemPedidoApp> findByPedidoAppId(Long pedido, Pageable paginacao);
}
