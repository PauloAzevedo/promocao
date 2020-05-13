package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Arquivo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.ItemPedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.ArquivoDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesItemDto;
import br.com.lirasistema.promocao.demo.modelo.dto.ItemDto;
import br.com.lirasistema.promocao.demo.modelo.form.ArquivoForm;
import br.com.lirasistema.promocao.demo.modelo.form.ItemForm;
import br.com.lirasistema.promocao.demo.modelo.form.ItemFormEditar;
import br.com.lirasistema.promocao.demo.repository.ArquivoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import br.com.lirasistema.promocao.demo.utilidades.Util;
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

/**
 *
 * @author paulo
 */
@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public Page<ArquivoDto> lista(@RequestParam(required = false) Integer tipo, @RequestParam(required = false) String empresa,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getEmpresa() != null) {
            if (tipo == null) {
                Page<Arquivo> itens = arquivoRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                return ArquivoDto.converter(itens);
            } else {
                Page<Arquivo> itens = arquivoRepository.procurarPorTipoEEmpresa(tipo, usuario.getEmpresa().getId(), paginacao);
                return ArquivoDto.converter(itens);
            }
        } else if (usuario != null && usuario.getCliente() != null) {
            Integer empresaCorreta = Util.validarInteiro(empresa);
            if (tipo != null) {
                Page<Arquivo> itens = arquivoRepository.procurarPorTipoEEmpresa(tipo, empresaCorreta, paginacao);
                return ArquivoDto.converter(itens);
            } else {
                Page<Arquivo> itens = arquivoRepository.findByEmpresaId(empresaCorreta, paginacao);
                return ArquivoDto.converter(itens);
            }            
        }
        return null;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Arquivo> item = arquivoRepository.findById(id);
            if (item.isPresent()) {
                return ResponseEntity.ok(new ArquivoDto(item.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ArquivoForm arquivoF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getEmpresa() != null) {
            Arquivo itemE = arquivoF.converter(itemRepository, usuario);
            arquivoRepository.save(itemE);
            URI uri = uriBuilder.path("/arquivos/{id}").buildAndExpand(itemE.getId()).toUri();
            return ResponseEntity.created(uri).body(new ArquivoDto(itemE));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
    
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ArquivoForm arquivoF, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<Arquivo> opt = arquivoRepository.findById(id);
        if (opt.isPresent()) {
            try {
                if (usuario != null && usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmpresa().getId()) {
                    Arquivo arquivoEditado = arquivoF.atualizar(id, itemRepository, arquivoRepository, usuario);
                    return ResponseEntity.ok(new ArquivoDto(arquivoEditado));
                } else {
                    return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
                }
            } catch (Exception ex) {
                return new ResponseEntity<String>("Erro inesperado ao executar a atualização!", HttpStatus.BAD_REQUEST);
            }

        }
        return ResponseEntity.notFound().build();
    }
    
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        Optional<Arquivo> opt = arquivoRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null && usuario.getEmpresa() != null) {              
            if (usuario.getEmpresa().getId() == opt.get().getEmpresa().getId()) {
                arquivoRepository.delete(opt.get());
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
}
