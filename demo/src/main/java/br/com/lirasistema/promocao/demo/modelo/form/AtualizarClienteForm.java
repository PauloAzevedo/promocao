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

public class AtualizarClienteForm {
    @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String fantasia;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String razaosocial;   
      
    @NotNull @NotEmpty @Length(min = 5)
    private String telefone;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String celular;
    
    @NotNull
    private Integer tipo;
    
    @NotNull
    private Integer pessoa;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String email;
    
    private String responsavel;
    
    private String telefoneResponsavel;    
    
    private String cpfResponsavel;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String cnpj;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String rg_InscricaoEstadual;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String logradouro;    
    
    private String tpLogradouro;
    
    @NotNull 
    private String bairro;
    
    @NotNull
    private String numero;
    
    @NotNull @NotEmpty
    private String cep;
    
    private String complemento;
    
    private String observacao;
    
        
    @NotNull @NotEmpty
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
    
        
         
      
    
    public Cliente atualizar(Integer id, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        Cliente clienteE = clienteRepository.getOne(id);
        clienteE.setFantasia(this.fantasia);
        clienteE.setNome_RazaoSocial(this.razaosocial);
        clienteE.setCelular(this.celular);
        clienteE.setTelefone(this.telefone);
        clienteE.setEmail(this.email);
        clienteE.setRg_InscricaoEstadual(this.rg_InscricaoEstadual);
        clienteE.setCpf_Cnpj(this.cnpj);
        clienteE.setObservacao(this.observacao);
        clienteE.setTipo(this.tipo);
        clienteE.setPessoa(this.pessoa);
        clienteE.setResponsavel(this.responsavel);
        clienteE.setTelefoneResponsavel(this.telefoneResponsavel);
        clienteE.setCpfResponsavel(this.cpfResponsavel);        
        Optional<Endereco> endE = enderecoRepository.findById(clienteE.getEndereco().getId());
        if(endE.isPresent()){
            Endereco endObj = endE.get();
            endObj.setLogradouro(this.logradouro);
            endObj.setNumero(this.numero);
            endObj.setComplemento(this.complemento);
            endObj.setBairro(this.bairro);            
            Optional<Cidade> cidadeObj = cidadeRepository.findByCodigoIBGE(cidade);
            if(cidadeObj.isPresent()){
                endObj.setCidade(this.cidade);
                endObj.setCidadeObjeto(cidadeObj.get());
            }          
            
            clienteE.setEndereco(endObj);
        }
        return clienteE;
    }
}
