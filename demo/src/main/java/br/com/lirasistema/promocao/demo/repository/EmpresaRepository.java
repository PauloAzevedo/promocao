package br.com.lirasistema.promocao.demo.repository;


import br.com.lirasistema.promocao.demo.modelo.Empresa;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    
    public Page<Empresa> findByFantasiaContaining(@Param("fantasia") String fantasia, Pageable paginacao);
    
    public Page<Empresa> findByCnpjContaining(@Param("cnpj") String cnpj, Pageable paginacao);
    
    public Page<Empresa> findByRamo(@Param("ramo") Integer ramo, Pageable paginacao);
    
    public Optional<Empresa> findByHashTexto(String hashTexto);
}
