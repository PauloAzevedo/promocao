package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;




public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    
    public Page<Grupo> findByDescricaoContaining(String descricao,  Pageable paginacao);
    
    public Page<Grupo> findByEmpresaId(Integer id,  Pageable paginacao);
    
}
