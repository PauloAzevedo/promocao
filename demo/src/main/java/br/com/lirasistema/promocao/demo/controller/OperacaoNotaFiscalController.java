package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.CentroCusto;
import br.com.lirasistema.promocao.demo.modelo.OperacaoNotaFiscal;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesDaOperacaoNotaFiscalDto;
import br.com.lirasistema.promocao.demo.modelo.dto.OperacaoNotaFiscalDto;
import br.com.lirasistema.promocao.demo.modelo.form.OperacaoNotaFiscalForm;
import br.com.lirasistema.promocao.demo.repository.CentroCustoRepository;
import br.com.lirasistema.promocao.demo.repository.OperacaoNotaFiscalRepository;
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
@RequestMapping("/operacoes")
public class OperacaoNotaFiscalController {

    @Autowired
    private OperacaoNotaFiscalRepository operacaoNotaFiscalRepository;
    
    @Autowired
    private CentroCustoRepository centroCustoRepository;
    
    @GetMapping
    public Page<OperacaoNotaFiscalDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                Page<OperacaoNotaFiscal> clientes = operacaoNotaFiscalRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                return OperacaoNotaFiscalDto.converter(clientes);
            } else {
                Page<OperacaoNotaFiscal> clientes = operacaoNotaFiscalRepository.procurarClientePorDescricaoEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                return OperacaoNotaFiscalDto.converter(clientes);
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<OperacaoNotaFiscal> op = operacaoNotaFiscalRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (op.isPresent()) {
                return ResponseEntity.ok(new DetalhesDaOperacaoNotaFiscalDto(op.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid OperacaoNotaFiscalForm operacaoForm, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            OperacaoNotaFiscal operacao = operacaoForm.converter(operacaoNotaFiscalRepository, usuario, centroCustoRepository);
            operacaoNotaFiscalRepository.save(operacao);
            URI uri = uriBuilder.path("/operacaoes/{id}").buildAndExpand(operacao.getIdDaEmpresa()).toUri();
            return ResponseEntity.created(uri).body(new OperacaoNotaFiscalDto(operacao));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid OperacaoNotaFiscalForm operacaoForm, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<OperacaoNotaFiscal> opt = operacaoNotaFiscalRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (opt.isPresent()) {
                if (usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmpresa().getId()) {
                    OperacaoNotaFiscal operacaoEditavel = operacaoForm.atualizar(opt.get().getIdDaEmpresa(), operacaoNotaFiscalRepository, usuario, centroCustoRepository);
                    return ResponseEntity.ok(new OperacaoNotaFiscalDto(operacaoEditavel));
                    
                }
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
}
