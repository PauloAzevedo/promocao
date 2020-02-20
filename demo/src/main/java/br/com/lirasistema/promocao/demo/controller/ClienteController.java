package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Cliente;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.ClienteDto;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesDoClienteDto;
import br.com.lirasistema.promocao.demo.modelo.form.AtualizarClienteForm;
import br.com.lirasistema.promocao.demo.modelo.form.ClienteForm;
import br.com.lirasistema.promocao.demo.modelo.form.EmpresaForm;
import br.com.lirasistema.promocao.demo.repository.CidadeRepository;
import br.com.lirasistema.promocao.demo.repository.ClienteRepository;
import br.com.lirasistema.promocao.demo.repository.EnderecoRepository;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public Page<ClienteDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            if (descricao == null || descricao.isEmpty()) {
                Page<Cliente> clientes = clienteRepository.findByEmpresaId(usuario.getEmpresa().getId(), paginacao);
                return ClienteDto.converter(clientes);
            } else {
                if (filtro == null || filtro.isEmpty()) {
                    Page<Cliente> clientes = clienteRepository.procurarClientePorDescricaoEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                    return ClienteDto.converter(clientes);
                } else if (filtro.equals("fantasia")) {
                    Page<Cliente> clientes = clienteRepository.procurarClientePorFantasiaEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                    return ClienteDto.converter(clientes);
                } else if (filtro.equals("cnpj")) {
                    Page<Cliente> clientes = clienteRepository.procurarClientePorCNPJEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                    return ClienteDto.converter(clientes);
                } else if (filtro.equals("idDaEmpresa")) {
                    Integer idInterno;
                    try {
                        idInterno = Integer.parseInt(descricao);
                    } catch (Exception ex) {
                        idInterno = 0;
                    }
                    Page<Cliente> clientes = clienteRepository.procurarClientePorIdDaEmpresaEEmpresa(idInterno, usuario.getEmpresa().getId(), paginacao);
                    return ClienteDto.converter(clientes);
                } else {
                    Page<Cliente> clientes = clienteRepository.procurarClientePorDescricaoEEmpresa(descricao, usuario.getEmpresa().getId(), paginacao);
                    return ClienteDto.converter(clientes);
                }
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Cliente> cliente = clienteRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (cliente.isPresent()) {
                return ResponseEntity.ok(new DetalhesDoClienteDto(cliente.get()));
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteForm clienteF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Cliente> clienteExiste = clienteRepository.procurarClientePorCNPJIdenticoEEmpresa(clienteF.getCnpj(), usuario.getEmpresa().getId());
            if (!clienteExiste.isPresent()) {
                Cliente cliente = clienteF.converter(enderecoRepository, cidadeRepository, usuario, clienteRepository);
                clienteRepository.save(cliente);
                URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getIdDaEmpresa()).toUri();
                return ResponseEntity.created(uri).body(new ClienteDto(cliente));
            }
            return new ResponseEntity<String>("Cadastro já existente com o ID: " + clienteExiste.get().getIdDaEmpresa(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizarClienteForm clienteF, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        if (usuario != null) {
            Optional<Cliente> opt = clienteRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
            if (opt.isPresent()) {
                if (usuario.getEmpresa() != null && usuario.getEmpresa().getId() == opt.get().getEmpresa().getId()) {
                    Cliente empresaE = clienteF.atualizar(opt.get().getId(), clienteRepository, enderecoRepository, cidadeRepository);
                    return ResponseEntity.ok(new ClienteDto(empresaE));
                }
                return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

}
