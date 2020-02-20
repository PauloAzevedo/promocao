
package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
    
    public Page<Item> findByDescricaoContaining(String descricao, Pageable paginacao);
    
    public Page<Item> findByEmpresaId(Integer empresa, Pageable paginacao);
    
   
    
}
