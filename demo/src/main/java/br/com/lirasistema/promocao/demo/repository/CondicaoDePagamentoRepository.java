package br.com.lirasistema.promocao.demo.repository;


import br.com.lirasistema.promocao.demo.modelo.CondicaoDePagamento;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CondicaoDePagamentoRepository extends JpaRepository<CondicaoDePagamento, Integer> {

    public Page<CondicaoDePagamento> findByEmpresaId(Integer id, Pageable paginacao);

    @Query("SELECT t FROM CondicaoDePagamento t WHERE t.descricao  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<CondicaoDePagamento> procurarClientePorDescricaoEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM CondicaoDePagamento t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<CondicaoDePagamento> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);
    
    
    @Query("SELECT max(idDaEmpresa) FROM CondicaoDePagamento t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
}
