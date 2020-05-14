package br.com.lirasistema.promocao.demo.repository;


import br.com.lirasistema.promocao.demo.modelo.Arquivo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author paulo
 */
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    
    public Page<Arquivo> findByEmpresaId(Integer empresa, Pageable paginacao);
    
    public Page<Arquivo> findByItemId(Long item, Pageable paginacao);
    
    @Query("SELECT t FROM Arquivo t WHERE t.tipo  =  :tipo  AND empresa_id = :empresa")
    public Page<Arquivo> procurarPorTipoEEmpresa(@Param("tipo") Integer tipo,@Param("empresa") Integer empresa, Pageable paginacao);

}
