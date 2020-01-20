/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.config.security;


import br.com.lira.apiliragestao.modelo.Usuario;
import br.com.lira.apiliragestao.modelo.UsuarioApi;
import br.com.lira.apiliragestao.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author paulo
 */
@Service
public class AutenticacaoService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //Optional<Empresa> empresa = empresaRepository
        Optional<UsuarioApi> usuario = usuarioRepository.findByLogin(login);
        if(usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados incorretos!");
    }
    
}
