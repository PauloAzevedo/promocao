/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Cidade;
import br.com.lirasistema.promocao.demo.modelo.Cliente;
import br.com.lirasistema.promocao.demo.modelo.Endereco;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.CidadeRepository;
import br.com.lirasistema.promocao.demo.repository.ClienteRepository;
import br.com.lirasistema.promocao.demo.repository.EnderecoRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author paulo
 */
public class ClienteForm {

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 100)
    private String fantasia;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String razaosocial;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String telefone;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String celular;

    @NotNull
    private Integer tipo;

    @NotNull
    private Integer pessoa;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String email;

    private String responsavel;

    private String telefoneResponsavel;

    private String cpfResponsavel;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String cnpj;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String rg_InscricaoEstadual;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String logradouro;

    private String tpLogradouro;

    @NotNull
    private String bairro;

    @NotNull
    private String numero;

    @NotNull
    @NotEmpty
    private String cep;

    private String complemento;

    private String observacao;

    @NotNull
    @NotEmpty
    private String cidade;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(String tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg_InscricaoEstadual() {
        return rg_InscricaoEstadual;
    }

    public void setRg_InscricaoEstadual(String rg_InscricaoEstadual) {
        this.rg_InscricaoEstadual = rg_InscricaoEstadual;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public Cliente converter(EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository, UsuarioApi usuario, ClienteRepository clienteRepository) {
        Endereco end = null;
        Optional<Cidade> cidadeObj = cidadeRepository.findByCodigoIBGE(cidade);
        if (cidadeObj.isPresent()) {
            end = new Endereco(logradouro, tpLogradouro, bairro, numero, cep, complemento, cidadeObj.get(), cidade);
            enderecoRepository.save(end);
        }

        if (usuario.getEmpresa() != null) {
            Integer totalClientes = 0;
            try {
                totalClientes = clienteRepository.maiorCadastro(usuario.getEmpresa().getId());
                totalClientes += 1;
                System.out.println(totalClientes);
            } catch (Exception ex) {
                totalClientes = 1;
            }
            return new Cliente(razaosocial, fantasia, telefone, celular, email, rg_InscricaoEstadual, cnpj, responsavel, telefoneResponsavel, observacao, tipo, pessoa, cpfResponsavel, end, usuario.getEmpresa(), totalClientes);
        } else {
            return new Cliente(razaosocial, fantasia, telefone, celular, email, rg_InscricaoEstadual, cnpj, responsavel, telefoneResponsavel, observacao, tipo, pessoa, cpfResponsavel, end, null, 1);
        }
    }

    public Cliente atualizar(Integer id, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        Cliente clienteE = clienteRepository.getOne(id);
        clienteE.setFantasia(this.fantasia);
        clienteE.setNome_RazaoSocial(this.razaosocial);
        clienteE.setCelular(this.celular);
        clienteE.setTelefone(this.telefone);
        Optional<Endereco> endE = enderecoRepository.findById(clienteE.getEndereco().getId());
        if (endE.isPresent()) {
            Endereco endObj = endE.get();
            endObj.setLogradouro(this.logradouro);
            endObj.setNumero(this.numero);
            endObj.setComplemento(this.complemento);
            endObj.setBairro(this.bairro);
            endObj.setCidade(this.cidade);
            clienteE.setEndereco(endObj);
        }
        return clienteE;
    }

}
