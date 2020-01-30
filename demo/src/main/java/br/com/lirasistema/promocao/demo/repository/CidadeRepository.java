package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Cidade;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    Optional<Cidade> findByCodigoIBGE(@Param("codigoIBGE") String codigoIBGE);
}
