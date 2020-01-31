package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Cidade;
import br.com.lirasistema.promocao.demo.modelo.Empresa;
import br.com.lirasistema.promocao.demo.modelo.Endereco;
import br.com.lirasistema.promocao.demo.repository.CidadeRepository;
import br.com.lirasistema.promocao.demo.repository.EmpresaRepository;
import br.com.lirasistema.promocao.demo.repository.EnderecoRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class EmpresaForm {
    @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String fantasia;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String razaosocial;   
      
    @NotNull @NotEmpty @Length(min = 5)
    private String telefone;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String celular;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String caminhoLogo;
    
    @NotNull
    private Integer ramo;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String cnpj;
    
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
    
    @NotNull
    private Integer lojaAtiva;
    
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

    public String getCaminhoLogo() {
        return caminhoLogo;
    }

    public void setCaminhoLogo(String caminhoLogo) {
        this.caminhoLogo = caminhoLogo;
    }

    public Integer getRamo() {
        return ramo;
    }

    public void setRamo(Integer ramo) {
        this.ramo = ramo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getLojaAtiva() {
        return lojaAtiva;
    }

    public void setLojaAtiva(Integer lojaAtiva) {
        this.lojaAtiva = lojaAtiva;
    }
    
      
    
    public Empresa converter(EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {   
        Endereco end = null;
        Optional<Cidade> cidadeObj = cidadeRepository.findByCodigoIBGE(cidade);
        if(cidadeObj.isPresent()){
            end = new Endereco(logradouro, tpLogradouro, bairro, numero, cep, complemento, cidadeObj.get(), cidade);
            enderecoRepository.save(end);
        }
        return new Empresa(fantasia, razaosocial, telefone, celular, caminhoLogo, ramo, cnpj, end);
    }
    
    public Empresa atualizar(Integer id, EmpresaRepository empresaRepository, EnderecoRepository enderecoRepository) {
        Empresa empE = empresaRepository.getOne(id);
        empE.setFantasia(this.fantasia);
        empE.setRazaosocial(this.razaosocial);
        empE.setCaminhoLogo(this.caminhoLogo);
        empE.setCelular(this.celular);
        empE.setTelefone(this.telefone);
        empE.setLojaAtiva(this.lojaAtiva);
        empE.setRamo(this.ramo);
        empE.setLojaAtiva(this.lojaAtiva);
        Optional<Endereco> endE = enderecoRepository.findById(empE.getEndereco().getId());
        if(endE.isPresent()){
            Endereco endObj = endE.get();
            endObj.setLogradouro(this.logradouro);
            endObj.setNumero(this.numero);
            endObj.setComplemento(this.complemento);
            endObj.setBairro(this.bairro);
            endObj.setCidade(this.cidade);
            empE.setEndereco(endObj);
        }
        return empE;
    }
    
            
    
}
