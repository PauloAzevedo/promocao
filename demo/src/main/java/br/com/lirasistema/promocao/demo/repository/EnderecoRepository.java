package br.com.lirasistema.promocao.demo.repository;

import br.com.lirasistema.promocao.demo.modelo.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
