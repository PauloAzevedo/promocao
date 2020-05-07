package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PedidoAppRepository extends JpaRepository<PedidoApp, Long>{
    
    public Page<PedidoApp> findByClienteId(Integer id, Pageable paginacao);
    
    //public Page<PedidoApp> findById(Long id, Pageable paginacao);
    
    
    @Query("SELECT t FROM PedidoApp t WHERE t.numero  =  :numero  AND cliente_id = :cliente")
    public Optional<PedidoApp> procurarPorNumeroECliente(@Param("numero") Integer numero, @Param("cliente") Integer cliente);
    
    @Query("SELECT t FROM PedidoApp t WHERE t.numero  =  :numero  AND empresa_id = :empresa")
    public Optional<PedidoApp> procurarPorNumeroEEmpresa(@Param("numero") Integer numero, @Param("empresa") Integer empresa);
   
    
    @Query("SELECT t FROM PedidoApp t WHERE empresa_id = :empresa")
    public Page<PedidoApp> procurarTodosPorEmpresa(@Param("empresa") Integer empresa, Pageable paginacao);
    
      @Query("SELECT t FROM PedidoApp t WHERE empresa_id = :empresa AND situacao = :situacao")
    public Page<PedidoApp> procurarTodosPorEmpresaESituacao(@Param("empresa") Integer empresa,@Param("situacao") Integer situacao, Pageable paginacao);
    
    
    
    @Query("SELECT t FROM PedidoApp t WHERE cliente_id = :cliente_id")
    public Page<PedidoApp> procurarTodosPorCliente(@Param("cliente_id") Integer cliente_id, Pageable paginacao);
    
    @Query("SELECT t FROM PedidoApp t WHERE cliente_id = :cliente_id AND empresa_id = :empresa_id AND situacao = :situacao")
    public Page<PedidoApp> procurarTodosPorClienteEEmpresaESituacao(@Param("cliente_id") Integer cliente_id, @Param("empresa_id") Integer empresa_id, @Param("situacao") Integer situacao ,Pageable paginacao);
    
    @Query("SELECT t FROM PedidoApp t WHERE cliente_id = :cliente_id AND situacao = :situacao")
    public Page<PedidoApp> procurarTodosPorClienteESituacao(@Param("cliente_id") Integer cliente_id, @Param("situacao") Integer situacao ,Pageable paginacao);
    
    @Query("SELECT t FROM PedidoApp t WHERE cliente_id = :cliente_id AND situacao NOT IN :situacao")
    public Page<PedidoApp> procurarTodosPorClienteComSituacaoDiferenteDe(@Param("cliente_id") Integer cliente_id, @Param("situacao") Integer situacao ,Pageable paginacao);

    
    @Query("SELECT max(numero) FROM PedidoApp t WHERE  cliente = :cliente")
    public Integer maiorCadastro(@Param("cliente") Integer cliente);
    
}
