package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Promocao;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesPromocaoDto;
import br.com.lirasistema.promocao.demo.modelo.dto.PromocaoDto;
import br.com.lirasistema.promocao.demo.modelo.form.PromocaoForm;
import br.com.lirasistema.promocao.demo.repository.PromocaoRepository;
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
@RequestMapping("/promocoes")
public class PromocaoControler {

    @Autowired
    private PromocaoRepository promocaoRepository;
    
    

    @GetMapping
    public Page<PromocaoDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                Page<Promocao> promocoes = promocaoRepository.findAll(paginacao);
                return PromocaoDto.converter(promocoes);
            } else {
                if (filtro != null) {
                    if (filtro.equals("titulo")) {
                        Page<Promocao> promocoes = promocaoRepository.findByTituloContaining(descricao, paginacao);
                        return PromocaoDto.converter(promocoes);
                    } else if (filtro.equals("valor")) {
                        Double valor = 0.0;
                        try {
                            valor = Double.parseDouble(descricao);
                        } catch (Exception ex) {
                            valor = 0.0;
                        }
                        Page<Promocao> promocoes = promocaoRepository.findByValor(valor,  paginacao);
                        return PromocaoDto.converter(promocoes);
                    } else if (filtro.equals("autor")) {
                        Page<Promocao> promocoes = promocaoRepository.findByAutorLogin(descricao,  paginacao);
                        return PromocaoDto.converter(promocoes);
                    } else {
                        Page<Promocao> promocoes = promocaoRepository.findByTituloContaining(descricao,  paginacao);
                        return PromocaoDto.converter(promocoes);
                    }
                } else {
                    Page<Promocao> promocoes = promocaoRepository.findByTituloContaining(descricao,  paginacao);
                    return PromocaoDto.converter(promocoes);
                }
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Promocao> promo = promocaoRepository.findById(id);
            if (promo.isPresent()) {
                return ResponseEntity.ok(new DetalhesPromocaoDto(promo.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PromocaoDto> cadastrar(@RequestBody @Valid PromocaoForm promoF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Promocao promo = promoF.converter(usuario);
        promocaoRepository.save(promo);
        URI uri = uriBuilder.path("/promocoes/{id}").buildAndExpand(promo.getId()).toUri();
        return ResponseEntity.created(uri).body(new PromocaoDto(promo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid PromocaoForm topicof, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<Promocao> opt = promocaoRepository.findById(id);
        if (opt.isPresent()) {
            if( opt.get().getAutor().equals(usuario)){
            Promocao topico = topicof.atualizar(id, promocaoRepository);
            return ResponseEntity.ok(new PromocaoDto(topico));
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id, @AuthenticationPrincipal Authentication usuarioLogado, @RequestBody @Valid PromocaoForm topicof) {
        Optional<Promocao> opt = promocaoRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //verifica se quem ta excluindo eh o proprio dono do topico:
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario.equals(opt.get().getAutor())) {
            Promocao topico = topicof.alterarSituacao(id, promocaoRepository);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
