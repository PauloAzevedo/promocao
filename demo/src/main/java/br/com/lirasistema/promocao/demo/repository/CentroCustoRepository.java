
package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.CentroCusto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {
     public Page<CentroCusto> findByEmpresaId(Integer id, Pageable paginacao);
    
    @Query("SELECT t FROM CentroCusto t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<CentroCusto> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);

    @Query("SELECT t FROM CentroCusto t WHERE t.descricao  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<CentroCusto> procurarCentroCustoPorDescricaoEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT max(idDaEmpresa) FROM CentroCusto t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
}
