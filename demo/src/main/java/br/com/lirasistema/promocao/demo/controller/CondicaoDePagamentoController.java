package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.CondicaoDePagamento;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.CondicaoDePagamentoDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesDaCondicaoDePagamentoDto;
import br.com.lirasistema.promocao.demo.modelo.form.CondicaoDePagamentoForm;
import br.com.lirasistema.promocao.demo.repository.CondicaoDePagamentoRepository;
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
@RequestMapping("/condicoes")
public class CondicaoDePagamentoController {

    @Autowired
    private CondicaoDePagamentoRepository condicaoDePagamentoRepository;

    @GetMapping
    public Page<CondicaoDePagamentoDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                Page<CondicaoDePagamento> condicoes = condicaoDePagamentoRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                return CondicaoDePagamentoDto.converter(condicoes);
            } else {
                Page<CondicaoDePagamento> condicoes = condicaoDePagamentoRepository.procurarClientePorDescricaoEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                return CondicaoDePagamentoDto.converter(condicoes);
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<CondicaoDePagamento> op = condicaoDePagamentoRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (op.isPresent()) {
                return ResponseEntity.ok(new DetalhesDaCondicaoDePagamentoDto(op.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CondicaoDePagamentoForm condicaoFormulario, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            CondicaoDePagamento condicao = condicaoFormulario.converter(usuario, condicaoDePagamentoRepository);
            condicaoDePagamentoRepository.save(condicao);
            URI uri = uriBuilder.path("/codicoes/{id}").buildAndExpand(condicao.getIdDaEmpresa()).toUri();
            return ResponseEntity.created(uri).body(new CondicaoDePagamentoDto(condicao));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid CondicaoDePagamentoForm codicaoFormulario, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<CondicaoDePagamento> opt = condicaoDePagamentoRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (opt.isPresent()) {
                if (usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmpresa().getId()) {
                    CondicaoDePagamento condicaoEditavel = codicaoFormulario.atualizar(opt.get().getId(), condicaoDePagamentoRepository);
                    return ResponseEntity.ok(new CondicaoDePagamentoDto(condicaoEditavel));
                }
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario.getEmpresa() != null) {
            Optional<CondicaoDePagamento> opt = condicaoDePagamentoRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (!opt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            if (usuario.getEmpresa().getHashTexto().equals(opt.get().getEmpresa().getHashTexto())) {
                condicaoDePagamentoRepository.delete(opt.get());
                return ResponseEntity.ok().build();
            }
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
