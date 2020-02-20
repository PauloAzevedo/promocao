package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Agenda;
import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.AgendaDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesDaAgendaDto;
import br.com.lirasistema.promocao.demo.modelo.dto.GrupoDto;
import br.com.lirasistema.promocao.demo.modelo.form.AgendaForm;
import br.com.lirasistema.promocao.demo.modelo.form.GrupoForm;
import br.com.lirasistema.promocao.demo.repository.AgendaRepository;
import br.com.lirasistema.promocao.demo.repository.ClienteRepository;
import br.com.lirasistema.promocao.demo.utilidades.Util;
import java.net.URI;
import java.util.Calendar;
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
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public Page<AgendaDto> lista(@RequestParam(required = false) String dataInicial, @RequestParam(required = false) String dataFinal, @RequestParam(required = false) Integer situacao,
            @PageableDefault(sort = "idDaEmpresa", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (dataInicial == null || dataInicial.isEmpty() || dataFinal == null || dataFinal.isEmpty()) {
                Page<Agenda> agendas = agendaRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                return AgendaDto.converter(agendas);
            } else {
                if (situacao == null || situacao < 0) {
                    Calendar inicioC = Util.stringToCalendarWeb(dataInicial);
                    Calendar fimC = Util.stringToCalendarWeb(dataFinal);
                    Page<Agenda> agendas = agendaRepository.procurarPorPeriodo(inicioC, fimC, usuario.getEmpresa().getId(), paginacao);
                    return AgendaDto.converter(agendas);
                } else {
                    Calendar inicioC = Util.stringToCalendarWeb(dataInicial);
                    Calendar fimC = Util.stringToCalendarWeb(dataFinal);
                    Page<Agenda> agendas = agendaRepository.procurarPorPeriodoESituacao(inicioC, fimC, situacao, usuario.getEmpresa().getId(), paginacao);
                    return AgendaDto.converter(agendas);
                }
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Agenda> agenda = agendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (agenda.isPresent()) {
                return ResponseEntity.ok(new DetalhesDaAgendaDto(agenda.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AgendaForm clienteF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Agenda cliente = clienteF.converter(usuario, clienteRepository, agendaRepository);
            agendaRepository.save(cliente);
            URI uri = uriBuilder.path("/agendas/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(new AgendaDto(cliente));
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid AgendaForm agendaEditar, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<Agenda> opt = agendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
        if (opt.isPresent()) {
            if (usuario.getEmpresa().getHashTexto().equals(opt.get().getEmpresa().getHashTexto())) {
                Agenda agendaAtualizada = agendaEditar.atualizar(id, agendaRepository, usuario, clienteRepository);
                return ResponseEntity.ok(new AgendaDto(agendaAtualizada));
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario.getEmpresa() != null) {
            Optional<Agenda> opt = agendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (!opt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            if (usuario.getEmpresa().getHashTexto().equals(opt.get().getEmpresa().getHashTexto())) {
                agendaRepository.delete(opt.get());
                return ResponseEntity.ok().build();
            }
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }
}
