
package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Item;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ItemRepository extends JpaRepository<Item, Long> {
    
    public Page<Item> findByDescricaoContaining(String descricao, Pageable paginacao);
    
    public Page<Item> findByEmpresaId(Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Item t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<Item> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);

    //@Query("SELECT t FROM Item t WHERE t.grupo_id  =  :grupo_id  AND empresa_id = :empresa")
    //public Optional<Item> procurarPorGrupoEEmpresa(@Param("grupo_id") Integer grupo_id, @Param("empresa") Integer empresa);


    public Page<Item> findByGrupoId(Integer grupo, Pageable paginacao);
   
    
}
