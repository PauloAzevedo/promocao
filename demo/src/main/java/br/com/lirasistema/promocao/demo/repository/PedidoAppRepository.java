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
    
    @Query("SELECT t FROM PedidoApp t WHERE t.numero  =  :numero  AND emitente_id = :empresa")
    public Optional<PedidoApp> procurarPorNumeroEEmpresa(@Param("numero") Integer numero, @Param("empresa") Integer empresa);
   
    
    @Query("SELECT t FROM PedidoApp t WHERE empresa = :empresa")
    public Page<PedidoApp> procurarTodosPorEmpresa(@Param("empresa") Integer empresa, Pageable paginacao);
    
    
    
    @Query("SELECT t FROM PedidoApp t WHERE cliente = :cliente")
    public Page<PedidoApp> procurarTodosPorCliente(@Param("cliente") Integer cliente, Pageable paginacao);

    
    @Query("SELECT max(numero) FROM PedidoApp t WHERE  cliente = :cliente")
    public Integer maiorCadastro(@Param("cliente") Integer cliente);
    
}
