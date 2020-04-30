package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.GrupoDto;
import br.com.lirasistema.promocao.demo.modelo.form.GrupoForm;
import br.com.lirasistema.promocao.demo.repository.GrupoRepository;
import java.net.URI;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping
    public Page<GrupoDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) Integer empresa, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if(usuario.getEmpresa()!=null){
            Page<Grupo> empresas = grupoRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
            return GrupoDto.converter(empresas);
        } else if(descricao == null || descricao.equals("")) {
            //Page<Grupo> empresas = grupoRepository.findAll(paginacao);
            Page<Grupo> empresas = grupoRepository.findByEmpresaId(empresa, paginacao);
            return GrupoDto.converter(empresas);
        } else {
            if (filtro.equals("descricao")) {
                Page<Grupo> empresas = grupoRepository.findByDescricaoContaining(descricao, paginacao);
                return GrupoDto.converter(empresas);
            } else if (filtro.equals("empresa")) {
                Page<Grupo> empresas = grupoRepository.findByEmpresaId(empresa, paginacao);
                return GrupoDto.converter(empresas);
            } else {
                Page<Grupo> empresas = grupoRepository.findAll(paginacao);
                return GrupoDto.converter(empresas);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        //UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        //if (usuario != null) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        if (grupo.isPresent()) {
            return ResponseEntity.ok(new GrupoDto(grupo.get()));
        }
        return ResponseEntity.notFound().build();
        //}
        //return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid GrupoForm grupoF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Grupo grupo = grupoF.converter(usuario);
            grupoRepository.save(grupo);
            URI uri = uriBuilder.path("/grupos/{id}").buildAndExpand(grupo.getId()).toUri();
            return ResponseEntity.created(uri).body(new GrupoDto(grupo));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid GrupoForm grupoE, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<Grupo> opt = grupoRepository.findById(id);
        if (opt.isPresent()) {
            if(usuario.getEmpresa().getHashTexto().equals(opt.get().getEmpresa().getHashTexto())){
            Grupo grupo = grupoE.atualizar(id, grupoRepository);
            return ResponseEntity.ok(new GrupoDto(grupo));
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado, @RequestBody @Valid GrupoForm grupoD) {
        Optional<Grupo> opt = grupoRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //verifica se quem ta excluindo eh o proprio dono do topico:
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if(usuario.getEmpresa() !=null && usuario.getEmpresa().getHashTexto().equals(opt.get().getEmpresa().getHashTexto())){
            Grupo topico = grupoD.alterarSituacao(id, grupoRepository);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
}
