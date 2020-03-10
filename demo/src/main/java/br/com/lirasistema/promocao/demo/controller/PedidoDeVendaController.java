package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Cliente;
import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.ClienteDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhePedidoDeVendaDto;
import br.com.lirasistema.promocao.demo.modelo.dto.PedidoDeVendaDto;
import br.com.lirasistema.promocao.demo.modelo.form.AtualizarClienteForm;
import br.com.lirasistema.promocao.demo.modelo.form.AtualizarPedidoForm;
import br.com.lirasistema.promocao.demo.modelo.form.ClienteForm;
import br.com.lirasistema.promocao.demo.modelo.form.PedidoForm;
import br.com.lirasistema.promocao.demo.repository.PedidoDeVendaRepository;
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
@RequestMapping("/pedidos")
public class PedidoDeVendaController {

    @Autowired
    private PedidoDeVendaRepository pedidoDeVendaRepository;

    @GetMapping
    public Page<PedidoDeVendaDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                if (filtro == null || filtro.isEmpty()) {
                    Page<PedidoDeVenda> pdvs = pedidoDeVendaRepository.findByEmitenteId(usuario.getEmpresa().getId(), paginacao);
                    return PedidoDeVendaDto.converter(pdvs);
                } else {
                    Page<PedidoDeVenda> pdvs = pedidoDeVendaRepository.procurarPedidoDeVendaPorFiltroEmpresa(filtro, usuario.getEmpresa().getId(), paginacao);
                    return PedidoDeVendaDto.converter(pdvs);
                }
            } else {
                if (filtro == null || filtro.isEmpty()) {
                    Page<PedidoDeVenda> pdvs = pedidoDeVendaRepository.procurarPedidoDeVendaPorDescricaoEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                    return PedidoDeVendaDto.converter(pdvs);
                } else {
                    if (filtro.equals("0")) {
                        Integer idFiltro;
                        try {
                            idFiltro = Integer.parseInt(descricao);
                        } catch (Exception ex) {
                            idFiltro = 0;
                        }
                        Page<PedidoDeVenda> pdvs = pedidoDeVendaRepository.procurarTodosPorIdDaEmpresaEEmpresa(idFiltro, usuario.getEmpresa().getId(), paginacao);
                        return PedidoDeVendaDto.converter(pdvs);
                    }
                    Page<PedidoDeVenda> pdvs = pedidoDeVendaRepository.procurarPedidoDeVendaPorDescricaoEFiltroEmpresa(descricao, filtro, usuario.getEmpresa().getId(), paginacao);
                    return PedidoDeVendaDto.converter(pdvs);
                }
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<PedidoDeVenda> op = pedidoDeVendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (op.isPresent()) {
                return ResponseEntity.ok(new DetalhePedidoDeVendaDto(op.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PedidoForm pedidoF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            PedidoDeVenda pedidoNovo = pedidoF.converter(usuario);
            pedidoDeVendaRepository.save(pedidoNovo);
            URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoNovo.getNumeroNota()).toUri();
            return ResponseEntity.created(uri).body(new PedidoDeVendaDto(pedidoNovo));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizarPedidoForm pedidoA, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<PedidoDeVenda> opt = pedidoDeVendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (opt.isPresent()) {
                if (usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmitente().getId()) {
                    PedidoDeVenda pedidoE = pedidoA.atualizar(usuario);
                    return ResponseEntity.ok(new PedidoDeVendaDto(pedidoE));
                }
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
