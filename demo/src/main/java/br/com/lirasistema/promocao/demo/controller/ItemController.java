package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.Promocao;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesItemDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesPromocaoDto;
import br.com.lirasistema.promocao.demo.modelo.dto.ItemDto;
import br.com.lirasistema.promocao.demo.modelo.dto.PromocaoDto;
import br.com.lirasistema.promocao.demo.modelo.form.ItemForm;
import br.com.lirasistema.promocao.demo.modelo.form.PromocaoForm;
import br.com.lirasistema.promocao.demo.repository.GrupoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping
    public Page<ItemDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                Page<Item> itens = itemRepository.findAll(paginacao);
                return ItemDto.converter(itens);
            } else {
                if (filtro.equals("empresa")) {
                    Page<Item> itens = itemRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                    return ItemDto.converter(itens);
                }
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isPresent()) {
                return ResponseEntity.ok(new DetalhesItemDto(item.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ItemForm itemF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getEmpresa() != null) {
            Item promo = itemF.converter(usuario, grupoRepository);
            itemRepository.save(promo);
            URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(promo.getId()).toUri();
            return ResponseEntity.created(uri).body(new ItemDto(promo));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
}
