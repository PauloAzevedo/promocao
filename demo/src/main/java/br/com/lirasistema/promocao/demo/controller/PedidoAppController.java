package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.PedidoAppDto;
import br.com.lirasistema.promocao.demo.modelo.form.AtualizarPedidoAppForm;
import br.com.lirasistema.promocao.demo.modelo.form.PedidoAppForm;
import br.com.lirasistema.promocao.demo.repository.CondicaoDePagamentoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import br.com.lirasistema.promocao.demo.repository.PedidoAppRepository;
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
@RequestMapping("/pedidosapp")
public class PedidoAppController {

    @Autowired
    private PedidoAppRepository pedidoAppRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CondicaoDePagamentoRepository condicaoDePagamentoRepository;

    @GetMapping
    public Page<PedidoAppDto> lista(@RequestParam(required = false) Integer empresa, @RequestParam(required = false) Integer situacao,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getEmpresa() != null) {
            Page<PedidoApp> pdvs = pedidoAppRepository.procurarTodosPorEmpresaESituacao(usuario.getEmpresa().getId(), situacao, paginacao);
            return PedidoAppDto.converter(pdvs);
        } else if (usuario != null && usuario.getCliente() != null) {
            if (empresa != null && situacao != null) {
                Page<PedidoApp> pdvs = pedidoAppRepository.procurarTodosPorClienteEEmpresaESituacao(usuario.getCliente().getId(), empresa, situacao, paginacao);
                return PedidoAppDto.converter(pdvs);
            } else if (situacao != null) {
                if (situacao == -1) {
                    Page<PedidoApp> pdvs = pedidoAppRepository.procurarTodosPorClienteComSituacaoDiferenteDe(usuario.getCliente().getId(), situacao, paginacao);
                    return PedidoAppDto.converter(pdvs);
                } else {
                    Page<PedidoApp> pdvs = pedidoAppRepository.procurarTodosPorClienteESituacao(usuario.getCliente().getId(), situacao, paginacao);
                    return PedidoAppDto.converter(pdvs);
                }
            } else {
                Page<PedidoApp> pdvs = pedidoAppRepository.procurarTodosPorCliente(usuario.getCliente().getId(), paginacao);
                return PedidoAppDto.converter(pdvs);
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<PedidoApp> op = pedidoAppRepository.findById(id);
            if (op.isPresent()) {
                return ResponseEntity.ok(new PedidoAppDto(op.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PedidoAppForm pedidoAppF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            PedidoApp pedidoNovo = pedidoAppF.converter(usuario, itemRepository, pedidoAppRepository);
            pedidoAppRepository.save(pedidoNovo);
            URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoNovo.getId()).toUri();
            return ResponseEntity.created(uri).body(new PedidoAppDto(pedidoNovo));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPedidoAppForm pedidoA, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<PedidoApp> opt = pedidoAppRepository.findById(id);
            if (opt.isPresent()) {
                if (usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmpresa().getId() || usuario.getCliente().getId() == opt.get().getCliente().getId()) {
                    PedidoApp pedidoE = pedidoA.atualizar(id, usuario, itemRepository, pedidoAppRepository, condicaoDePagamentoRepository);
                    return ResponseEntity.ok(new PedidoAppDto(pedidoE));
                }
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
