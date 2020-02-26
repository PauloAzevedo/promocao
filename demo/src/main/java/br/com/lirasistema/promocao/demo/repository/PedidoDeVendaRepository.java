package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoDeVendaRepository extends JpaRepository<PedidoDeVenda, Long> {
    
   public Page<PedidoDeVenda> findByEmitenteId(Integer id, Pageable paginacao);
    
    @Query("SELECT t FROM PedidoDeVenda t WHERE t.remetenteDestinatario.nome_RazaoSocial  LIKE %:descricao% AND emitente.id = :empresa")
    public Page<PedidoDeVenda> procurarPedidoDeVendaPorDescricaoEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM PedidoDeVenda t WHERE t.remetenteDestinatario.nome_RazaoSocial  LIKE %:descricao% AND t.situacaoNota = :filtro AND emitente.id = :empresa")
    public Page<PedidoDeVenda> procurarPedidoDeVendaPorDescricaoEFiltroEmpresa(@Param("descricao") String descricao,@Param("filtro") String filtro, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM PedidoDeVenda t WHERE t.situacaoNota = :filtro AND emitente.id = :empresa")
    public Page<PedidoDeVenda> procurarPedidoDeVendaPorFiltroEmpresa(@Param("filtro") String filtro, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM PedidoDeVenda t WHERE t.numeroNota  =  :numeroNota  AND emitente_id = :empresa")
    public Optional<PedidoDeVenda> procurarPorIdDaEmpresaEEmpresa(@Param("numeroNota") Integer numeroNota, @Param("empresa") Integer empresa);
    
    @Query("SELECT t FROM PedidoDeVenda t WHERE t.numeroNota  =  :numeroNota  AND emitente_id = :empresa")
    public Page<PedidoDeVenda> procurarTodosPorIdDaEmpresaEEmpresa(@Param("numeroNota") Integer numeroNota, @Param("empresa") Integer empresa, Pageable paginacao);

    
    @Query("SELECT max(numeroNota) FROM PedidoDeVenda t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
    
}
