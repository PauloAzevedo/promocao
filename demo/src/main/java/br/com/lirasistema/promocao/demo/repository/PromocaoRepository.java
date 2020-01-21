package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Promocao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
    
    public Page<Promocao> findByTituloContaining(@Param("titulo") String titulo, Pageable paginacao);
    
    public Page<Promocao> findByValor(@Param("valor") Double valor, Pageable paginacao);
    
    public Page<Promocao> findByAutorLogin(@Param("login") String login, Pageable paginacao);
}
