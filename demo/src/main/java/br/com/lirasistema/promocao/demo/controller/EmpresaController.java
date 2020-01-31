package br.com.lirasistema.promocao.demo.controller;

import br.com.lirasistema.promocao.demo.modelo.Empresa;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.modelo.dto.DetalhesEmpresaDto;
import br.com.lirasistema.promocao.demo.modelo.dto.EmpresaDto;
import br.com.lirasistema.promocao.demo.modelo.form.EmpresaForm;
import br.com.lirasistema.promocao.demo.repository.CidadeRepository;
import br.com.lirasistema.promocao.demo.repository.EmpresaRepository;
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
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public Page<EmpresaDto> lista(@RequestParam(required = false) String descricao, @RequestParam(required = false) String filtro,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao, @AuthenticationPrincipal Authentication usuarioLogado) {

        if (descricao == null || descricao.equals("")) {
            Page<Empresa> empresas = empresaRepository.findAll(paginacao);
            return EmpresaDto.converter(empresas);
        } else {
            if (filtro.equals("fantasia")) {
                Page<Empresa> empresas = empresaRepository.findByFantasiaContaining(descricao, paginacao);
                return EmpresaDto.converter(empresas);
            } else if (filtro.equals("cnpj")) {
                Page<Empresa> empresas = empresaRepository.findByCnpjContaining(descricao, paginacao);
                return EmpresaDto.converter(empresas);
            } else if (filtro.equals("ramo")) {
                Page<Empresa> empresas = empresaRepository.findByRamo(descricao, paginacao);
                return EmpresaDto.converter(empresas);
            } else {
                Page<Empresa> empresas = empresaRepository.findAll(paginacao);
                return EmpresaDto.converter(empresas);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Integer id, @AuthenticationPrincipal Authentication usuarioLogado) {
        //UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        //if (usuario != null) {
        Optional<Empresa> emp = empresaRepository.findById(id);
        if (emp.isPresent()) {
            return ResponseEntity.ok(new DetalhesEmpresaDto(emp.get()));
        }
        return ResponseEntity.notFound().build();
        //}
        //return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmpresaDto> cadastrar(@RequestBody @Valid EmpresaForm empF, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Authentication usuarioLogado) {
        // UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Empresa empresa = empF.converter(enderecoRepository, cidadeRepository);
        empresaRepository.save(empresa);
        URI uri = uriBuilder.path("/empresas/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmpresaDto(empresa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody @Valid EmpresaForm empF, @AuthenticationPrincipal Authentication usuarioLogado) {
        UsuarioApi usuario = (UsuarioApi) usuarioLogado.getPrincipal();
        Optional<Empresa> opt = empresaRepository.findById(id);
        if (opt.isPresent()) {
            if(usuario.getEmpresa()!= null && usuario.getEmpresa().getHashTexto().equals(opt.get().getHashTexto())){
                Empresa empresaE = empF.atualizar(id, empresaRepository, enderecoRepository);
                return ResponseEntity.ok(new EmpresaDto(empresaE));
            }
            return new ResponseEntity<String>("Você não tem permissão para executar essa operação!", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.notFound().build();
    }

}
