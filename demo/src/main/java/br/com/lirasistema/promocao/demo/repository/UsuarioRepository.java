
package br.com.lirasistema.promocao.demo.repository;



import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author paulo
 */
public interface UsuarioRepository extends JpaRepository<UsuarioApi, Long> {
    
    //@Query("SELECT t FROM Cep t WHERE t.logradouro LIKE  :logradouro ")
    //Optional<Usuario> findByLoginCnpj(@Param("login") String login, @Param("empresaCnpj") String empresaCnpj);
    
    Optional<UsuarioApi> findByLogin(String login);
    
}
