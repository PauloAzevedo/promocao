/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Cliente;
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
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    public Page<Cliente> findByEmpresaId(Integer id, Pageable paginacao);

    @Query("SELECT t FROM Cliente t WHERE t.nome_RazaoSocial  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<Cliente> procurarClientePorDescricaoEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
     @Query("SELECT t FROM Cliente t WHERE t.fantasia  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<Cliente> procurarClientePorFantasiaEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Cliente t WHERE t.cpf_Cnpj  LIKE %:descricao% AND empresa_id = :empresa")
    public Page<Cliente> procurarClientePorCNPJEEmpresa(@Param("descricao") String descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Cliente t WHERE t.idDaEmpresa  = :descricao AND empresa_id = :empresa")
    public Page<Cliente> procurarClientePorIdDaEmpresaEEmpresa(@Param("descricao") Integer descricao, @Param("empresa") Integer empresa, Pageable paginacao);
    
    @Query("SELECT t FROM Cliente t WHERE t.idDaEmpresa  =  :idDaEmpresa  AND empresa_id = :empresa")
    public Optional<Cliente> procurarPorIdDaEmpresaEEmpresa(@Param("idDaEmpresa") Integer idDaEmpresa,@Param("empresa") Integer empresa);
    
    @Query("SELECT count(*) FROM Cliente t WHERE  empresa_id = :empresa")
    public Integer totalCadastros(@Param("empresa") Integer empresa);
    
    @Query("SELECT t FROM Cliente t WHERE t.cpf_Cnpj  =  :cpf_Cnpj  AND empresa_id = :empresa")
    public Optional<Cliente> procurarClientePorCNPJIdenticoEEmpresa(@Param("cpf_Cnpj") String descricao,@Param("empresa") Integer empresa);
    
    @Query("SELECT max(idDaEmpresa) FROM Cliente t WHERE  empresa_id = :empresa")
    public Integer maiorCadastro(@Param("empresa") Integer empresa);
    
    
}
