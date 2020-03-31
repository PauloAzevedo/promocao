package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.UsuarioApiDto;
import br.com.lirasistema.promocao.demo.modelo.form.AtualizacaoUsuarioApiForm;
import br.com.lirasistema.promocao.demo.modelo.form.UsuarioApiForm;
import br.com.lirasistema.promocao.demo.repository.EmpresaRepository;
import br.com.lirasistema.promocao.demo.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public Page<UsuarioApiDto> lista(@RequestParam(required = false) String login,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (login == null || login.isEmpty()) {
                Page<UsuarioApi> promocoes = usuarioRepository.findAll(paginacao);
                return UsuarioApiDto.converter(promocoes);
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (id != null && id != 0 ) {
                Optional<UsuarioApi> promo = usuarioRepository.findById(id);
                if (promo.isPresent()) {
                    return ResponseEntity.ok(new UsuarioApiDto(promo.get()));
                }
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(new UsuarioApiDto(usuario));
            }
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioApiForm usuarioF, UriComponentsBuilder uriBuilder) {
        Optional<UsuarioApi> usuarioExiste = usuarioRepository.findByLogin(usuarioF.getLogin());
        if (!usuarioExiste.isPresent()) {
            UsuarioApi novoUsuario = usuarioF.converter(empresaRepository);
            usuarioRepository.save(novoUsuario);
            URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novoUsuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioApiDto(novoUsuario));
        }
        return new ResponseEntity<String>("Cadastro já existente para o login: " + usuarioExiste.get().getLogin(), HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioApiForm usuarioF, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<UsuarioApi> opt = usuarioRepository.findById(id);
        if (opt.isPresent()) {
            if (opt.get().equals(usuario)) {
                UsuarioApi topico = usuarioF.atualizar(id, usuarioRepository);
                return ResponseEntity.ok(new UsuarioApiDto(topico));
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.notFound().build();
    }

}
