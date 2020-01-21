
package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Promocao;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.PromocaoRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class PromocaoForm {
    @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String titulo;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String descricao;   
      
    @NotNull
    private Double valor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Promocao converter(UsuarioApi autor) {      
        return new Promocao(titulo, descricao, valor, autor);
    }
    
    public Promocao atualizar(Long id, PromocaoRepository promocaoRepository) {
        Promocao promoE = promocaoRepository.getOne(id);
        promoE.setTitulo(this.titulo);
        promoE.setDescricao(this.descricao);
        promoE.setValor(this.valor);
        return promoE;
    }
    
    public Promocao alterarSituacao(Long id, PromocaoRepository promocaoRepository) {
        Promocao promoE = promocaoRepository.getOne(id);
        promoE.setSituacao(1);
        return promoE;
    }
    
}
