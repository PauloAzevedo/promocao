package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Agenda;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AgendaRepository extends JpaRepository<Agenda, Integer>{
    
    public Page<Agenda> findByEmpresaId(Integer id, Pageable paginacao);
    
    @Query("SELECT t FROM Agenda t WHERE t.data  BETWEEN :dataInicial AND :dataFinal AND empresa_id = :empresa")
    public Page<Agenda> procurarPorPeriodo(@Param("dataInicial") Calendar dataInicial, @Param("dataFinal") Calendar dataFinal, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Agenda t WHERE t.data  BETWEEN :dataInicial AND :dataFinal AND t.situacao = :situacao AND empresa_id = :empresa")
    public Page<Agenda> procurarPorPeriodoESituacao(@Param("dataInicial") Calendar dataInicial, @Param("dataFinal") Calendar dataFinal,  @Param("situacao") Integer situacao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Agenda t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<Agenda> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);
   
     @Query("SELECT count(*) FROM Agenda t WHERE  empresa_id = :empresa")
    public Integer totalCadastros(@Param("empresa") Integer empresa);
    
    @Query("SELECT max(idDaEmpresa) FROM Agenda t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
  
}
