package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.OperacaoNotaFiscal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperacaoNotaFiscalRepository extends JpaRepository<OperacaoNotaFiscal, Integer> {
       
    public Page<OperacaoNotaFiscal> findByEmpresaId(Integer id, Pageable paginacao);
    
    @Query("SELECT t FROM OperacaoNotaFiscal t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<OperacaoNotaFiscal> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);

    @Query("SELECT t FROM OperacaoNotaFiscal t WHERE t.descricao  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<OperacaoNotaFiscal> procurarClientePorDescricaoEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    
    @Query("SELECT max(idDaEmpresa) FROM OperacaoNotaFiscal t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
}
