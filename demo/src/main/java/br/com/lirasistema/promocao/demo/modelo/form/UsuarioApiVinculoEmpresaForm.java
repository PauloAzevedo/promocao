/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Empresa;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.EmpresaRepository;
import br.com.lirasistema.promocao.demo.repository.UsuarioRepository;
import java.util.Optional;

/**
 *
 * @author paulo
 */
public class UsuarioApiVinculoEmpresaForm {
    
    public UsuarioApi vincularEmpresa(Long id, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository, Integer empresa) {
        UsuarioApi usuarioE = usuarioRepository.getOne(id);
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresa);
        if(empresaOpt.isPresent()){
            usuarioE.setEmpresa(empresaOpt.get());
        } 
        return usuarioE;
    }
}
