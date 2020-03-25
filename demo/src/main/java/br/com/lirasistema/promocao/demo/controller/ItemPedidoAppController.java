package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.ItemPedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.ItemPedidoAppDto;
import br.com.lirasistema.promocao.demo.modelo.form.GrupoForm;
import br.com.lirasistema.promocao.demo.modelo.form.ItemPedidoAppForm;
import br.com.lirasistema.promocao.demo.repository.ItemPedidoAppRepository;
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
@RequestMapping("/itensapp")
public class ItemPedidoAppController {

    @Autowired
    private ItemPedidoAppRepository itemPedidoAppRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoAppRepository pedidoAppRepository;

    @GetMapping
    public Page<ItemPedidoAppDto> lista(@RequestParam(required = true) Long pedido,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {

        Page<ItemPedidoApp> itensApp = itemPedidoAppRepository.findByPedidoAppId(pedido, paginacao);
        return ItemPedidoAppDto.converter(itensApp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        //UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        //if (usuario != null) {
        Optional<ItemPedidoApp> itemAppOpt = itemPedidoAppRepository.findById(id);
        if (itemAppOpt.isPresent()) {
            return ResponseEntity.ok(new ItemPedidoAppDto(itemAppOpt.get()));
        }
        return ResponseEntity.notFound().build();
        //}
        //return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ItemPedidoAppForm itemAppF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getCliente() != null) {
            ItemPedidoApp itemApp = itemAppF.converter(usuario, itemRepository, pedidoAppRepository);
            itemPedidoAppRepository.save(itemApp);
            URI uri = uriBuilder.path("/itensapp/{id}").buildAndExpand(itemApp.getId()).toUri();
            return ResponseEntity.created(uri).body(new ItemPedidoAppDto(itemApp));
        }
        return new ResponseEntity<String>("Verifique seu cadastro, dados incompletos de usuario/cliente...!", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ItemPedidoAppForm itemAppF, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<ItemPedidoApp> opt = itemPedidoAppRepository.findById(id);
        if (opt.isPresent()) {
            if (usuario != null && usuario.getCliente() != null && usuario.getCliente().getId() == opt.get().getPedidoApp().getCliente().getId()) {
                ItemPedidoApp itemAppE = itemAppF.atualizar(id, itemPedidoAppRepository);
                return ResponseEntity.ok(new ItemPedidoAppDto(itemAppE));
            } else {
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado, @RequestBody @Valid GrupoForm grupoD) {
        Optional<ItemPedidoApp> opt = itemPedidoAppRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getCliente() != null && usuario.getCliente().getId() == opt.get().getPedidoApp().getCliente().getId()) {
            Optional<ItemPedidoApp> itemAppD = itemPedidoAppRepository.findById(id);
            if (itemAppD.isPresent()) {
                itemPedidoAppRepository.delete(itemAppD.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
